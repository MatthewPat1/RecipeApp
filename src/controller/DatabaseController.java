package controller;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
public class DatabaseController {

	private String user = "";
	private String pass = "";
	private String url = "jdbc:mysql://localhost:3306/recipedb";
	Connection con;
	public DatabaseController() {
		try {
			con = DriverManager.getConnection(url,user,pass);
			System.out.println("Database Connection Successful!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Create account
	public boolean createAccount(Account a) {
		String user = a.getUser();
		String pass = a.getPass();
		String name = a.getName();
		String query = String.format("INSERT INTO account(name,user,pass) values('%s','%s','%s')", name, user, pass);
		try(PreparedStatement state = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);){
			state.execute();
			System.out.println("UPDATED DATABASE SUCCESS!");
			return true;
		} catch (SQLException e) {
			System.out.println("DATABASE UPDATE FAILURE: Probably already exists");
			e.printStackTrace();
			return false;
		}
	}

	// Get account name
	public String getAccountName(String user, String pass){
		String name ="No Return";
		String query = String.format("SELECT name from account(name,user,pass) WHERE user='%s' and pass='%s'", user, pass);
		try(Statement state = con.createStatement();
				ResultSet res = state.executeQuery(query)
				){
			name = res.getString("name");
		}catch (SQLException e){
			e.printStackTrace();
		}
		return name;
	}
	
	// Get recipe titles
	public ArrayList<String> getRecipeTitles(String user){
		ArrayList<String> titles = new ArrayList<>();

		String query = "SELECT title FROM recipe WHERE account_user='" + user +"'";
		try(
				Statement state = con.createStatement();
				ResultSet res = state.executeQuery(query)
				){
			while(res.next()) {
				titles.add(res.getString("title"));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}

		return titles;
	}
	//Get Recipes
	public ArrayList<String> getRecipes(Account a){
		ArrayList<String> titles = new ArrayList<>();

		String user = a.getUser();
		String query = "SELECT title FROM recipe WHERE account_user='" + user +"'";
		try(
				Statement state = con.createStatement();
				ResultSet res = state.executeQuery(query)
				){
			while(res.next()) {
				titles.add(res.getString("title"));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}

		return titles;
	}
	//Add Recipe
	public boolean insertRecipe(String title, String user) {
		String query = String.format("INSERT INTO recipe(account_id,title) VALUES(%s,%s)", user, title);
		try(PreparedStatement state = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);){
			state.execute();
			System.out.println("UPDATED DATABASE SUCCESS!");
			return true;
		} catch (SQLException e) {
			System.out.println("DATABASE UPDATE FAILURE");
			e.printStackTrace();
			return false;
		}
	}
	
	// Retrieve steps of recipe
	public HashMap<Integer,String> getRecipeSteps(int recipeID){
		HashMap<Integer,String> steps = new HashMap<>();
		String query = "SELECT step,step_num FROM step WHERE recipe_id='%s'";

		try(Statement state = con.createStatement();
				ResultSet res = state.executeQuery(query)){
			while(res.next()) {
				steps.put(res.getInt("step_num"), res.getString("step"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return steps;
	}
	
}
