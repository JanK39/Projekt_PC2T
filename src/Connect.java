import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	private Connection conn; 
	public boolean connect() { 
	       conn=null; 
	       try {
	           conn = DriverManager.getConnection("jdbc:sqlite:Databaze.db");
	       } 
		   catch (SQLException e) { 
			   System.out.println(e.getMessage());
		       return false;
		   }
		   return true;
	}
	
	public void disconnect() { 
		if (conn != null) {
		       try {
		    	   conn.close();
		       } 
	           catch (SQLException ex) { 
	        	   System.out.println(ex.getMessage());
	           }
		}
	}
	
	public boolean vytvorTable() {
		if (conn==null)
	           return false;
		
	    String sql = "CREATE TABLE IF NOT EXISTS Studenti(ID INTEGER PRIMARY KEY,"
	    		+ "Jmeno VARCHAR (255) NOT NULL,"
	    		+ "Prijmeni VARCHAR (255) NOT NULL,"
	    		+ "DatumNarozeni SMALLINT NOT NULL,"
	    		+ "Prumer FLOAT,"
	    		+ "Obor VARCHAR (2) NOT NULL);";
	    try{
	            Statement stmt = conn.createStatement(); 
	            stmt.execute(sql);
	            return true;
	    } 
	    catch (SQLException e) {
	    System.out.println(e.getMessage());
	    }
	    return false;

	}
	
	public void smazatZaznamy() {
		String com = "DELETE FROM Studenti;";
        try {
        	Statement stmt = conn.createStatement(); 
            stmt.execute(com);
        }
        catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
	}
	
	public void ulozStudenta(String jmeno, String prijmeni, int rok_narozeni, float prumer, String obor) {
		String com = "INSERT INTO Studenti(Jmeno,Prijmeni,DatumNarozeni,Prumer,Obor) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(com);
			pstmt.setString(1, jmeno);
			pstmt.setString(2, prijmeni);
			pstmt.setInt(3, rok_narozeni);
			pstmt.setFloat(4, prumer);
			pstmt.setString(5, obor);
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ResultSet getResultSetFor_nactiStudenta() {
		String com = "SELECT ID,Jmeno,Prijmeni,DatumNarozeni,Prumer,Obor FROM Studenti";
		ResultSet rs = null;
        try {
        	Statement stmt  = conn.createStatement();
            rs    = stmt.executeQuery(com);
        }
        catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return rs;
	}
	
	public Student_p nactiStudenta(ResultSet rs) {
		Student_p student = null;
        try {
            if (rs.next() == false) {
            	return null;
            }
            if (rs.getString("Obor").equals("TEL")) {
            	student = new Stud_tel(rs.getString("Jmeno"), rs.getString("Prijmeni"), rs.getInt("DatumNarozeni"));
            	student.setPrumer(rs.getFloat("Prumer"));
            }
            else if (rs.getString("Obor").equals("KYB")) {
            	student = new Stud_kyber(rs.getString("Jmeno"), rs.getString("Prijmeni"), rs.getInt("DatumNarozeni"));
            	student.setPrumer(rs.getFloat("Prumer"));
            }
        }
        catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
		return student;
	}
}
