import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

class Main {
  public static void main(String[] args) {
    Subject subject = new Subject();
    subject.takeInput("Type in the student data file (include the file extension)");
    subject.takeInput("\nType in the file containing the questions (include the file extension)");
    OpenAnswer answer = new OpenAnswer();    
    answer.chooseSolveOrSkip();
  }
}
