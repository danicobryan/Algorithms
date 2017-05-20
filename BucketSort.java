import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;

public class BucketSort{

	static double minimum;
	static double maximum;
	static ArrayList<Double> data;
	static ArrayList<ArrayList<Double>> buckets;

	/**
     * This is the main method takes in a list of Doubles from a standard input and implements the Bucket Sort Algorithm to sort the Doubles in ascending order.
     * @param args Unused.
     * @return Nothing.
     * @exception IOException On input error.
     * @see IOException
     */

	public static void main (String [] args) throws IOException{
		minimum = 0;
		maximum = 0;

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String s = stdIn.readLine();
		data = new ArrayList<Double>();

		while (s != null){
			double value = Double.parseDouble(s);
			if(data.size() == 0){
				maximum = value;
				minimum = value;
			} else {
				minimum = Math.min(value, minimum);
				maximum = Math.max(value, maximum);
			}

			data.add(value);
			s = stdIn.readLine();
		}

		buckets = new ArrayList<ArrayList<Double>>(data.size());

		for(int bucket = 0; bucket < data.size(); bucket++){
			buckets.add(new ArrayList<Double>());
		}

		fillBuckets(buckets, data);

		for(int bucket = 0; bucket < buckets.size(); bucket++){
			insertSort(buckets.get(bucket));
		}

		remerge(buckets, data);

		for(int dataIndex = 0; dataIndex < data.size(); dataIndex++){
			System.out.println(data.get(dataIndex));
		}
	}

	/**
     * Puts Doubles into specified buckets using the bucketIndex method. 
     * @param bucketArrayList The ArrayList of buckets. 
     * 						  Buckets are made of ArrayLists that hold Doubles.
     * @param originalList The Arraylist of the data read in from standard input.
   	 */

	private static void fillBuckets(ArrayList<ArrayList<Double>> bucketArrayList, ArrayList<Double> originalList){
		for(int i = 0; i < originalList.size(); i++){
			int indexOfBucket;
			if(originalList.get(i) == maximum){
				indexOfBucket = data.size() - 1;
			} else {
				indexOfBucket = bucketIndex(originalList, i);
			}

			bucketArrayList.get(indexOfBucket).add(data.get(i));
		}
	}

	/**
     * Merges the sorted ArrayList of buckets with the original ArrayList of Doubles read from standard input.
     * @param bucketArrayList The sorted ArrayList of buckets, which are ArrayLists of Doubles.				  
     * @param originalList The Arraylist of the data read in from standard input.
   	 */
	private static void remerge(ArrayList<ArrayList<Double>> bucketArrayList, ArrayList<Double> originalList){
		int dataIndex = 0;
		for(int bucketIndex = 0; bucketIndex < buckets.size(); bucketIndex++){
			if(bucketArrayList.get(bucketIndex).size() != 0){
				for(int item = 0; item < bucketArrayList.get(bucketIndex).size(); item++){
					originalList.set(dataIndex, bucketArrayList.get(bucketIndex).get(item));
					dataIndex++;
				}
			}
		}
	}

	/**
     * Uses Insert Sort algorithm to sort the Doubles in all of the individual buckets.
     * @param list An Array List of Doubles that make up an individual bucket.
   	 */
	private static void insertSort(ArrayList<Double> list){
		for(int i = 1; i < list.size(); i++){
			double temp = list.get(i);
			int j;
			for(j = i - 1; j >= 0 && temp < list.get(j); j--){
				list.set(j + 1, list.get(j));
			}
			list.set(j + 1, temp);
		}
	}

	/**
     * Calculates an index for a value to be placed in a specific bucket and ensures uniform distribution.
     * @param input An Array List of Doubles read from a standard input.
     * @param index The index of the value we are checking in the Array List of Doubles read from a standard input. 
     * @return The index that the value should be mapped to to ensure uniform distribution.
   	 */	
	private static int bucketIndex(ArrayList<Double> input, int index){
		int size = input.size();
		double numOfElements = (double)(size);
		double result = (((input.get(index) - minimum)/(maximum - minimum)) * (numOfElements - 1));
		return (int)(Math.floor(result));

	} 

}