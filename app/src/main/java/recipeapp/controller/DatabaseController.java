package controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
public class DatabaseController {

	private String[] cred = new String[2];
	private String url = "jdbc:mysql://localhost:3306/recipe_maker";
	Connection con;
	public DatabaseController() {
		try {
			cred = dbCred();
			con = DriverManager.getConnection(url,cred[0],cred[1]);
			System.out.println("Database Connection Successful!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String[] dbCred() {
		String[] creds = new String[2];
		File f = new File("DBCredentials.txt");
		try (Scanner myReader = new Scanner(f)){
			while(myReader.hasNextLine()) {
				creds = myReader.nextLine().split(" ");
			}
		}catch(FileNotFoundException e) {
			System.out.println("Error getting credentials text file probably doesn't exist.");
			e.printStackTrace();
		}
		return creds;
	}
	

	public boolean createUser(Account a) {
		/*
		 * args: Account
		 * return: boolean on the success or failure of 
		 */
		String user = a.getUser();
		String pass = a.getPass();
		String name = a.getName();
		String query = String.format("INSERT INTO user(name,user,pass) values('%s','%s','%s')", name, user, pass);
		try(PreparedStatement state = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);){
			state.execute();
			System.out.println("UPDATED DATABASE SUCCESS!");
			return true;
		} catch (SQLException e) {
			System.out.println("DATABASE UPDATE FAILURE: Probably already exists");
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<String> getUserInfo(String user, String pass){
		/*
		 * args: user and pass for the user
		 * return: arraylist of user info id, user, pass, name
		 */
		ArrayList<String> info = new ArrayList<>();
		String query = String.format("SELECT * from users WHERE user='%s' and pass='%s'", user, pass);
		try(Statement state = con.createStatement();
				ResultSet res = state.executeQuery(query)
				){
			res.next();
			info.add(Integer.toString(res.getInt(1)));
			info.add(user);
			info.add(pass);
			info.add(res.getString("name"));
		}catch (SQLException e){
			e.printStackTrace();
		}
		return info;
	}
	

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
