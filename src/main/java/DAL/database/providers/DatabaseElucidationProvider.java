package DAL.database.providers;

import ACQ.*;
import DAL.database.DatabaseHelper;
import DAL.database.PostgreSqlDatabase;
import DAL.dataobject.*;

import java.sql.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class DatabaseElucidationProvider extends PostgreSqlDatabase implements IElucidationService {
	/**
	 * Create an elucidation with the data it could insert into the database.
	 * So, if only the main elucidation data is registered,
	 * then it will only contain that information.
	 * id = -1, means it could not save default data.
	 * @param citizen the citizen it is about
	 * @param caseworkers the caseworkers on the elucidation
	 * @param inquiry the inquiry about/from the citizen
	 * @return a data-only elucidation
	 */
	@Override
	public IElucidation createElucidation(IUser citizen, Set<IUser> caseworkers, IInquiry inquiry) {
		AtomicReference<Elucidation> atomicReference = new AtomicReference<>();

		executeQuery(conn -> {
			String insertElucidationQuery = "INSERT INTO elucidations(id, applies_ssn, creationdate, isclosed) VALUES (DEFAULT, ?, ?, ?);";
			PreparedStatement ps1 = conn.prepareStatement(insertElucidationQuery, Statement.RETURN_GENERATED_KEYS);

			Timestamp timestamp = Timestamp.from((new Date().toInstant()));

			ps1.setString(1, citizen.getSocialSecurityNumber());
			ps1.setTimestamp(2, timestamp);
			ps1.setBoolean(3, false);

			boolean insertedElucidation = ps1.executeUpdate() == 1;

			ResultSet rs = ps1.getGeneratedKeys();

			Elucidation elucidation;

			if(insertedElucidation && rs.next()) {
				long id = rs.getLong("id");

				boolean insertedCaseworkers = insertCaseworkers(conn, id, caseworkers);
				boolean insertedInquiry = updateInquiry(id, inquiry);

				elucidation = new Elucidation(
						id,
						citizen,
						insertedCaseworkers ? caseworkers : null,
						timestamp,
						null,
						null
				);

				if(insertedInquiry) elucidation.setTask(inquiry);

				atomicReference.set(elucidation);
			}
		});

		return atomicReference.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateInquiry(long id, IInquiry inquiry) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			StringBuilder builder = new StringBuilder();
			builder.append("BEGIN TRANSACTION;");
			builder.append("INSERT INTO inquiries(task_id, source, description) VALUES (?, ?, ?) ON CONFLICT ON CONSTRAINT inquiries_pkey DO UPDATE SET source = ?, description = ?;");
			builder.append("UPDATE cases SET inquries_source = ?, inquries_description = ? WHERE task_id = ?;");
			builder.append("COMMIT;");

			PreparedStatement ps = conn.prepareStatement(builder.toString());
			ps.setLong(1, id);
			ps.setString(2, inquiry.getSource());
			ps.setString(3, inquiry.getDescription());
			ps.setString(4, inquiry.getSource());
			ps.setString(5, inquiry.getDescription());
			ps.setString(6, inquiry.getSource());
			ps.setString(7, inquiry.getDescription());
			ps.setLong(8, id);

			bool.set(ps.executeUpdate() == 1);
		});

		return bool.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateState(long id, boolean isclosed) {
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);

		executeQuery(conn -> {
			String query = "UPDATE elucidations SET isclosed = ? WHERE id = ?;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setBoolean(1, isclosed);
			ps.setLong(2, id);

			atomicBoolean.set(ps.executeUpdate() == 1);
		});

		return atomicBoolean.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateCaseworkers(long id, Set<IUser> users) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			boolean deleted = deleteCaseworkers(conn, id);
			boolean inserted = insertCaseworkers(conn, id, users);

			bool.set(deleted && inserted);
		});

		return bool.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateTaskState(long id, ElucidationTaskState state) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			String updateTaskStateQuery = "UPDATE elucidationshastasks SET state = ? WHERE elucidations_id = ?;";
			PreparedStatement ps1 = conn.prepareStatement(updateTaskStateQuery);
			ps1.setString(1, state.name());
			ps1.setLong(2, id);

			PreparedStatement ps2 = null;

			switch(state) {
				case INQUIRY:
					String removeCaseQuery = "DELETE FROM cases WHERE task_id = ?;";
					ps2 = conn.prepareStatement(removeCaseQuery);
					ps2.setLong(1, id);

					break;
				case CASE:
					String insertCaseQuery = "INSERT INTO cases(task_id) VALUES (?) ON CONFLICT ON CONSTRAINT cases_pkey DO NOTHING;";
					ps2 = conn.prepareStatement(insertCaseQuery);
					ps2.setLong(1, id);

					break;
			}

			bool.set(ps1.executeUpdate() == 1 && ps2.executeUpdate() == 1);
		});

		return bool.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateCitizenConsent(long id, boolean hasConsent) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			String query = "UPDATE cases SET citizensconsent = ? WHERE task_id = ?;";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setBoolean(1, hasConsent);
			ps.setLong(2, id);

			bool.set(ps.executeUpdate() == 1);
		});

		return bool.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateActingMunicipality(long id, String municipality) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> bool.set(updateColumnOnATableWithStringOnTaskId(conn, id, String.valueOf(municipality), "cases", "actingMunicipality")));

		return bool.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateSpecialCircumstances(long id, String newDescription) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> bool.set(updateColumnOnATableWithStringOnTaskId(conn, id, String.valueOf(newDescription), "cases", "specialcircumstances")));

		return bool.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateGuardianAuthority(long id, String newDescription) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> bool.set(updateColumnOnATableWithStringOnTaskId(conn, id, String.valueOf(newDescription), "cases", "guardianauthority")));

		return bool.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateTotalLevelOfFunction(long id, char letter) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			bool.set(updateColumnOnATableWithStringOnTaskId(conn, id, String.valueOf(letter), "cases", "totalleveloffunction"));
		});

		return bool.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateOffers(long id, Set<IGranting> offers) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			boolean deleted = deleteOffers(conn, id);
			boolean inserted = insertOffers(conn, id, offers);

			bool.set(deleted && inserted);
		});

		return bool.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateGrantings(long id, Set<IOffer> grantings) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			boolean deleted = deleteGrantings(conn, id);
			boolean inserted = insertGrantings(conn, id, grantings);

			bool.set(deleted && inserted);
		});

		return bool.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateThemes(long id, Set<ITheme> themes) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			boolean deletedThemes = deleteThemes(conn, id);
			boolean deletedHasThemes = deleteHasThemes(conn, id);
			boolean insertedThemes = insertThemes(conn, id, themes);
			boolean insertedHasThemes = insertHasThemes(conn, id, themes);

			bool.set(deletedThemes && deletedHasThemes && insertedThemes && insertedHasThemes);
		});

		return bool.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateMeeting(long id, IMeeting meeting) {
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);

		executeQuery(conn -> {
			boolean insertUpdate = updateMeetingColumns(conn, id, meeting);
			boolean updateParticipants = updateMeetingParticipants(conn, id, meeting);

			atomicBoolean.set(insertUpdate && updateParticipants);
		});

		return atomicBoolean.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateThirdPartyAttachments(long id, Set<IAttachment> attachments) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IElucidation getElucidation(long id) {
		AtomicReference<Elucidation> elucidation = new AtomicReference<>();

		executeQuery(conn -> {
			ICase _case = getCaseForElucidation(conn, id);
			IInquiry inquiry = getInquiryForElucidation(conn, id);

			ITask task = (_case != null) ? _case : inquiry;

			elucidation.set(new Elucidation(
					id,
					getCitizenForElucidation(conn, id),
					getCaseworkersForElucidation(conn, id),
					getCreationDateForElucidation(conn, id),
					getDialogForElucidation(conn, id),
					task));
			}
		);

		return elucidation.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<IElucidation> getOpenElucidationsFromSSN(String ssn) {
		AtomicReference<Set<IElucidation>> atomicSet = new AtomicReference<>();
		executeQuery(conn -> atomicSet.set(getElucidationsFromSSN(conn, ssn, false)));
		return atomicSet.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<IElucidation> getClosedElucidationsFromSSN(String ssn) {
		AtomicReference<Set<IElucidation>> atomicSet = new AtomicReference<>();
		executeQuery(conn -> atomicSet.set(getElucidationsFromSSN(conn, ssn, true)));
		return atomicSet.get();
	}

	/**
	 * Get all open or closed elucidations that is assigned to a SSN.
	 * @param conn any connection
	 * @param ssn any ssn
	 * @param getClosedElucidations either true or false
	 * @return a set of all the elucidations, if any
	 * @throws SQLException if any sql exception occurs
	 */
	private Set<IElucidation> getElucidationsFromSSN(Connection conn, String ssn, boolean getClosedElucidations) throws SQLException {
		String query = "SELECT id FROM elucidations WHERE isclosed = ? AND (applies_ssn = ? OR id IN (SELECT elucidations_id FROM worksin WHERE users_ssn = ?));";

		PreparedStatement ps = conn.prepareStatement(query);
		ps.setBoolean(1, getClosedElucidations);
		ps.setString(2, ssn);
		ps.setString(3, ssn);

		ResultSet rs = ps.executeQuery();

		Set<IElucidation> elucidations = new HashSet<>();

		while(rs.next()) {
			elucidations.add(getElucidation(rs.getLong(1)));
		}

		return elucidations;
	}

	/**
	 * Update a column from a table with a string, if it is the WHERE is targeting task_id.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @param newValue the new value to update
	 * @param table the name of the table
	 * @param column the name of the column
	 * @return true, if change was successful; otherwise false
	 * @throws SQLException if any sql exception occurs
	 */
	private boolean updateColumnOnATableWithStringOnTaskId(Connection conn, long id, String newValue, String table, String column) throws SQLException {
		String query = "UPDATE "+ table +" SET "+ column +"= ? WHERE task_id = ?;";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, newValue);
		ps.setLong(2, id);

		return ps.executeUpdate() == 1;
	}

	/**
	 * Run a query if by the id only.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @param query any query
	 * @return true, if query was successful; otherwise false
	 * @throws SQLException if any sql exception occurs
	 */
	private boolean runQueryById(Connection conn, long id, String query) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);

		return ps.executeUpdate() >= 0;
	}

	/**
	 * Delete caseworkers from an elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return true, if delete query was successful; otherwise false
	 * @throws SQLException if any sql exception occurs
	 */
	private boolean deleteCaseworkers(Connection conn, long id) throws SQLException {
		String query = "DELETE FROM worksin WHERE elucidations_id = ?;";
		PreparedStatement ps = conn.prepareStatement(query);

		return ps.executeUpdate() >= 0;
	}

	/**
	 * Insert caseworkers to an elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @param caseworkers a set of caseworkers for a specific elucidation
	 * @return true, if insert query was successful; otherwise false
	 * @throws SQLException if any sql exception occurs
	 */
	private boolean insertCaseworkers(Connection conn, long id, Set<IUser> caseworkers) throws SQLException {
		String query = "INSERT INTO worksin(elucidations_id, users_ssn) VALUES (?, ?);";
		PreparedStatement ps = conn.prepareStatement(query);

		for(IUser caseworker : caseworkers) {
			ps.setLong(1, id);
			ps.setString(2, caseworker.getSocialSecurityNumber());

			ps.addBatch();
		}

		return Arrays.stream(ps.executeBatch()).allMatch(value -> value >= 0);
	}

	/**
	 * Get offers from a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return a set of offers, if any, from a specific elucidation
	 * @throws SQLException if any sql exception occurs
	 */
	private Set<IOffer> getOffers(Connection conn, long id) throws SQLException {
		String query = "SELECT * FROM offerings WHERE cases_id = ?;";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();

		Set<IOffer> offers = new HashSet<>();

		while(rs.next()) {
			offers.add(new Offer(rs.getString("description"), rs.getInt("paragraph")));
		}

		return offers;
	}

	/**
	 * Delete offers from a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return true, if delete query was successful; otherwise false
	 * @throws SQLException if any sql exception occurs
	 */
	private boolean deleteOffers(Connection conn, long id) throws SQLException {
		return runQueryById(conn, id, "DELETE FROM offerings WHERE cases_id = ?;");
	}

	/**
	 * Insert offers to a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @param offers a set of offers
	 * @return true, if insert query was successful; otherwise false
	 * @throws SQLException if any sql exception occurs
	 */
	private boolean insertOffers(Connection conn, long id, Set<IGranting> offers) throws SQLException {
		String query = "INSERT INTO offerings(cases_id, description, paragraph) VALUES (?, ?, ?);";
		PreparedStatement ps = conn.prepareStatement(query);

		for(IGranting offer : offers) {
			ps.setLong(1, id);
			ps.setString(2, offer.getDescription());

			ps.addBatch();
		}

		return Arrays.stream(ps.executeBatch()).allMatch(value -> value >= 0);
	}

	/**
	 * Get grantings from a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return a set of granting, if any, from a specific elucidation
	 * @throws SQLException if any sql exception occurs
	 */
	private Set<IGranting> getGrantings(Connection conn, long id) throws SQLException {
		String query = "SELECT * FROM grantings WHERE cases_id = ?;";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();

		Set<IGranting> grantings = new HashSet<>();

		while(rs.next()) {
			grantings.add(new Granting(rs.getString("description")));
		}

		return grantings;
	}

	/**
	 * Delete grantings from a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return true, if delete query was successful; otherwise false
	 * @throws SQLException if any sql exception occurs
	 */
	private boolean deleteGrantings(Connection conn, long id) throws SQLException {
		return runQueryById(conn, id, "DELETE FROM grantings WHERE cases_id = ?;");
	}

	/**
	 * Insert grantings for a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @param grantings a set of grantings to insert for a specific elucidation
	 * @return true, if insert query was successful; otherwise false
	 * @throws SQLException if any sql exception occurs
	 */
	private boolean insertGrantings(Connection conn, long id, Set<IOffer> grantings) throws SQLException {
		String query = "INSERT INTO grantings(cases_id, description) VALUES (?, ?);";
		PreparedStatement ps = conn.prepareStatement(query);

		for(IOffer granting : grantings) {
			ps.setLong(1, id);
			ps.setString(2, granting.getDescription());

			ps.addBatch();
		}

		return Arrays.stream(ps.executeBatch()).allMatch(value -> value >= 0);
	}

	/**
	 * Get themes from a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return a set of themes, if any, from a specific elucidation
	 * @throws SQLException if any sql exception occurs
	 */
	private Set<ITheme> getThemes(Connection conn, long id) throws SQLException {
		String query = "SELECT themes.*, casehasthemes.documentation FROM casehasthemes, themes WHERE themes.cases_id = ? AND themes.cases_id = casehasthemes.cases_id;";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();

		Set<ITheme> themes = new HashSet<>();

		while(rs.next()) {
			themes.add(new Theme(ThemeEnum.fromString(rs.getString("theme")), rs.getString("documentation"), rs.getString("subtheme"), rs.getInt("leveloffunction")));
		}

		return themes;
	}

	/**
	 * Delete themes from a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return true, if delete query was successful; otherwise false
	 * @throws SQLException if any sql exception occurs
	 */
	private boolean deleteThemes(Connection conn, long id) throws SQLException {
		return runQueryById(conn, id, "DELETE FROM themes WHERE cases_id = ?;");
	}

	/**
	 * Delete 'has themes' from a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return true, if delete query was successful; otherwise false
	 * @throws SQLException if any sql exception occurs
	 */
	private boolean deleteHasThemes(Connection conn, long id) throws SQLException {
		return runQueryById(conn, id, "DELETE FROM casehasthemes WHERE cases_id = ?;");
	}

	/**
	 * Insert themes to a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @param themes a set of themes to insert for a specific elucidation
	 * @return true, if insert query was successful; otherwise false
	 * @throws SQLException if any sql exception occurs
	 */
	private boolean insertThemes(Connection conn, long id, Set<ITheme> themes) throws SQLException {
		String query = "INSERT INTO themes(cases_id, theme, subtheme, leveloffunction) VALUES (?, ?, ?, ?);";
		PreparedStatement ps = conn.prepareStatement(query);

		for(ITheme theme : themes) {
			ps.setLong(1, id);
			ps.setString(2, theme.getTheme().getName());
			ps.setString(3, theme.getSubtheme());
			ps.setString(4, String.valueOf(theme.getLevelOfFunction()));

			ps.addBatch();
		}

		return Arrays.stream(ps.executeBatch()).allMatch(value -> value >= 0);
	}

	/**
	 * Insert 'has themes' to a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @param themes a set of themes to insert for a specific elucidation
	 * @return true, if insert query was successful; otherwise false
	 * @throws SQLException if any sql exception occurs
	 */
	private boolean insertHasThemes(Connection conn, long id, Set<ITheme> themes) throws SQLException {
		String query = "INSERT INTO casehasthemes(cases_id, theme, documentation) VALUES (?, ?, ?);";
		PreparedStatement ps = conn.prepareStatement(query);

		for(ITheme theme : themes) {
			ps.setLong(1, id);
			ps.setString(2, theme.getTheme().getName());
			ps.setString(3, theme.getDocumentation());

			ps.addBatch();
		}

		return Arrays.stream(ps.executeBatch()).allMatch(value -> value >= 0);
	}

	/**
	 * Update meeting columns for a meeting.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @param meeting the meeting to insert or update
	 * @return true, if update query was successful; otherwise false
	 * @throws SQLException if any sql exception occurs
	 */
	private boolean updateMeetingColumns(Connection conn, long id, IMeeting meeting) throws SQLException {
		String query = "INSERT INTO meetings(elucidation_id, number, information, date, creator, iscancelled) VALUES (?, ?, ?, ?, ?, ?) " +
				"ON CONFLICT ON CONSTRAINT meetings_pkey DO UPDATE SET date = ?, information = ?, iscancelled = ?;";
		PreparedStatement ps = conn.prepareStatement(query);
		Timestamp timestamp = new Timestamp(meeting.getMeetingDate().getTime());

		ps.setLong(1, id);
		ps.setInt(2, meeting.getNumber());
		ps.setString(3, meeting.getInformation());
		ps.setTimestamp(4, timestamp);
		ps.setString(5, meeting.getCreator().getSocialSecurityNumber());
		ps.setBoolean(6, meeting.isCancelled());
		ps.setTimestamp(7, timestamp);
		ps.setString(8, meeting.getInformation());
		ps.setBoolean(9, meeting.isCancelled());

		return ps.executeUpdate() == 1;
	}

	/**
	 * Update meeting participants for a meeting.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @param meeting the meeting to update participants
	 * @return true, if delete query was successful; otherwise false
	 * @throws SQLException if any sql exception occurs
	 */
	private boolean updateMeetingParticipants(Connection conn, long id, IMeeting meeting) throws SQLException {
		String deleteQuery = "DELETE FROM participates WHERE cases_id = ? AND meetings_number = ?;";
		PreparedStatement ps1 = conn.prepareStatement(deleteQuery);

		String insertQuery = "INSERT INTO participates(cases_id, meetings_number, users_ssn) VALUES (?, ?, ?);";
		PreparedStatement ps2 = conn.prepareStatement(insertQuery);

		ps1.setLong(1, id);
		ps1.setInt(2, meeting.getNumber());

		ps2.setLong(1, id);
		ps2.setInt(2, meeting.getNumber());

		for(IUser user : meeting.getParticipants()) {
			ps2.setString(3, user.getSocialSecurityNumber());
			ps2.addBatch();
		}

		return ps1.executeUpdate() >= 0 && Arrays.stream(ps2.executeBatch()).allMatch(value -> value >= 0);
	}

	/**
	 * Get the creation date from a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return get the creation date from a specific elucidation
	 * @throws SQLException if any sql exception occurs
	 */
	private Date getCreationDateForElucidation(Connection conn, long id) throws SQLException {
		String query = "SELECT creationdate FROM elucidations WHERE id = ?;";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();

		Date date = null;

		if(rs.next()) {
			date = rs.getTimestamp(1);
		}

		return date;
	}

	/**
	 * Get the inquiry that is attached to a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return Inquiry, if any; otherwise null;
	 * @throws SQLException if any sql exception occurs
	 */
	private Inquiry getInquiryForElucidation(Connection conn, long id) throws SQLException {
		String query = "SELECT state, source, description from elucidationshastasks, inquiries WHERE elucidationshastasks.task_id = ? AND inquiries.task_id = ?;";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);
		ps.setLong(2, id);
		Inquiry inquiry = null;

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			inquiry = new Inquiry(rs.getString("source"), rs.getString("description"), ElucidationTaskState.valueOf(rs.getString("state")));
		}

		return inquiry;
	}

	/**
	 * Get the case from a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return Case, if any; otherwise null
	 * @throws SQLException if any sql exception occurs
	 */
	private Case getCaseForElucidation(Connection conn, long id) throws SQLException {
		String query = "SELECT state, inquries_source, inquries_description, guardianauthority, citizensconsent, actingmunicipality, specialcircumstances, totalleveloffunction " +
				"FROM elucidationshastasks, cases WHERE elucidationshastasks.task_id = ? AND cases.task_id = ?;";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);
		ps.setLong(2, id);

		Case _case = null;

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			_case = new Case(rs.getString("inquries_source"), rs.getString("inquries_description"), ElucidationTaskState.valueOf(rs.getString("state")));
			_case.setCitizenConsent(rs.getBoolean("citizensconsent"));
			_case.setSpecialCircumstances(rs.getString("specialcircumstances"));
			_case.setActingMunicipality(rs.getString("actingmunicipality"));
			_case.setGuardianAuthority(rs.getString("guardianauthority"));
			_case.setTotalLevelOfFunction(rs.getString("totalleveloffunction").charAt(0));
			_case.setThemes(getThemes(conn, id));
			_case.setOffers(getOffers(conn, id));
			_case.setGrantings(getGrantings(conn, id));
		}

		return _case;
	}

	/**
	 * Get the citizen data from a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return a user, if connected; otherwise null
	 * @throws SQLException if any sql exception occurs
	 */
	private IUser getCitizenForElucidation(Connection conn, long id) throws SQLException {
		String query = "SELECT * FROM users WHERE ssn = (SELECT applies_ssn FROM elucidations WHERE id = ?);";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();

		User user = new User();

		if(rs.next()) {
			DatabaseHelper.setUserFromResultSet(rs, user);
		} else {
			user = null;
		}

		return user;
	}

	/**
	 * Get all the caseworkers that is attached to a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return a set of users/caseworkers, if any; otherwise an empty set
	 * @throws SQLException if any sql exception occurs
	 */
	private Set<IUser> getCaseworkersForElucidation(Connection conn, long id) throws SQLException {
		String query = "SELECT * FROM users WHERE ssn IN (SELECT users_ssn FROM worksin WHERE elucidations_id = ?);";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();

		Set<IUser> users = new HashSet<>();

		User user;
		while(rs.next()) {
			user = new User();
			DatabaseHelper.setUserFromResultSet(rs, user);
			users.add(user);
		}

		return users;
	}

	/**
	 * Get the dialog for a elucidation.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return A dialog object, if any; otherwise an empty dialog with limited functionality
	 * @throws SQLException if any sql exception occurs
	 */
	private IDialog getDialogForElucidation(Connection conn, long id) throws SQLException {
		Dialog dialog = new Dialog();
		dialog.setMeetings(getDialogMeetings(conn, id));
		return dialog;
	}

	/**
	 * Get dialog meetings from an elucidation.
	 * Invoked by {@link DatabaseElucidationProvider#getDialogForElucidation(Connection, long)}.
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @return a set of meetings, if any; otherwise an empty set
	 * @throws SQLException if any sql exception occurs
	 */
	private Set<IMeeting> getDialogMeetings(Connection conn, long id) throws SQLException {
		String query = "SELECT * FROM meetings WHERE elucidation_id = ?;";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();

		Set<IMeeting> meetings = new HashSet<>();

		Meeting meeting;
		while(rs.next()) {
			meeting = new Meeting();
			meeting.setNumber(rs.getInt("number"));
			meeting.setInformation(rs.getString("information"));
			meeting.setMeetingDate(rs.getTimestamp("date"));
			meeting.setCreator(getMeetingCreator(conn, rs.getString("creator")));
			meeting.setParticipants(getMeetingParticipates(conn, id, meeting));

			meetings.add(meeting);
		}

		return meetings;
	}

	/**
	 *
	 * @param conn any connection
	 * @param id any elucidation identifier
	 * @param meeting the meeting to search for
	 * @return A set of users, that is participating; otherwise an empty set
	 * @throws SQLException if any sql exception occurs
	 */
	private Set<IUser> getMeetingParticipates(Connection conn, long id, IMeeting meeting) throws SQLException {
		String query = "SELECT * FROM users WHERE ssn IN (SELECT users_ssn FROM participates WHERE cases_id = ? AND meetings_number = ?);";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);
		ps.setInt(2, meeting.getNumber());

		ResultSet rs = ps.executeQuery();

		Set<IUser> participates = new HashSet<>();

		User user;
		if(rs.next()) {
			user = new User();
			DatabaseHelper.setUserFromResultSet(rs, user);
			participates.add(user);
		}

		return participates;
	}

	/**
	 * Get the creator of a meeting.
	 * @param conn any connection
	 * @param ssn any ssn
	 * @return the creator in user format with all their information
	 * @throws SQLException if any sql exception occurs
	 */
	private IUser getMeetingCreator(Connection conn, String ssn) throws SQLException {
		String query = "SELECT * FROM users WHERE ssn = ?;";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, ssn);

		ResultSet rs = ps.executeQuery();

		User user = new User();

		if(rs.next()) {
			DatabaseHelper.setUserFromResultSet(rs, user);
		}

		return user;
	}
}