import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {

		// Get input file path
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter input filepath: ");
		String input_filepath = sc.nextLine();
		System.out.print("Enter output filepath: ");
		String output_filepath = sc.nextLine();
		sc.close();

		// Start the simulation
		Simulation simulation = new Simulation();
		simulation.Simulation(input_filepath, output_filepath);

	}

}
