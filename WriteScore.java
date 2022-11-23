import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class WriteScore implements Observer {
  /**
   * Writes the score in a new file
   *
   * @param createScore is the score students get in their homework
   */
  public void writeScoreInNewFile(String[][] createScore) {
    try {
      FileWriter studentResultFile = new FileWriter("student_mark_result.csv");
      studentResultFile.write("Student Number, First Name, Last Name, Email, Grade\n");
      for (String[] row : createScore) {
        for (String word : row) {
          studentResultFile.write(word + " ");
        }
        studentResultFile.write("\n");
      }
      studentResultFile.close();
      System.out.println("\nMarks were written to a new file.");
    } 
    catch (IOException e) {
      System.out.println("There has been an error, please reload and try again.");
      e.printStackTrace();
    }
  }
  
  // @Override
  public void update(String file) {
    System.out.println("updated answers");
  }
}
