package edu.gatech.seclass.project1;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class ASLTest {

    private ASL asl;
    private String fileDir;

    @Before
    public void setUp() throws Exception {
        asl = new ASL();
        fileDir = new String("src"+ File.separator + "test" + File.separator + "java" + File.separator + "inputfiles"
                + File.separator);
    }
    @After
    public void tearDown() throws Exception {
        asl = null;
        fileDir = null;
    }
    @Test
    public void testComputeAverageSentenceLength1() {
        String comment = "Testing sentences that span multiple lines";
        asl.setFile(new File(fileDir + "input.txt"));
        assertEquals(comment, 7, asl.computeAverageSentenceLength(), 0);
    }
    @Test
    public void testComputeAverageSentenceLength2() {
        String comment = "Testing customized delimiters";
        asl.setFile(new File(fileDir + "input.txt"));
        asl.setSentenceDelimiters("%.");
        assertEquals(comment, 3, asl.computeAverageSentenceLength(), 0);
    }
    @Test
    public void testComputeAverageSentenceLength3() {
        String comment = "Testing customized minimal word length";
        asl.setFile(new File(fileDir + "input.txt"));
        asl.setMinWordLength(5);
        assertEquals(comment, 3, asl.computeAverageSentenceLength(), 0);
    }
    
    @Test
    public void testNonexistentFileException() {
    	String comment = "Testing error message for empty file";
    	
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        asl.setFile(new File(fileDir + "gibberish!!!"));
		asl.setSentenceDelimiters("%.");
		
		double avg = asl.computeAverageSentenceLength();

		System.setOut(System.out);
		
		assertTrue(comment, (outContent.toString().equals("ERROR: Unable to read the input file. File Path is invalid.\n")));
    }
    
    @Test
    public void testEmptyDelimiters() {
    	//Passing an empty string of delimiters should default to the default delimiters.
    	String comment = "Testing empty custom delimiters list";
        asl.setFile(new File(fileDir + "input.txt"));
        asl.setSentenceDelimiters("");
        assertEquals(comment, 7, asl.computeAverageSentenceLength(), 0);
    }
    
    /**
     * Testing bad input handling.
     */
    @Test
    public void testBadMinWordLength() {
    	String comment = "Testing bad min word length";
    	asl.setFile(new File(fileDir + "input.txt"));
        asl.setMinWordLength(-1);
        assertEquals(comment, 7, asl.computeAverageSentenceLength(), 0);
    }
    
    @Test
    public void testEmptyFileException() {
    	String comment = "Testing error message for empty file";
    	
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        asl.setFile(new File(fileDir + "empty.txt"));
		asl.setSentenceDelimiters("%.");
		
		double avg = asl.computeAverageSentenceLength();
		
		System.setOut(System.out);
		
		assertTrue(comment, (outContent.toString().equals("ERROR: Unable to read the input file. File is blank.\n")));
    }

    public void testComputeAverageSentenceLength4() {
    	String comment = "Testing customized delimiters and minimal word length";
    	asl.setFile(new File(fileDir + "input.txt"));
    	asl.setMinWordLength(5);
    	asl.setSentenceDelimiters("%.");
    	assertEquals(comment, 3, asl.computeAverageSentenceLength(), 0);
    }
}
