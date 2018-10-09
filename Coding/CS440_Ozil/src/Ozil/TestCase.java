package Ozil;




public class TestCase implements Serializable {
 
  private String testCaseTitle;
  private String testCaseDescription;
  private String authorName;
  private int authorID;
  //Date of Create variable
  //etc...
  
  
  
  public TestCase(String TestCaseTitle,int authorId,String Name)
  {
    testCaseTitle = TestCaseTitle;
    authorID = authorId;
    authorName = Name;
  }
  
  
  
  
}
