//package com.daniharsh.InsightData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordCount {

	public static void main(String[] args) {
		String inputFile = "../wc_input/";
		String ouptutFile = "../wc_output/wc_result.txt";
		File inFile = new File(inputFile);
		//To make sure that all files are sorted
		TreeSet<String> fileNames = new TreeSet<String>();
		if (inFile.isDirectory()) { //Check if file is directory
			File[] listOfFiles = inFile.listFiles();
			for (File f : listOfFiles)
				fileNames.add(f.getAbsolutePath());
		} else {
			fileNames.add(inFile.getName());
		}
		//Intialize readers and writers
		BufferedReader br = null;
		BufferedWriter bw = null;
		String line = null;
		String[] splittedHyphen;
		String[] splittedConjuction;
		// To keep key-value pairs sorted according to key
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		for (String fileName : fileNames) {
			try {
				br = new BufferedReader(new FileReader(fileName));
				System.out.println("Currently reading " + fileName);
				//Read current line
				while ((line = br.readLine()) != null) {
					StringTokenizer tokenizer = new StringTokenizer(line,
							" _\t\r\f.,;?!#\n:\"()");
					//Convert current token to lowercase letter
					while (tokenizer.hasMoreTokens()) {
						String current = tokenizer.nextToken().trim()
								.toLowerCase();
						//if string contains hyphen then split it
						if (current.contains("-")) {
							splittedHyphen = current.split("\\-");
							current = ""; //reintialize current
							for (String e : splittedHyphen)
								current += e;
						}
						//if string contains 's then split it
						if (current.contains("'")) {
							splittedConjuction = current.split("\\'");
							current = "";
							for (String e : splittedConjuction)
								current += e;
						}
						//check if current word is actually in map
						// if yes then increment count otherwise
						// put 1.
						if (map.containsKey(current))
							map.put(current, map.get(current) + 1);

						else
							map.put(current, 1);
					}
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error in reading File!");
			}
		}
		//Write into buffer after all files are read
		try {
			bw = new BufferedWriter(new FileWriter(ouptutFile));
			//Just iterate over map and extract key value pairs
			for (Map.Entry<String, Integer> it : map.entrySet()) {
				bw.write(it.getKey() + "\t\t" + it.getValue() + "\n");
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("Error in Writing File");
		}
	}

}
