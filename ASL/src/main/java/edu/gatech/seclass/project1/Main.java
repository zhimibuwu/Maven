package edu.gatech.seclass.project1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static ASL aslObject = new ASL();

	public static void main(String[] args) throws Exception {


		int minWL = Integer.MIN_VALUE;
		String delimiters = null;

		String filePath = args[0];


		String[] filePathWords = filePath.split("/");
		filePath = System.getProperty("user.dir") + "/" + filePathWords[filePathWords.length-1];

		File file = new File(filePath);

		aslObject.setFile(file);

		// Command Line arguments given. If false, use defaults
		if (args.length > 1) {

			//If both command line arguments are given
			if (args.length > 3) {
				if (args[1].equalsIgnoreCase("-d")) {
					if (args[3].equalsIgnoreCase("-l") && args.length == 5) {
						minWL = Integer.parseInt(args[4]);
						delimiters = args[2];
					}

					else {
						throw new Exception("ERROR: Ill-formed command. Ill-Formed arguments");
					}
				}

				else if (args[1].equalsIgnoreCase("-l")) {
					if (args[3].equalsIgnoreCase("-d") && args.length == 5) {
						minWL = Integer.parseInt(args[2]);
						delimiters = args[4];
					}

					else {
						throw new Exception("ERROR: Ill-formed command. Ill-Formed arguments");
					}
				}
			}

			// Only one of the command line arguments given
			else {
				if (args[1].equalsIgnoreCase("-l"))
					minWL = Integer.parseInt(args[2]);

				else if (args[1].equalsIgnoreCase("-d"))
					delimiters = args[2];
				else
					throw new Exception("ERROR: Ill-formed command. Ill-Formed arguments");
			}
		}

		if (minWL!=Integer.MIN_VALUE && minWL<=0) {
			throw new Exception("ERROR: Ill-formed command. Ill-Formed arguments. The min word length is invalid");
		}

		aslObject.setMinWordLength(minWL);
		aslObject.setSentenceDelimiters(delimiters);

		System.out.println(aslObject.computeAverageSentenceLength());

	}

}
