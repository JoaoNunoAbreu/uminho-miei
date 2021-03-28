/**
 * Licensee: vr(Universidade do Minho)
 * License Type: Academic
 */
import org.orm.*;
public class CreateGameManagementSystemData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = GameManagementSystemPersistentManager.instance().getSession().beginTransaction();
		try {
			User user = UserDAO.createUser();
			// Initialize the properties of the persistent object here
			UserDAO.save(user);
			Game game = GameDAO.createGame();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : price, year
			GameDAO.save(game);
			Platform platform = PlatformDAO.createPlatform();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : year
			PlatformDAO.save(platform);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateGameManagementSystemData createGameManagementSystemData = new CreateGameManagementSystemData();
			try {
				createGameManagementSystemData.createTestData();
			}
			finally {
				GameManagementSystemPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
