import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This is the driver class for the Relationship Tester. It takes in a number of
 * strings that the user inputs, and represents their relationships through an
 * adjacency graph. This is the second version of my implementation, as I
 * couldn't work with an adjacency list for the graph. Done for CSC 232:
 * Foundations of Computation Instructor: Dr. Howard
 * 
 * @author Nathan Sanchez
 *
 */
public class Driver {

	public static void main(String[] args) {

		System.out.println("Hello! Welcome to the Relationship Tester!");
		// Create hashmap to assign the user's input as strings mapped to integers.
		// This will come in handy when indexing them for the adjacency graph.
		HashMap<String, Integer> variableNames = new HashMap<String, Integer>();
		// Create an arraylist to keep track of the relationships of each node.
		ArrayList<String> relationships = new ArrayList<String>();
		Scanner numScanner = new Scanner(System.in);
		// Create a variable to store user input for how many unique strings they will
		// input
		int num = 0;
		System.out.println("How many unique strings will you input?");
		// Store how many unique variables the user would like to store into num
		num = numScanner.nextInt();
		Scanner stringScanner = new Scanner(System.in);
		for (int i = 0; i < num; i++) {
			System.out.println("Please enter unique string " + (i + 1) + ".");
			// get the value of the string that the user input
			String toAdd = stringScanner.nextLine();
			/*
			 * if it is not in the hashmap, then we add it //and assign a value to it. This
			 * will be its position in the adjacency matrix.
			 */
			if (!variableNames.containsKey(toAdd)) {
				variableNames.put(toAdd, i);
			}
		}
		// Ask for and store the number of pairs within the graph
		System.out.println("How many pairs are contained in the set?");
		int pairs = numScanner.nextInt();
		for (int i = 0; i < pairs; i++) {
			/*
			 * Prompt user for the x and y value of that node, and store those values in the
			 * arrayList relationships.
			 */
			System.out.println("Please enter the first value of pair " + (i + 1) + ":");
			String value = stringScanner.nextLine();
			relationships.add(value);
			System.out.println("Enter the second value of pair " + (i + 1) + ": ");
			String otherValue = stringScanner.nextLine();
			relationships.add(otherValue);
			System.out.println();
		}
		/*
		 * initialize Graph, as we can form it with the information we have. It will be
		 * an n x n matrix that is the size of how many unique variables the user
		 * entered.
		 */
		AdjGraph g = new AdjGraph(variableNames.size());
		System.out.print("  ");
		/*
		 * This is for aesthetic purposes: print each of the unique strings on the top
		 * to form the top of a matrix
		 */
		for (String s : variableNames.keySet()) {
			System.out.print(s + " ");

		}
		System.out.println();
		/*
		 * Now, we can form a matrix.
		 * Incrementation for the loop goes up by two, since we are 
		 * grabbing two variables at a time to represent a directional edge between two nodes.
		 * 
		 */
		for (int i = 0; i < relationships.size(); i += 2) {
			try {
				/*
				 * Grab value at i, and attempt to grab the value in front of it.
				 * Using the integer value mapped to that string, we can determine 
				 * its position in the matrix. Then, we add an edge between them.
				 */
				int row = variableNames.get(relationships.get(i));
				int col = variableNames.get(relationships.get(i + 1));
				g.addEdge(row, col);
			} catch (NullPointerException e) {
				/*
				 * If the user input a value that does not exist in the set, then we 
				 * print an error message and close the scanners to prevent memory leaks.
				 */
				System.out.println("One of the values you entered doesn't exist!");
				System.out.println("As such, only the values that existed so far will be printed.");
				numScanner.close();
				stringScanner.close();
				return;
			}
		}
		/*
		 * Prints out the matrix, as well as its values.
		 */
		int count = 0;
		for (String s : variableNames.keySet()) {
			System.out.print(s + " ");
			g.printRow(count);
			System.out.println();
			count++;
		}
		System.out.println();
		//Print properties of this matrix.
		g.printProperties();
		//Close scanners to prevent memory leaks.
		numScanner.close();
		stringScanner.close();

	}

}
