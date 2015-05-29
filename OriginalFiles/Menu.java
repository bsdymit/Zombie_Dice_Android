import java.util.Scanner;

/**
 * 
 * The main menu for Zombie Dice CLI
 * 
 * @author Bradley Dymit
 *
 */
public class Menu 
{
	private String [] options; 
	
	/**
	 * 
	 * Constructor for Menu that takes in a string array of options and assigns
	 * the array to a field array of options to be displayed in the menu.
	 * 
	 * @param options
	 */
	public Menu(String [] options)
	{
		this.options = options;
		
	}
	
	/**
	 * 
	 * This method uses a Scanner to retrieve input from the user on which menu
	 * option they choose. This process implements a verification method to 
	 * ensure proper data is entered by the user. 
	 * 
	 * @return user's choice integer
	 */
	public int getChoice()
	{
		int choice;
		String input;
		Scanner scan = new Scanner(System.in);
		
		this.displayMenu();
		System.out.print("\nPlease enter your menu choice (1-" + this.options.length + "): ");
		input = scan.nextLine();
		choice = this.verifyChoice(input);
		
		while(choice == -1)
		{
			displayMenu();
			System.out.print("Invalid menu option, please enter a " +
					"number between 1 and " + this.options.length + ".");
			input = scan.nextLine();
			choice = this.verifyChoice(input);
		}
		
		return choice;
	}
	
	/**
	 * 
	 * This private method prints out the menu choices to the screen.
	 * 
	 */
	private void displayMenu()
	{
		System.out.println(printHeading());
		for(int i = 0; i < this.options.length; i++)
		{
			System.out.println((i+1) + ". " + this.options[i]);
		}
	}
	
	/**
	 * 
	 * This private method takes in user input as a String and verifies that
	 * the user entered a valid integer. If a valid integer is provided, the
	 * method will return that integer else it will return -1.
	 * 
	 * @param input user entered choice
	 * @return users choice in integer form if valid
	 */
	private int verifyChoice(String input)
	{
		int choice;
		try
		{
			choice = Integer.parseInt(input);
		}
		catch(NumberFormatException e)
		{
			choice = -1;
		}
		
		if(choice < 1 || choice > this.options.length)
		{
			choice = -1;
		}
		
		return choice;
	}
	
	/**
	 * 
	 * This private method returns a String heading to display on the menu.
	 * 
	 * @return String heading
	 */
	private String printHeading()
	{
		String heading = "\n\n\n\t\t\t-------------------" +
				   "\n\t\t\t|***Z*O*M*B*I*E***|" +
				   "\n\t\t\t|*****D*I*C*E*****|" +
				   "\n\t\t\t-------------------\n";
		
		return heading;
	}
}
