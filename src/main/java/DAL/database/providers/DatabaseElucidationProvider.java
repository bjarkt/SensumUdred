package DAL.database.providers;

import ACQ.*;
import BLL.case_opening.third_party_information.IAttachment;
import DAL.database.DatabaseHelper;
import DAL.database.PostgreSqlDatabase;
import DAL.dataobject.Dialog;
import DAL.dataobject.Elucidation;
import DAL.dataobject.Meeting;
import DAL.dataobject.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class DatabaseElucidationProvider extends PostgreSqlDatabase implements IElucidationService {
	@Override
	public IElucidation createElucidation(IUser citizen, Set<IUser> caseworkers, IInquiry inquiry, IDialog dialog) {
		return null;
	}

	@Override
	public boolean updateInqueryDescription(long id, String newDescription) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			bool.set(updateColumnOnATableWithStringOnTaskId(conn, id, newDescription, "inquiries", "description"));
		});

		return bool.get();
	}

	@Override
	public boolean updateCaseworkers(long id, IUser... users) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			boolean deleted = deleteCaseworkers(conn, id);
			boolean inserted = insertCaseworkers(conn, id, users);

			bool.set(deleted && inserted);
		});

		return bool.get();
	}

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

	@Override
	public boolean updateActingMunicipality(long id, String municipality) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> bool.set(updateColumnOnATableWithStringOnTaskId(conn, id, String.valueOf(municipality), "cases", "actingMunicipality")));

		return bool.get();
	}

	@Override
	public boolean updateSpecialCircumstances(long id, String newDescription) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> bool.set(updateColumnOnATableWithStringOnTaskId(conn, id, String.valueOf(newDescription), "cases", "specialcircumstances")));

		return bool.get();
	}

	@Override
	public boolean updateGuardianAuthority(long id, String newDescription) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> bool.set(updateColumnOnATableWithStringOnTaskId(conn, id, String.valueOf(newDescription), "cases", "guardianauthority")));

		return bool.get();
	}

	@Override
	public boolean updateTotalLevelOfFunction(long id, char letter) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			bool.set(updateColumnOnATableWithStringOnTaskId(conn, id, String.valueOf(letter), "cases", "totalleveloffunction"));
		});

		return bool.get();
	}

	@Override
	public boolean updateOffers(long id, IOffer ... offers) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			boolean deleted = deleteOffers(conn, id);
			boolean inserted = insertOffers(conn, id, offers);

			bool.set(deleted && inserted);
		});

		return bool.get();
	}

	@Override
	public boolean updateGranting(long id, IGranting ... grantings) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			boolean deleted = deleteGrantings(conn, id);
			boolean inserted = insertGrantings(conn, id, grantings);

			bool.set(deleted && inserted);
		});

		return bool.get();
	}

	@Override
	public boolean updateThemes(long id, ITheme ... themes) {
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

	@Override
	public boolean updateThirdPartyAttachments(long id, IAttachment... attachments) {
		return false;
	}

	@Override
	public IElucidation getElucidation(long id) {
		AtomicReference<Elucidation> elucidation = new AtomicReference<>();

		executeQuery(conn -> elucidation.set(new Elucidation(
				getCitizenForElucidation(conn, id),
				getCaseworkersForElucidation(conn, id),
				getCreationDateForElucidation(conn, id),
				getDialogForElucidation(conn, id)))
		);

		return elucidation.get();
	}

	private boolean updateColumnOnATableWithStringOnTaskId(Connection conn, long id, String newDescription, String table, String column) throws SQLException {
		String query = "UPDATE "+ table +" SET "+ column +"= ? WHERE task_id = ?;";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, newDescription);
		ps.setLong(2, id);

		return ps.executeUpdate() == 1;
	}

	private boolean deleteByCaseId(Connection conn, long id, String query) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);

		return ps.executeUpdate() >= 0;
	}

	private boolean deleteCaseworkers(Connection conn, long id) throws SQLException {
		String query = "DELETE FROM worksin WHERE elucidations_id = ?;";
		PreparedStatement ps = conn.prepareStatement(query);

		return ps.executeUpdate() >= 0;
	}

	private boolean insertCaseworkers(Connection conn, long id, IUser ... caseworkers) throws SQLException {
		String query = "INSERT INTO worksin(elucidations_id, users_ssn) VALUES (?, ?);";
		PreparedStatement ps = conn.prepareStatement(query);

		for(IUser caseworker : caseworkers) {
			ps.setLong(1, id);
			ps.setString(2, caseworker.getSocialSecurityNumber());

			ps.addBatch();
		}

		return Arrays.stream(ps.executeBatch()).allMatch(value -> value >= 0);
	}

	private boolean deleteOffers(Connection conn, long id) throws SQLException {
		return deleteByCaseId(conn, id, "DELETE FROM offerings WHERE cases_id = ?;");
	}

	private boolean insertOffers(Connection conn, long id, IOffer ... offers) throws SQLException {
		String query = "INSERT INTO offerings(cases_id, description, paragraph) VALUES (?, ?, ?);";
		PreparedStatement ps = conn.prepareStatement(query);

		for(IOffer offer : offers) {
			ps.setLong(1, id);
			ps.setString(2, offer.getDescription());

			ps.addBatch();
		}

		return Arrays.stream(ps.executeBatch()).allMatch(value -> value >= 0);
	}

	private boolean deleteGrantings(Connection conn, long id) throws SQLException {
		return deleteByCaseId(conn, id, "DELETE FROM grantings WHERE cases_id = ?;");
	}

	private boolean insertGrantings(Connection conn, long id, IGranting ... grantings) throws SQLException {
		String query = "INSERT INTO grantings(cases_id, description) VALUES (?, ?);";
		PreparedStatement ps = conn.prepareStatement(query);

		for(IGranting granting : grantings) {
			ps.setLong(1, id);
			ps.setString(2, granting.getDescription());

			ps.addBatch();
		}

		return Arrays.stream(ps.executeBatch()).allMatch(value -> value >= 0);
	}

	private boolean deleteThemes(Connection conn, long id) throws SQLException {
		return deleteByCaseId(conn, id, "DELETE FROM themes WHERE cases_id = ?;");
	}

	private boolean deleteHasThemes(Connection conn, long id) throws SQLException {
		return deleteByCaseId(conn, id, "DELETE FROM casehasthemes WHERE cases_id = ?;");
	}

	private boolean insertThemes(Connection conn, long id, ITheme ... themes) throws SQLException {
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

	private boolean insertHasThemes(Connection conn, long id, ITheme ... themes) throws SQLException {
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

	private IDialog getDialogForElucidation(Connection conn, long id) throws SQLException {
		Dialog dialog = new Dialog();
		dialog.setMeetings(getDialogMeetings(conn, id));
		return dialog;
	}

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
		}

		return meetings;
	}

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