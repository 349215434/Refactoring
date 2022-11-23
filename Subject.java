import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Subject {

  private ArrayList<Observer> observers = new ArrayList<Observer>();

  public void registerObserver(Observer observer) {
    observers.add(observer);
  }

  /**
   * takes the user's input and makes sure the file exist
   * @param String event
   * @return studentResponse;
   */
  String takeInput(String prompt) {
    Scanner reader = new Scanner(System.in);
    System.out.println(prompt);
    String studentResponse = reader.nextLine().toLowerCase();
    // check if the file exist
    File checkFile = new File(studentResponse);
    while (!checkFile.exists()) {
      System.out.println("You entered: " + studentResponse);
      System.out.println("This file does not exist. Please try another file\n");
      studentResponse = reader.nextLine().toLowerCase();
      checkFile = new File(studentResponse);
    }
    System.out.println("You entered: " + studentResponse + "\n");
    
    notifyAllObservers(studentResponse);
    return studentResponse;
  }

  /**
   * notifies all the observers of the inputs
   * @param String event
   */
  void notifyAllObservers(String event) {
    if (event.contains("student_data")) {
      StudentData open = new StudentData();
      open.printResults(event);
    } else if (event.contains("_q_")) {
      QuestionData openQuesFile = new QuestionData();
      openQuesFile.printResults(event);
    } else if (event.contains("_a_")) {
      AnswerData openAnsFile = new AnswerData();
      openAnsFile.printResults(event);
    } else if (event.contains("response")) {
      StudentResponse openResponse = new StudentResponse();
      openResponse.printResults(event);
    }
      
    for (Observer observer : observers) {
      observer.update(event);
    }
  }
}
