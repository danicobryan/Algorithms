import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Calendar;

public class Select {
	public static void main (String [] args) throws IOException{
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String s = stdIn.readLine();
		ArrayList<Integer> data = new ArrayList<Integer>();


		while (s != null){
			data.add(Integer.parseInt(s));
			s = stdIn.readLine();
		}

		try{

			int k = Integer.parseInt(args[0]);

			if(k > data.size() || k < 1){
				System.out.println("BAD DATA");
				System.exit(0);
			}
			
			//long startTime = System.currentTimeMillis();
			System.out.println(quicksort(data, 0, data.size() - 1, k));
			//long endTime = System.currentTimeMillis();
			//long totalTime = endTime - startTime;
			//System.out.println("Current time is : " + totalTime);
		} catch(Exception e){
			System.out.println("BAD DATA");
		}	
	}

	private static int randPartition(ArrayList<Integer> data, int left, int right){
		Random rand = new Random();
		int pivotIndex = rand.nextInt((right - left) + 1) + left;
		
		while(left < right){
			while(left < pivotIndex){
				if(data.get(left) > data.get(pivotIndex)){
					swap(data, left, pivotIndex);
					pivotIndex = left;
				} else {
					left++;
				}
			}
			while(right > pivotIndex){
				if(data.get(right) < data.get(pivotIndex)){
					swap(data, right, pivotIndex);
					pivotIndex = right;
				} else {
					right--;
				}
			}
		}
		return pivotIndex;
		
	}

	private static int quicksort(ArrayList<Integer> data, int start, int end, int k){
		int pivot = randPartition(data, start, end);
		if(pivot == k - 1){
			return data.get(pivot);
		} else if(pivot > k - 1){
			return quicksort(data, start, pivot - 1, k);
		} else {
			return quicksort(data, pivot + 1, end, k);
		}
	}

	private static void swap(ArrayList<Integer> data, int i, int j){
		int temp = data.get(i);
		data.set(i, data.get(j));
		data.set(j, temp);
	}
}