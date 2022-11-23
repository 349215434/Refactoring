import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class AnswerData implements Observer {

  // Properties or Attributes
  String answerFile;
  File checkFile;

  // Methods

    /**
   * Makes the data into an array and prints
   * @param String data - passes data as a String
   */ 
  public String printResults(String data) {
    // making it a array
    int totalLines = countingLines(data);
    String[] studentDataArray = new String[totalLines - 1];
    readResultForAns(data, studentDataArray);
    for (String word : studentDataArray) {
      System.out.print(word + " ");
      System.out.println();
    }
    return data;
  }

  /**
   * makes answer a 1D array, loads response, and cleans out unnecessary data
   * 
   * @param String file - reads the results from the file that the user wants to open
   * @param String[] studentDataArray- the array with student data
   * @throws IOException
   */
  public static void readResultForAns(String file, String[] studentDataArray) {
    BufferedReader br = null;
    int lineNum = 0;
    try {
      br = new BufferedReader(new FileReader(file));
      String contentLine = br.readLine();
      while (contentLine != null) {
        if (contentLine.length() == 0) {
          contentLine = br.readLine();
          continue;
        }
        // replaces the space into a smaller space
        studentDataArray[lineNum] = contentLine.replace(" ", "");
        lineNum++;
        contentLine = br.readLine();
      }
    }
    // catching errors
    catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (br != null) {
          br.close();
        }
      } catch (IOException ioe) {
        System.out.println("Error in closing the BufferedReader");
      }
    }
  }

  /**
   * counts the lines in the array
   * @param String file this specifically counts the lines of the file the user wants to open
   * @throws IOException
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

  // @Override
  public void update(String file) {
    System.out.println("updated AnswerData");
  }
}
