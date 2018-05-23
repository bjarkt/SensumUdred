package DAL.database.providers;

import ACQ.*;
import BLL.case_opening.third_party_information.IAttachment;
import BLL.meeting.IDialog;
import DAL.database.PostgreSqlDatabase;

import java.sql.PreparedStatement;
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
			String query = "UPDATE inquiries SET description = ? WHERE task_id = ?;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, newDescription);
			ps.setLong(2, id);

			bool.set(ps.executeUpdate() == 1);
		});

		return bool.get();
	}

	@Override
	public boolean updateOffers(long id, IOffer... offers) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			String deleteQuery = "DELETE FROM offerings WHERE cases_id = ?;";
			String insertQuery = "INSERT INTO offerings(cases_id, description, paragraph) VALUES (?, ?, ?);";

			PreparedStatement ps1 = conn.prepareStatement(deleteQuery);
			ps1.setLong(1, id);

			PreparedStatement ps2 = conn.prepareStatement(insertQuery);

			for(IOffer offer : offers) {
				ps2.setLong(1, id);
				ps2.setString(2, offer.getDescription());
				ps2.setInt(3, offer.getParagraph());

				ps2.addBatch();
			}
			boolean deleted = ps1.executeUpdate() >= 0;
			int[] updated = ps2.executeBatch();

			bool.set(deleted && Arrays.stream(updated).allMatch(i -> i >= 1));
		});

		return bool.get();
	}

	@Override
	public boolean updateGranting(long id, IGranting ... grantings) {
		AtomicBoolean bool = new AtomicBoolean(false);

		executeQuery(conn -> {
			String deleteQuery = "DELETE FROM grantings WHERE cases_id = ?;";
			String insertQuery = "INSERT INTO grantings(cases_id, description) VALUES (?, ?);";

			PreparedStatement ps1 = conn.prepareStatement(deleteQuery);
			ps1.setLong(1, id);

			PreparedStatement ps2 = conn.prepareStatement(insertQuery);

			for(IGranting granting : grantings) {
				ps2.setLong(1, id);
				ps2.setString(2, granting.getDescription());

				ps2.addBatch();
			}

			boolean deleted = ps1.executeUpdate() >= 0;
			int[] updated = ps2.executeBatch();

			bool.set(deleted && Arrays.stream(updated).allMatch(i -> i >= 1));
		});

		return bool.get();
	}

	@Override
	public boolean updateCaseworkers(long id, IUser... users) {
		return false;
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
		return false;
	}

	@Override
	public boolean updateSpecialCircumstances(long id, String newDescription) {
		return false;
	}

	@Override
	public boolean updateGuardianAuthority(long id, String newDescription) {
		return false;
	}

	@Override
	public boolean updateTotalLevelOfFunction(long id, char letter) {
		return false;
	}

	@Override
	public boolean updateThemes(long id, ITheme... themes) {
		return false;
	}

	@Override
	public boolean updateThirdPartyAttachments(long id, IAttachment... attachments) {
		return false;
	}

	@Override
	public IElucidation getElucidation(long id) {
		return null;
	}
}