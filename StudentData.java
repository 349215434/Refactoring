public class StudentData extends Observer{

  // Properties or Attributes
  String studentFile;
  File checkFile;

  // Constructor: A method that hasa the same name as the class
  /*
  public Pokemon(String name){
   this.name = name;
   this.HP = 10;    // All pokemon will have a default of 10hp
  }*/

  // Methods
  void String takeInput() {
    Scanner reader = new Scanner(System.in);
    System.out.println("Type in the Student Data file name: ");
    studentFile = reader.nextLine();
  }
  
  void checkExist() {
    File checkFile = new File(Studentfile);
    // if the file does not exist
    while (!checkFile.exists()) {
      System.out.println("\nThis file does not exist please try again.");
      studentFile = reader.nextLine();
      checkFile = new File(studentFile);    
    }
    // if the file exists
    if(checkFile.exists()){
      System.out.println("Finding file for you");
      // prints and returns the student file
      printResults(studentFile);
      return studentFile;
    }
  }
