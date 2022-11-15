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
 }
}
