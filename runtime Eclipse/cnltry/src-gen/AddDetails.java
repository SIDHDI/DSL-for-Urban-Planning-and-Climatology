@SuppressWarnings("all")
public class AddDetails {
  private static String patName = "Sidhdi";
  
  private static String patId = "1";
  
  private static String patPhoneNo = "9403038876";
  
  private static int patAge = 21;
  
  private static String patEmail = "abc@fdjn";
  
  private static String patBloodGrp = "A+";
  
  private static String patAddress = "GHNEW";
  
  private static String patHeight = "5.3";
  
  private static String patWeight = "45";
  
  private static String patGender = "f";
  
  public String getPatName() {
    return this.patName;
  }
  
  public String getPatId() {
    return this.patId;
  }
  
  public String getPatPhoneNo() {
    return this.patPhoneNo;
  }
  
  public int getPatAge() {
    return this.patAge;
  }
  
  public String getPatEmail() {
    return this.patEmail;
  }
  
  public String getPatBloodGrp() {
    return this.patBloodGrp;
  }
  
  public String getPatAddress() {
    return this.patAddress;
  }
  
  public String getPatHeight() {
    return this.patHeight;
  }
  
  public String getPatWeight() {
    return this.patWeight;
  }
  
  public String getPatGender() {
    return this.patGender;
  }
  
  public void setPatName(final String patName) {
    this.patName = patName;
  }
  
  public void setPatId(final String patId) {
    this.patId = patId;
  }
  
  public void setPatPhoneNo(final String patPhoneNo) {
    this.patPhoneNo = patPhoneNo;
  }
  
  public void setPatAge(final int patAge) {
    this.patAge = patAge;
  }
  
  public void setPatEmail(final String patEmail) {
    this.patEmail = patEmail;
  }
  
  public void setPatBloodGrp(final String patBloodGrp) {
    this.patBloodGrp = patBloodGrp;
  }
  
  public void setPatAddress(final String patAddress) {
    this.patAddress = patAddress;
  }
  
  public void setPatHeight(final String patHeight) {
    this.patHeight = patHeight;
  }
  
  public void setPatWeight(final String patWeight) {
    this.patWeight = patWeight;
  }
  
  public void setPatGender(final String patGender) {
    this.patGender = patGender;
  }
  
//  public static void main(final String... args) {
//    try{
//    	java.sql.Connection myConn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_database", "root",
//    															"kartik");
//    	java.sql.Statement myStmt = myConn.createStatement();	
//    	String query = "insert into patient_details values" + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//    	java.sql.PreparedStatement preparedStmt = myConn.prepareStatement(query);
//    	preparedStmt.setString(1, patName);
//    	preparedStmt.setString(2, patId);
//    	preparedStmt.setInt(3, patAge);
//    	preparedStmt.setString(4, patPhoneNo);
//    	preparedStmt.setString(5, patEmail);
//    	preparedStmt.setString(6, patAddress);
//    	preparedStmt.setString(7, patBloodGrp);
//    	preparedStmt.setString(8, patHeight);
//    	preparedStmt.setString(9, patWeight);
//    	preparedStmt.setString(10, patGender);
//    	preparedStmt.execute();
//    	System.out.println("Patient details added Successfully");
//    	myStmt.close();	
//    	myConn.close();
//    								    } catch (Exception exc) {
//    		exc.printStackTrace();
//    }					
    
//  }
}
