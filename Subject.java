import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Subject {
	
   private ArrayList<Observer> observers = new ArrayList<Observer>();

   public void registerObserver(Observer observer){
      observers.add(observer);		
   }

  void takeInput() {
    Scanner reader = new Scanner(System.in);
    System.out.println("\nType in the student response file name (include the file extension)");
    String studentResponse = reader.nextLine().toLowerCase();
    checkExist(studentResponse);
    notifyAllObservers(studentResponse);

  }
  
  void checkExist(String studentResponse) {
    File checkFile = new File(studentResponse);
    while (!checkFile.exists()) {
     System.out.println("You entered: " + studentResponse);
     System.out.println("This file does not exist. Please try another file\n");
     takeInput();
     checkFile = new File(studentResponse);
   }

    while (!studentResponse.contains("response")) {
      System.out.println("You entered: " + studentResponse);
      System.out.println("Cannot open this file. Please try another file\n");
      takeInput();
      checkFile = new File(studentResponse);
     }
     System.out.println("You entered: " + studentResponse + "\n");
  }
  
  public void notifyAllObservers(String event){
    for (Observer observer : observers) {
       observer.update(event);
    }
  }//sends to all, find a way that only the one observer does something w it  	
}
