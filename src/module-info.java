module RecipeApp {
	requires javafx.controls;
	requires java.sql;
	requires javafx.graphics;
	requires junit;
	exports tests to junit;
	opens application to javafx.graphics, javafx.fxml;
}
