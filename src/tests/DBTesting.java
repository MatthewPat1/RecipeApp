package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.DatabaseController;

public class DBTesting {

	@Before
	public void createDatabaseConnectionTest() {
		DatabaseController dbc = new DatabaseController();
	}
	
}
