public class Pokemon
{
	String name;
	String type;
	int dexNumber;
	
	public Pokemon()
	{
		name = "Charmander";
		type = "Fire";
		dexNumber = 4;
	}
	
	public Pokemon(String n, String t, int d)
	{
		name = n;
		type = t;
		dexNumber = d;
	}
	
	public void print()
	{
		System.out.println("Your pokemon is " + name + " and is a " + type + " type.");
		System.out.println("The PokeDex number is " + dexNumber);
	}
}