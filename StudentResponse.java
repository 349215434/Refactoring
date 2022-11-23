import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class StudentResponse implements Observer {

  // Properties or Attributes
  String studentResponse;
  File checkFile;
  // Methods
  
  /**
 * This takes the results of the array and makes it a array
 * @param String data - takes the String data
 */
  void printResults(String data) {
    int totalLines = countingLines(data);
    String[][] studentsArray = new String[totalLines-1][];
     //printing result of the array
    populateArray(data, studentsArray);

    // This makes it a 1D array first
    // this is taking each line of the array at a time
    for (String[] line : studentsArray) {
      for (String word : line) {
        System.out.print(word+ "  ");
      }
      System.out.println();
    }
  }

/**
 * counts the lines in the array
 * @param String file - this specifically counts the lines of the file the user wants to open
 * @return -1
 */
  public static int countingLines(String file) {
    BufferedReader br = null;
    int count = 0;
    try {
      br = new BufferedReader(new FileReader(file));
      String contentLine = br.readLine();
      while (contentLine != null) {
        count++;
        contentLine = br.readLine();
      }
      return count;
    } catch (IOException e) {
      e.printStackTrace();
      return -1;
    } finally {
      try {
        if (br != null) {
          br.close();
        }
      } catch (IOException e) {
        System.out.println("Error in closing the BufferedReader");
      }
    }
  }
  
/**
 * Populates array aka makes it into a array 
 * @param String file - the file the user wants to open
 * @param String[][] data - the data in a 2D array
 */
  public static void populateArray(String file, String[][] data) {
    BufferedReader br = null;
    int lineNum = 0;
    //Gets rid of the commas from the data as they are not needed 
    String delim = ",";
    try {
      br = new BufferedReader(new FileReader(file));
      br.readLine();
      String contentLine = br.readLine();
      while (contentLine != null) {
        data[lineNum] = contentLine.split(delim,-1);
        lineNum++;
        contentLine = br.readLine();
      }
      //catches any errors
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (br != null) {
          br.close();
        }
      } catch (IOException e) {
        System.out.println("Error in closing the BufferedReader");
      }
    }
  }
  
  //@Override
  public void update(String file) {
    System.out.println("updated StudentResponse"); 
  }
}
