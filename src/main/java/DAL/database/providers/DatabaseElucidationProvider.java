package DAL.database.providers;

import ACQ.*;
import BLL.case_opening.third_party_information.IAttachment;
import BLL.meeting.IDialog;
import DAL.database.PostgreSqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

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
		return null;
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
}