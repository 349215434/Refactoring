public class AnswerData extends Observer { 
  
  //Attributes
  String answerFile;
  File checkFile; 
  
  //Methods
  void String takeInput() {
    Scanner reader = new Scanner(System.in);
    System.out.println("Type in the file name containing the answer");
    String answerFile = reader.nextLine();
    
  void checkExists() {
  
