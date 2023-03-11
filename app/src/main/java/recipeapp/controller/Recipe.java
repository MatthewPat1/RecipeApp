package controller;

public class Recipe {

	private int id;
	private int numSteps;
	private String name;


	public Recipe(int id, String name,  int numSteps) {
		this.id = id;
		this.name = name;
		this.numSteps = numSteps;

	}

	public int getId() {
		return id;
	}

	public int getNumSteps() {
		return numSteps;
	}

	public void setNumSteps(int numSteps) {
		this.numSteps = numSteps;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
