/**
 * This is the AdjGraph class for the Relationship Tester. It is a 2D array that
 * holds either a 1 or 0 in its contents. If the value at a certain index is 1,
 * then that means the there is a link between the value in the row and the
 * value in the column. It implements a graph through an adjacency matrix.
 * 
 * @author Nathan Sanchez
 *
 */
public class AdjGraph {
	private int[][] matrix;

	/**
	 * Constructor for the AdjGraph, which is an adjacency matrix.
	 * 
	 * @param nodes: number of nodes in the array.
	 */
	public AdjGraph(int nodes) {
		this.matrix = new int[nodes][nodes];
	}

	/**
	 * A method that prints out the row of the adjacency matrix.
	 * 
	 * @param index: index of the row that the user wants to print.
	 */
	public void printRow(int index) {
		for (int i = 0; i < this.matrix[index].length; i++) {
			System.out.print(this.matrix[index][i] + " ");
		}
	}

	/**
	 * A method that establishes a relationship between two nodes. Changes the value
	 * in the matrix to 1 to represent that there is a link between the two
	 * parameters.
	 * 
	 * @param startingPos: Row of the x value of the point.
	 * @param endingPos:   Column of the y value of the point.
	 */
	public void addEdge(int startingPos, int endingPos) {
		matrix[startingPos][endingPos] = 1;
	}

	/**
	 * A method that determines if the graph is reflexive. That is, every value in
	 * the array also points toward itself.
	 * 
	 * @return
	 */
	public boolean isReflexive() {
		// loop through array
		for (int i = 0; i < this.matrix.length; i++) {
			/*
			 * If there is not a diagonal line of 1s going from the top left to the bottom
			 * right, return false.
			 */
			if (this.matrix[i][i] != 1) {
				return false;
			}
		}
		// Otherwise, the graph is reflexive.
		return true;
	}

	/**
	 * A method that determines if a graph is symmetric. That is, for every value of
	 * (x,y), (y,x) exists and there is a link between both of them.
	 * 
	 * @return
	 */
	public boolean isSymmetric() {
		// loop through matrix
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix[0].length; j++) {
				/**
				 * If the value at [i][j] and [j][i] are not the same, then function is not
				 * symmetric.
				 */
				if (this.matrix[i][j] != this.matrix[j][i]) {
					return false;
				}
			}
		}
		// Otherwise, the function is symmetric.
		return true;
	}

	/**
	 * A method that determines if a graph is transitive. That is, if (a,b) and
	 * (b,c), we check to see if (a,c) exists. Looks for "shortcuts" in between the
	 * values of the matrix.
	 * 
	 * @return
	 */
	public boolean isTransitive() {
		// loop through entire matrix
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix[0].length; j++) {
				/*
				 * If there is a link between [i][j], then we check if there is a link between
				 * [j][k] and [i][k]. If these two values aren't the same, then the graph is not
				 * transitive.
				 */
				if (this.matrix[i][j] == 1) {
					for (int k = 0; k < this.matrix.length; k++) {
						if ((this.matrix[j][k] == 1) && (this.matrix[i][k] == 0)) {
							return false;
						}
					}
				}
			}
		}
		// Otherwise, the graph is transitive.
		return true;
	}

	/**
	 * A method that determines if a graph is antisymmetric. That is, the reversed
	 * pair of [i][j] = 0, unless i and j are the same value.
	 * 
	 * @return
	 */
	public boolean isAntiSymmetric() {
		// loop through entire array
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix[0].length; j++) {
				/*
				 * If i and j are not the same index, we check if the values at those indexes
				 * are not zero. If there are, then we return false.
				 */
				if ((i != j)) {
					if ((this.matrix[i][j] != 0) && (this.matrix[j][i] != 0)) {
						return false;
					}
				}
			}
		}
		// Otherwise, the graph is antisymmetric.
		return true;
	}

	/**
	 * A method that determines if a graph is an equivalence relation. That is, the
	 * graph is reflexive, symmetric, and transitive.
	 * 
	 * @return
	 */
	private boolean isEquivalence() {
		if (this.isReflexive() && this.isSymmetric() && this.isTransitive()) {
			return true;
		}
		return false;
	}

	/**
	 * A method that determines if a graph is an equivalence relation. That is, the
	 * graph is reflexive, transitive, and antisymmetric.
	 * 
	 * @return
	 */
	private boolean isPartialOrder() {
		if (this.isReflexive() && this.isTransitive() && this.isAntiSymmetric()) {
			return true;
		}
		return false;
	}
	
	/**
	 * A method that prints out the properties of the matrix.
	 */
	public void printProperties() {

		if (this.isReflexive()) {
			System.out.println("This graph is reflexive.");
		} else {
			System.out.println("This graph is not reflexive.");
		}

		if (this.isSymmetric()) {
			System.out.println("This graph is symmetric.");
		} else {
			System.out.println("This graph is not symmetric.");
		}

		if (this.isTransitive()) {
			System.out.println("This graph is transitive.");
		} else {
			System.out.println("This graph is not transitive.");
		}

		if (this.isAntiSymmetric()) {
			System.out.println("This graph is antisymmetric.");
		} else {
			System.out.println("This graph is not antisymmetric.");
		}
		if (this.isPartialOrder()) {
			System.out.println("This graph is a partial order.");
		} else if (this.isEquivalence()) {
			System.out.println("This graph is an equivalence relation.");
			;
		} else {
			System.out.println("This graph is neither a partial order or an equivalence relation.");
		}

	}
}
