package edu.gatech.seclass.project1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ASL {

	String filetext;
	int defaultWl = 3;
	int customWl = Integer.MIN_VALUE;
	String defaultDelims = ".?!;";
	String customDelims = "";
	File file;


	public void setFile(File file) {
		this.file = file;
	}

	@SuppressWarnings("null")
	public double computeAverageSentenceLength() {
		if (!file.exists()) {
			System.out.println("ERROR: Unable to read the input file. File Path is invalid.");
			return Double.MIN_VALUE;
		}
		
		try{
			if(fileEmptyCheck(file)) {
				System.out.println("ERROR: Unable to read the input file. File is blank.");
				return Double.MIN_VALUE;
			}
		} catch(Exception e) {
			
		}
		try{
			String fileData = getFileData(file);
			setfileText(fileData);
			return ASLUtility(filetext, customWl, customDelims);
		} catch(Exception e)
		{
		}

		return Double.MIN_VALUE;
	}
	
	public static String getFileData(File file) throws Exception {
		StringBuilder fileText = new StringBuilder();
		FileInputStream fis = null;
		int content;

		// Try entered file path
		try {	

			fis = new FileInputStream(file);
			while ((content = fis.read()) != -1) {
				fileText.append((char)content);
			}

		} catch (Exception e) {

			throw (new Exception("ERROR: Unable to process input file. File Path is invalid"));
		}

		fis.close();

		return fileText.toString();
	}

	public void setSentenceDelimiters(String string) {
		if (string!=null)
			customDelims = string;
	}

	public void setMinWordLength(int i) {
		if(i!=Integer.MIN_VALUE)
			customWl = i;
	}

	public void setfileText(String s)
	{
		filetext = s;
	}

	public File getFile()
	{
		return file;
	}

	public int ASLUtility(String textPostProc, int minWL, String delimiters) throws Exception
	{

		if(minWL<1)
			minWL = defaultWl;

		if(delimiters!=null && !delimiters.isEmpty())
			delimiters = "["+delimiters+"]";
		else
			delimiters = "["+defaultDelims+"]";

		String[] sentences = textPostProc.split(delimiters);

		int wordCount = 0;
		int sentenceCount = 0;

		for(String sentence:sentences)
		{
			if(!sentence.trim().isEmpty() && sentence.trim()!=null)
			{
				String words[] = sentence.split("\\s");

				for(String word:words)
				{
					if(word.length()>=minWL)
						wordCount++;
				}
				
				sentenceCount++;
			}
		}

		if(sentenceCount==0)
		{
			throw new Exception("ERROR: Program terminated unexpectedly. Sentences count is 0");
		}
		return (wordCount/sentenceCount);

	}
	
	public static boolean fileEmptyCheck(File file) throws FileNotFoundException, IOException
	{
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));

		if (br.readLine() == null || br.readLine()=="")
		{
			return true;
		}

		return false;
	}

}


