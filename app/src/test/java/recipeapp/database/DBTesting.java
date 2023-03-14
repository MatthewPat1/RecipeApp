package recipeapp.database;

import org.junit.jupiter.api.*;
import recipeapp.controller.DatabaseController;

public class DBTesting {

	@BeforeAll
	public void createDatabaseConnectionTest() {
		DatabaseController dbc = new DatabaseController();
		dbc.createUser(null);
	}
	
}
