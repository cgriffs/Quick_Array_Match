import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class ArrayMatch {

	/*
	 * match
	 * Purpose: Determine if the two given arrays 'match'
	 * Parameters: int[] a, int[] b - the two arrays
	 * Returns: boolean - true if arrays 'match', false otherwise
	 * Preconditions: a and b have the same number of elements
	 */
	public static boolean match(int[] a, int[] b) {
		int size = a.length;
		
		//For Even Element Start
		if(size%2 == 0){
			if(splitCompare(a, b) == true){
				return true;
			} else {
				return false;
			}

		//For Odd Elements Start
		} else {
			for (int i = 0; i < a.length; i++) {
				if(a[i]!=b[i]){
					return false;
				}
			}
			return true;
		}
	}

	public static boolean splitCompare(int[] a, int[] b){
		int size = a.length;
		if(size%2 != 0){
			return false;
		}else{
			int halfSize = (a.length/2);

			int[] a1 = new int[halfSize];
			a1 = Arrays.copyOfRange(a, 0, halfSize);

			int[] a2 = new int[halfSize];
			a2 = Arrays.copyOfRange(a, halfSize, a.length);

			int[] b1 = new int[halfSize];
			b1 = Arrays.copyOfRange(b, 0, halfSize);

			int[] b2 = new int[halfSize];
			b2 = Arrays.copyOfRange(b, halfSize, b.length);

			//System.out.println(Arrays.toString(a1));
			//System.out.println(Arrays.toString(a2));
			//System.out.println(Arrays.toString(b1));
			//System.out.println(Arrays.toString(b2));

			//Conditions 2A and 2B
			if(Arrays.equals(a1, b1) == true){
				if(Arrays.equals(a2, b2) == true){
					return true;
				}
				if(Arrays.equals(a1, b2) == true){
					return true;
				}
			}
			//Condition 2C
			else if((Arrays.equals(a2, b1) == true) && (Arrays.equals(a2, b2) == true)){
				return true;
			}

			else{
				return splitCompare(a1, b1);
			}
			return false;
		}	
	}
	
	/*
	 * fillArray
	 * Purpose: Fills arrays with contents read from Scanner
	 * Parameters: int[] x, Scanner fileReader
	 * Returns: nothing
	 */
	public static void fillArray(int[] x, Scanner fileReader) throws NoSuchElementException {
		Scanner f = new Scanner(fileReader.nextLine());
		for (int i = 0; i < x.length; i++) {
			x[i] = f.nextInt();
		}
	}
		
	/*
	 * a3Setup
	 * Purpose: Initializes the input arrays for Assignment 3 match detection
	 *          by reading data from the text file named fname
	 * Parameters: String fname - name of the file containig input data
	 * Returns: nothing
	 */
	public static void a3Setup(String fname) {
		Scanner fileReader = null;
		int[] A = null;
		int[] B = null;
		
		try {
			fileReader = new Scanner(new File(fname));
		} catch (FileNotFoundException e) {
			System.out.println("Error finding input file");
			e.printStackTrace();
			return;
		}
		
		try {
			int size = Integer.parseInt(fileReader.nextLine());
			A = new int[size];
			B = new int[size];
			fillArray(A, fileReader);
			fillArray(B, fileReader);
		} catch (NoSuchElementException e) {
			System.out.println("Error reading input file data");
			e.printStackTrace();
		}
		
		if (match(A,B)) {
			System.out.println("match found");
		} else {
			System.out.println("no matches");
		}
	}
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Incorrect usage, should be:");
			System.out.println("java MysteryArray filename.txt");
			return;
		}
		a3Setup(args[0]);
	}
}
