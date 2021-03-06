
public class Phobia {
	
	private String name;
	private String description;

	
	public Phobia()
	{
		name = description = "None yet";
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String toString()
	{
		return this.name + "\n" + this.description;
	}
	
	public boolean equals (Phobia aFear)
	{
		return this.name.equals(aFear.getName()) 
				&& this.description.equals(aFear.getDescription());
	}
}
