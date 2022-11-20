public class AnswerData extends Observer { 
  
  //Attributes
  String answerFile;
  File checkFile; 
  
  //Methods
  void String takeInput() {
    Scanner reader = new Scanner(System.in);
    System.out.println("Type in the file name containing the answer");
    String answerFile = reader.nextLine();
  }
    
  void checkFile() {
    File checkFile = new File(answerFile);
    while (!checkFile.exists()) {
      System.out.println("\nThis file does not exist please try again.");
      answerFile = reader.nextLine().toLowerCase();
      checkFile = new File(answerFile);    
    }
    if(checkFile.exists()){
      System.out.println("Finding file for you");
      System.out.println("Please wait as it loads");
    }
    return answerFile;
  }
    @Override
  public void update() {
    System.out.println("updated answerData"); 
  }
}
