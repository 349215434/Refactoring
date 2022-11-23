import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class ComparingAnswers implements Observer {
  /**
   * Compares the answer of the array of the student's response and the answer
   *
   * @param createScore creates the score students get in their homework
   * @param studentResponseArray opens the student's response to the questions
   * @param answerArray is the actual answer key
   */
  public void compareAnswers(String[][] createScore, String[][] studentResponseArray, String[] answerArray) {
    System.out.println("\nComparing answers...\n");
    int mark = 0;
    for (int i = 0; i < studentResponseArray.length; i++) {
      // loops across the columns of a single row in the response
      for (int j = 0; j < studentResponseArray.length / 2; j++) {
        // after each question, program compares the student's results with the actual
        // answers
        // j+4 starts comparing the student's response on column 4
        // j*2+1 compares the to the answers instead of a1, a2, a3 (odd columns)
        if (studentResponseArray[i][j + 4].equals(answerArray[j * 2 + 1])) {
          mark++;
        }
        createScore[i][4] = Integer.toString(mark); // turn int to string
      }
      // resets the score for the next row
      mark = 0;
    }
    for (String[] line : createScore) {
      // prints out the individual words
      for (String word : line) {
        System.out.print(word + " ");
      }
      System.out.println();
    }
    
    WriteScore writeScore = new WriteScore();
    writeScore.writeScoreInNewFile(createScore);
  }

  // @Override
  public void update(String file) {
    System.out.println("updated answers");
  }
}
