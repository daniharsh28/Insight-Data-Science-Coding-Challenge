//package com.daniharsh.InsightData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class FindMedian {

	public static void main(String[] args)  {
		String inputFile = "../wc_input/"; //input directory
		String outputFile = "../wc_output/med_result.txt"; //output file
		File inFile = new File(inputFile);
		//To keep the fileNames Sorted I use TreeSet!
		TreeSet<String> fileNames = new TreeSet<String>(); 
		if (inFile.isDirectory()) { //check whether file is directory
			File[] listOfFiles = inFile.listFiles();
			for (File f : listOfFiles)  
				fileNames.add(f.getAbsolutePath());
		} else {
			fileNames.add(inFile.getName());
		}
		//declare all readers and writers used for reading and writing
		BufferedReader br = null;
		String line = null;
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(outputFile));
		} catch (IOException e1) {
			System.out.println("Some Error in opening output file!");
		}
		//For each file in TreeSet find Median
		for(String fileName: fileNames){
			try {
				//open a file using BufferedReader
				br = new BufferedReader(new FileReader(fileName));
				System.out.println("Currently reading is "+ fileName);
				while ((line = br.readLine()) != null) { //Read a line
					int len = line.split(" ").length;
					//if line length is 1 and first character is "", it is a blank line
					// Hence we should take its length as 0 instead of 1
					if(line.split(" ").length == 1 && line.split(" ")[0].equals(""))
						len = 0;
					MedianUsingHeap.add((float) len);
					bw.write(MedianUsingHeap.getMedian() + "\n");
				}
				br.close(); //close reading stream
			} catch (IOException e) {
				System.out.println("Sorry some error occured!");
			}
		}
		
		try {
			bw.close();//close writing stream
		} catch (IOException e) {
			System.out.println("Error in closing output stream!");
		}
	}

}
//We will use two heaps one maxHeap and another minHeap, and if there are even number of elements in both
// heaps, we will use peek from both the heap and average it and for odd number of elements return peek from 
// maxHeap
class MedianUsingHeap {
	//Create reverseComparator for MaxHeap
	static Comparator<Float> reverseComparator = new Comparator<Float>() {
		
		@Override
		public int compare(Float o1, Float o2) {
			// TODO Auto-generated method stub
			return o2.compareTo(o1);
		}
	};
	// I am using PriorityQueue implementation of Heap
	public static PriorityQueue<Float> minHeap = new PriorityQueue<Float>();
	public static PriorityQueue<Float> maxHeap = new PriorityQueue<Float>(20,reverseComparator);
	public static int elems = 0;
	//add a number in Heap
	public static void add(float num) {
		maxHeap.add(num);

		if (elems % 2 == 0) {
		
			if (minHeap.isEmpty()) {
				elems++; // First time when we are adding in heap
				return;
			}
			// If maxHeap's peek is more than minHeap's peek
			// then just change the peeks. 
			else if (maxHeap.peek() > minHeap.peek()) { 
				float maxRoot = maxHeap.poll();
				float minRoot = minHeap.poll();
				maxHeap.add(minRoot);
				minHeap.add(maxRoot);
			}
		} else {
			minHeap.add(maxHeap.poll());
		}
		elems++;
	}
	// return median according to odd or even numbers in list.
	public static float getMedian() {
		if (elems % 2 != 0)
			return maxHeap.peek();
		else
			return (float) ((maxHeap.peek() + minHeap.peek()) / 2.0);
	}
}
