public class StudentResponse extends Observer {

  // Properties or Attributes
  String studentResponse;
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
   System.out.println("\nType in the student response file name (include the file extension)");
   studentResponse = reader.nextLine().toLowerCase();
  }
  
  void checkExist() {
   checkFile = new File(studentResponse);
   while (!checkFile.exists()) {
     System.out.println("You entered: " + studentResponse);
     System.out.println("This file does not exist. Please try another file\n");
     studentResponse = reader.nextLine().toLowerCase();
     checkFile = new File(studentResponse);
   }

   while (!studentResponse.contains("response")) {
     System.out.println("You entered: " + studentResponse);
     System.out.println("Cannot open this file. Please try another file\n");
     inputStudentResponse = reader.nextLine().toLowerCase();
     checkFile = new File(studentResponse);
   }
   System.out.println("You entered: " + studentResponse + "\n");

    return studentResponse;
  }
}
