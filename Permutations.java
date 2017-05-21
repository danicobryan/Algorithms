public class Permutations{


	/**
     * This is the main method that implements a permutation algorithm that generates all the possible permutations of the decimal numbers
     * then prints whether the permutation solves the Pentagon Problem, as well as the edge sum, and how many possible solutions there are.
     * @param args Unused.
     * @return Nothing.
     */

	public static void main(String [] args){
		int[] decimalNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		permute(decimalNumbers);
	}

	/**
     * Prints an Array of numbers as a string, and the edge sum of the sides of the pentagon.
     * @param numbers The array of decimal numbers to be printed
   	 */

	public static void print(int[] numbers){
		String result = "";
		for(int i = 0; i < numbers.length; i++){
			result += Integer.toString(numbers[i]);
		}
		System.out.println("Permutation: " + result);
		System.out.println("Sum: " + (numbers[0] + numbers[1] + numbers[2]));
	}


	/**
     * Swaps two numbers in an array of Integers
     * @param numbers The array of decimal numbers
     * @param first One of the integers to be swapped
     * @param second The other integer to be swapped
   	 */
	public static void swap(int[] numbers, int first, int second) {
        int temp = numbers[first];
        numbers[first] = numbers[second];
        numbers[second] = temp;
    }

    /**
     * Implements a permutation algorithm to determine whether there is another permutation in lexicographic order next
     * @param numbers The array of decimal numbers
     * @return boolean Returns whether there is another permutation in lexicographic order next
   	 */
    public static boolean hasNext(int[] numbers) {
        int k; 
        for (k = numbers.length - 2; k >= 0; k--){
        	if (numbers[k] < numbers[k+1]){
        		break;
        	} 
        }
        
        if (k == -1) {
        	return false;
        } 

        int l = numbers.length - 1;
        while (numbers[k] > numbers[l]){
        	l--;
        }

        swap(numbers, l, k);

        for (int x = numbers.length-1, y = k + 1; x > y; x--, y++)
            swap(numbers, x, y);

        return true;
    }

    /**
     * Executes the permutation algorithm and prints out all possible permutations if the permutation solves the Pentagon Problem
     * @param numbers The array of decimal numbers
   	 */
    public static void permute(int[] numbers) {
    	int count = 0;
        while (hasNext(numbers)){
        	if(isEqualSum(numbers)){
        		print(numbers);
        		count++;
        	}
        }
  		System.out.println("Possible Solutions: " + count);
    }

    /**
     * CHecks whether the permutation solves the Pentagon Problem: all sides of the Pentagon are equal
     * @param numbers The array of decimal numbers
     * @return boolean Returns true if all the sums of the pentagon are equal
   	 */
    public static boolean isEqualSum(int[] numbers){
    	int sum1 = numbers[0] + numbers[1] + numbers[2];
    	int sum2 = numbers[2] + numbers[3] + numbers[4];
    	int sum3 = numbers[4] + numbers[5] + numbers[6];
    	int sum4 = numbers[6] + numbers[7] + numbers[8];
    	int sum5 = numbers[8] + numbers[9] + numbers[0];

    	if(sum1 == sum2 && sum1 == sum3 && sum1 == sum4 && sum1 == sum5){
    		return true;
    	} 
    	return false;
    }

}