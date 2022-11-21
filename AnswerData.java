import java.util.Scanner;
import java.io.File;

public class AnswerData extends Observer { 
  
  //Attributes
  String answerFile;
  File checkFile; 

  // Constructor: A method that has the same name as the class
  public answerData(Subject subject){
    this.subject = subject;
    //this.subject.attach(this);
  }
  
  //Methods
String takeInput() {
    Scanner reader = new Scanner(System.in);
    System.out.println("Type in the file name containing the answers");
    String answerFile = reader.nextLine();
    return answerFile;
  }
    
  void checkFile() {
    checkFile = new File(answerFile);
    while (!checkFile.exists()) {
      System.out.println("\nThis file does not exist please try again.");
      takeInput();
      checkFile = new File(answerFile);    
    }
    if(checkFile.exists()){
      System.out.println("Finding file for you");
      System.out.println("Please wait as it loads");
    }
  }
    @Override
  public void update() {
    System.out.println("updated answerData"); 
  }
}
