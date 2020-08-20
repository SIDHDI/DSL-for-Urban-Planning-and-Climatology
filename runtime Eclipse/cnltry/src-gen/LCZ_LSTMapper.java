
public class LCZ_LSTMapper {
	
	private int LCZ;
	private float minTemp;
	private float maxTemp;
	private String avgTemp;
	
	public void getLST()
	{
		try
		{
			final String URL = "jdbc:mysql://localhost/dataDB";
			final String USER = "root";
			final String PASSWORD = "password";
			
			java.sql.Connection myConn = java.sql.DriverManager.getConnection(URL, USER,
														PASSWORD);
			java.sql.Statement myStmt = myConn.createStatement();	
			java.sql.ResultSet myRs = myStmt.executeQuery("Select * from LCZ_LST where LCZ =" + "'" + LCZ + "'");
			
			if (myRs.next())
			{
				setMinTemp(myRs.getFloat("minTemp"));
				setMaxTemp(myRs.getFloat("maxTemp"));
				setAvgTemp(myRs.getString("avgTemp"));
			}
			else 
			{
				System.out.println("LCZ not found");	
			}	
			myStmt.close();	
			myConn.close();
		} 
		catch (Exception exc) 
		{
			exc.printStackTrace();
		}				
	}
	
	public int getLCZ() {
		return LCZ;
	}
	public void setLCZ(int lCZ) {
		LCZ = lCZ;
	}
	public float getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(float minTemp) {
		this.minTemp = minTemp;
	}
	public float getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(float maxTemp) {
		this.maxTemp = maxTemp;
	}
	
	public String getAvgTemp() {
		return avgTemp;
	}

	public void setAvgTemp(String avgTemp) {
		this.avgTemp = avgTemp;
	}

	public LCZ_LSTMapper(int l)
	{
		LCZ = l;
	}

}
