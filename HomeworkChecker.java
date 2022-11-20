import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class HomeworkChecker {
 public static void main(String[] args) {
  Subject subject = new Subject();

   new StudentData(subject);
   new QuestionData(subject);
   new AnswerData(subject);
   new StudentResponse(subject);
   new EquationSolver(subject);
  
   System.out.println("hi");
 }
 
 
  /**
 * Meant to solve the equations but I did not get a chance to figure out how to do it (this is where the code would have gone). Instead it asks if you want to skip to just seeing the answers or get it to solve
 * @param N/A
 * @return 
 */
public static void solver(){
    Scanner reader = new Scanner(System.in);
    System.out.println("\nType in 'solve' inorder for the program to solve the equations for you or 'skip' to go directly to the answer key");
    String studentResponse = reader.nextLine();
  //if they do not say skip or solve this statement will print prompting them to enter something again (wont continue until either solve or skip is written)
    while(!studentResponse.equals("solve") && !studentResponse.equals("skip")){
      System.out.println("Your input is not valid please type either solve for the program to solve for you or skip inorder to go straight to the answers");
      studentResponse = reader.nextLine();
    }
  //since the solver is not complete it prints that out 
    if(studentResponse.equals("solve")){
      System.out.println("The Solver is still under construction. Try again some other time. ");
    }
    theAnswer();
  }
    
    /**
 * prints result of array 
 * @param String data is the file that is being opened
 * @return results
 */
   public static void printResults(String data) {
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
public static void theAnswer(){
    String answer = answerData();
    int answerLines = countingLines(answer);
    // this makes the answer into a 1D array because it will be easier to compare later this way
    String[] ansArray = new String[answerLines-1];
    ansResult(answer, ansArray);

    // prints out every element in the array individually
    for (String word : ansArray) {
      System.out.print(word + "  "); 
      System.out.println();
    }
    //this is loading the student responses again
    String responses = studentResponse();
    int responseLines = countingLines(responses);
    // response is a 2D array
    String[][] responseArray = new String[responseLines-1][];  
    populateArray(responses, responseArray);
    for (String[] row : responseArray){
      for (String word : row) {
        System.out.print(word+" ");
      }
      System.out.println();
    }   
    //more cleaning unneccisary data
    String[][] createScore = new String[responseLines-1][5];
    for (int i = 0; i < responseLines-1; i++){
      for (int j = 0; j < 4; j++){
        // this is adding the response array to the score array
        createScore[i][j] = responseArray[i][j];
      }
    }
    compareBothAnswers(createScore, responseArray, ansArray);
  }
    /**
 * counts the lines in the array
 * @param String file this specifically counts the lines of the file the user wants to open
 * @return line amount
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
 * @param opens the file 
 * @return file made into a array
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
        //when i got rid of the -1 after delim the index was going out of bounds therfore this keeps it in bounds :)
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

  public static String[][] studentsArr (String[][] studentData){
    int studentDataLength = studentData.length;
    String[][] studentArray = new String[studentDataLength][2]; 

    for (int i = 0; i < studentDataLength; i++){
      studentArray[i][0] = studentData[i][0];
      studentArray[i][1] = "0";
    }
    return studentArray;
  }
    /**
 * compares the student answers and the answers from the file
 * @param 2D array of the scores and 2D array of the student responses
 * @return  the scores if the answer was correct
 */
  public static void compareBothAnswers(String[][] createScore, String[][] responseArray, String[] ansArray) {
    System.out.println("\nComparing...\n");
    int mark = 0;
    for (int i = 0; i < responseArray.length; i++) {
      // loops across the columns of a single row in the response
      for(int j = 0; j < responseArray.length/2; j++){
        // program compares the student's answers with answer key answers
        // j+4 starts comparing the student's response on column 4
        // j*2+1 compares the to the answers instead of a1, a2, a3 (on the odd columns)
        if(responseArray[i][j+4].equals(ansArray[j*2+1])){
          mark++;
        }
        createScore[i][4] = Integer.toString(mark); //turn int to string
      } 
      //resets the score 
      mark = 0;
    }  
    for (String[] line : createScore) {
      for (String word : line) {
        System.out.print(word + " ");
      }
      System.out.println();
    }
    newFile(createScore);
  }
    /**
 * This is opening a new file with the results
 * @param 2D array of the scores (because thats the point of opening the file, for the score)
 * @return file with scores  
 */
  public static void newFile(String[][] createScore){
    try{
      FileWriter ResultFile = new FileWriter("Marked_Results.csv");
      ResultFile.write("Student Number, First Name, Last Name, Email, Grade\n");
      for(String[] row: createScore){
        for(String word: row){
          ResultFile.write(word + " ");
        }
        ResultFile.write("\n");
      }
      ResultFile.close();
      System.out.println("\nA new file has been opened containing the student marks");
    } 
    catch (IOException e) {
      System.out.println("There has been an error, please reload and try again.");
      e.printStackTrace();
    }
  }
}
