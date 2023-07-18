package jdbcDemo;

import java.sql.*;

public class JDBCDemo {

	public static void main(String[] args) throws Exception {
		
//		insertRecords();
//		insertVar();
//		deleteRecords();
//		updateRecords();
//		readRecords();
		sp2();
	}
   
	//--------used to read records---------
	public static void readRecords() throws Exception {

		String url = "jdbc:mysql://localhost:3006/jdbcDemo";
		String userName = "root";
		String password = "Ssv@3003";
		
		String query = "select * from Employee";
		
      Connection con = DriverManager.getConnection(url, userName, password);
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(query);
      
      while(rs.next()) {
      
      System.out.println(" ID is " + rs.getInt(1));
      System.out.println(" Name is " + rs.getString(2));
      System.out.println(" Salary is " + rs.getFloat(3));
      }

      con.close();
	}
	
//------used to insert records manually by normal statement------------
	public static void insertRecords() throws Exception {

		String url = "jdbc:mysql://localhost:3006/jdbcDemo";
		String userName = "root";
		String password = "Ssv@3003";
		
		String query = "insert into Employee values (4,'priya',12000)";
		
      Connection con = DriverManager.getConnection(url, userName, password);
      Statement st = con.createStatement();
      int rows = st.executeUpdate(query);
      
      System.out.println("Number of rows added :"+ rows);
      con.close();
	}
	
//--------used to insert records by using prepared statements------------
	public static void insertVar() throws Exception {

		String url = "jdbc:mysql://localhost:3006/jdbcDemo";
		String userName = "root";
		String password = "Ssv@3003";
		
		int id = 6;
		String name = "Ramesh";
		int salary = 17000;
		
		String query = "insert into Employee values (?,?,?)";
		
      Connection con = DriverManager.getConnection(url, userName, password);
      PreparedStatement pst = con.prepareStatement(query);
      pst.setInt(1, id);
      pst.setString(2,name);
      pst.setInt(3, salary);
      int rows = pst.executeUpdate();
      
      System.out.println("Number of rows added :"+ rows);
      con.close();
	}
	
//------------used to delete records----------------
	
	public static void deleteRecords() throws Exception {

		String url = "jdbc:mysql://localhost:3006/jdbcDemo";
		String userName = "root";
		String password = "Ssv@3003";
		
		int id = 6;
		
		String query = "delete from Employee where emp_id= " + id;
		
      Connection con = DriverManager.getConnection(url, userName, password);
      Statement st = con.createStatement();
      int rows = st.executeUpdate(query);
      
      System.out.println("Number of rows deleted :"+ rows);
      con.close();
	}
	
//------------used to update a record by using query-----------------
	
	public static void updateRecords() throws Exception {

		String url = "jdbc:mysql://localhost:3006/jdbcDemo";
		String userName = "root";
		String password = "Ssv@3003";
		
		int id = 1;
		
		String query = "update employee set salary = 10000 where emp_id ="+id;
		
      Connection con = DriverManager.getConnection(url, userName, password);
      Statement st = con.createStatement();
      int rows = st.executeUpdate(query);
      
      System.out.println("Number of rows updated :"+ rows);
      con.close();
	}
	
//----------------used to execute a stored procedure in mysql using callable statement-------------
	
	public static void sp() throws Exception {

		String url = "jdbc:mysql://localhost:3006/jdbcDemo";
		String userName = "root";
		String password = "Ssv@3003";
		
		
      Connection con = DriverManager.getConnection(url, userName, password);
      CallableStatement cst = con.prepareCall("{call new_procedure()}");
      ResultSet rs = cst.executeQuery();
      
      while(rs.next()) {
      
      System.out.println(" ID is " + rs.getInt(1));
      System.out.println(" Name is " + rs.getString(2));
      System.out.println(" Salary is " + rs.getFloat(3));
      }

      con.close();
      
      
	}
	
//----------used to call stored procedure in mysql with in parameter------------------
	
	public static void sp1() throws Exception {

		String url = "jdbc:mysql://localhost:3006/jdbcDemo";
		String userName = "root";
		String password = "Ssv@3003";
		
		int id=3;
		
      Connection con = DriverManager.getConnection(url, userName, password);
      CallableStatement cst = con.prepareCall("{call getEmp(?)}");
      cst.setInt(1, id);
      ResultSet rs = cst.executeQuery();
      
      while(rs.next()) {
      
      System.out.println(" ID is " + rs.getInt(1));
      System.out.println(" Name is " + rs.getString(2));
      System.out.println(" Salary is " + rs.getFloat(3));
      }

      con.close();
      
      
	}
	
	
//----------used to call stored procedure in mysql with in and out parameter------------------
		
		public static void sp2() throws Exception {

			String url = "jdbc:mysql://localhost:3006/jdbcDemo";
			String userName = "root";
			String password = "Ssv@3003";
			
			int id=4;
			
	      Connection con = DriverManager.getConnection(url, userName, password);
	      CallableStatement cst = con.prepareCall("{call getNameByID(?,?)}");
	      cst.setInt(1, id);
	      cst.registerOutParameter(2, Types.VARCHAR);
	      cst.executeUpdate();
	      
	      System.out.println(cst.getString(2));
	      
	      con.close();
	      
	      
		}
}
