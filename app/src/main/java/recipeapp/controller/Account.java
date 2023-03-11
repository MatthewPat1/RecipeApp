package controller;

public class Account {
	private String user;
	private String pass;
	private String name;
	private int account_id;
	private Recipe[] recipes;
	
	public Account(String user, String pass, String name, Recipe[] recipes) {
		this.user=user;
		this.pass=pass;
		this.name=name;
	}
	public String getUser() {
		return user;
	}
	public String getPass() {
		return pass;
	}
	public String getName() {
		return name;
	}

	

}
