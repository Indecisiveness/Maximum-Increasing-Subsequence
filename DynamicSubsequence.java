package cs310;


public class DynamicSubsequence {
	private int[] longest; //longest path possible for the first i elements of full seq
	private int[] prev; //index of previous additive path for longest paths
	private int seqLen; //length of longest sequence found
	private int endOf; //index of longest sequence found
	
	private int[] seq; //full input sequence
	
	
	//accept an array as input and create and fill associated arrays
	public DynamicSubsequence(int[] arr) {
		seq = arr;
		longest = new int[arr.length];
		prev = new int[arr.length];
		fillLongest();
		
	}
	//find the longest possible path for each subsequence
	private void fillLongest() {
		
		for (int i = 0; i < longest.length; i++) {
			int currNum = seq[i];
			int prevVals = 0;
			prev[i] = -1;
			for (int j = 0; j < i; j++) {
				if (currNum > seq[j] && longest[j] > prevVals) {
					prevVals = longest[j];
					prev[i] = j;
				}
			}
			longest[i] = prevVals + 1;
			if (longest[i] > seqLen) {
				seqLen = longest[i];
				endOf = i;
			}
		}
	}
	
	//print the longest path
	public void printLongest() {
		int i = endOf;
		printChildren(i);
	}
	
	//internal recursive function to retrieve children of longest path
	private void printChildren(int i) {
		if (prev[i] != -1) {
			printChildren(prev[i]);
			System.out.print(", " + seq[i]);
		} else {
			System.out.print(seq[i]);
		}
		
	}
	
	//accepts command line arguments in the form of integers separated by space
	public static void main(String[]args) {
		int[] myArr = new int[args.length];
		for (int i = 0; i<myArr.length; i++) {
			myArr[i] = Integer.parseInt(args[i]);
		}
		DynamicSubsequence myDS = new DynamicSubsequence(myArr);
		myDS.printLongest();
		
		
		
	}
	
	
	
	
	
}
