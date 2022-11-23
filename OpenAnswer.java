import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class OpenAnswer implements Observer {
  /**
   * Meant to solve the equations but I did not get a chance to figure out 
   * how to do it (this is where the code would have gone). Instead it asks if you 
   * want to skip to just seeing the answers or get it to solve
   */
  public void chooseSolveOrSkip(){
    Scanner reader = new Scanner(System.in);
    System.out.println("\nType in 'solve' for the program to solve the equations for you or 'skip' to go directly to the answer key");
    String studentResponse = reader.nextLine().toLowerCase();

    // continues in the while loop until the input is valid
    // if the input isn't 'solve' or 'skip', it stays in the while
    // loop until it becomes one or the other
    while (!studentResponse.equals("solve") && !studentResponse.equals("skip")) {
      System.out.println("Invalid input. \n\nType 'solve' for the program to solve the equations or 'skip' to go directly to the answer key");
      studentResponse = reader.nextLine().toLowerCase();
    }
    if (studentResponse.equals("solve")) {
      System.out.println("GOING TO SOLVE");

      // Diego's part
      // EquationSolver solver = new EquationSolver();
      // solver.solve_quest(??);
    }
    else if (studentResponse.equals("skip")){
      System.out.println("GOING TO SKIP");
      goToAnswer();
    } 
  }

  public static void goToAnswer(){
    Subject subject = new Subject();
    String openAnswer = subject.takeInput("Type in the answer file (include the file extension)");
    int answerLines = countingLines(openAnswer);
    // this makes the answer into a 1D array because it will be easier to compare later this way
    String[] ansArray = new String[answerLines-1];
    readResultForAns(openAnswer, ansArray);

    // prints out every element in the array individually
    for (String word : ansArray) {
      System.out.print(word + "  "); 
      System.out.println();
    }
    // This is loading the student responses again
    String responses = subject.takeInput("Type in the student response file (include the file extension)");
    int responseLines = countingLines(responses);
    // response is a 2D array
    String[][] responseArray = new String[responseLines-1][];  
    populateArray(responses, responseArray);
    for (String[] row : responseArray){
      for (String word : row) {
        System.out.print(word + " ");
      }
      System.out.println();
    }   
    // more cleaning data
    String[][] createScore = new String[responseLines-1][5];
    for (int i = 0; i < responseLines-1; i++){
      int num = 4;
      for (int j = 0; j < num; j++){
        // this is adding the response array to the score array
        createScore[i][j] = responseArray[i][j];
      }
    }
    ComparingAnswers compare = new ComparingAnswers();
    compare.compareAnswers(createScore, responseArray, ansArray);
  }
  
  /**
   * makes answer a 1D array, loads response, and cleans out unnecessary data
   * @param String file - reads the results from the file that the user wants to open
   * @param String[] studentDataArray - the array with student data
   * @throws IOException
   */
  public static void readResultForAns(String file, String[] studentDataArray) {
    BufferedReader br = null;
    int lineNum = 0;
    try {
      br = new BufferedReader(new FileReader(file));
      String contentLine = br.readLine();
      while (contentLine != null) {
        // skip the empty spaces between lines
        if (contentLine.length() == 0) {
          contentLine = br.readLine();
          continue;
        }
        // replaces the " " with "" in the file
        studentDataArray[lineNum] = contentLine.replace(" ", "");
        lineNum++;
        contentLine = br.readLine();
      }
    } catch (IOException e) {
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
   * @param String file specifically counts the 
   * lines of the file the user wants to open
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
   * @param String file - reads the results from the file 
   * that the user wants to open
   * @param String [][] data - the 2D array with the data
   * @throws IOException
   */
  public static void populateArray(String file, String[][] data) {
    BufferedReader br = null;
    int lineNum = 0;
    String delim = ",";

    try {
      br = new BufferedReader(new FileReader(file));
      // reads first line but doesn't print it
      br.readLine();
      String contentLine = br.readLine();
      // starts printing second line
      while (contentLine != null) {
        // Convert the string contentLine into an array
        data[lineNum] = contentLine.split(delim, -1);
        lineNum++;
        contentLine = br.readLine();
      }
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
  
  // @Override
  public void update(String file) {
    System.out.println("updated answers");
  }
}
