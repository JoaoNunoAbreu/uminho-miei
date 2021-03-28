/**
 * Licensee: vr(Universidade do Minho)
 * License Type: Academic
 */
import org.orm.*;
public class CreateGameManagementSystemDatabaseSchema {
	public static void main(String[] args) {
		try {
			ORMDatabaseInitiator.createSchema(GameManagementSystemPersistentManager.instance());
			GameManagementSystemPersistentManager.instance().disposePersistentManager();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
