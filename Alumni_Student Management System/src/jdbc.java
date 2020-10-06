import java.sql.*;
public class jdbc {
	public static void main(String[] args) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/sdldatabase","root","123654");
		
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery("select * from student");
		while(rs.next())
		{
		System.out.print(rs.getString(1)+" ");
		System.out.print(rs.getString(2)+" ");
		System.out.print(rs.getString(3)+" ");
		System.out.print(rs.getString(4)+" ");
		System.out.print(rs.getString(5)+" ");
		System.out.print(rs.getString(6)+" ");

		System.out.println("\n");
		}
		rs.close();
		st.close();
		con.close();
	}
	
	//creating student account
	
	
			//creating alumni account
			

			//creating an internship
			
			
			//verifying the student sign-in
			
			
			//verifying the alumni sign-in
			
			
			//select all alumni
			
			
			//select all list of internships

			//print out

}
