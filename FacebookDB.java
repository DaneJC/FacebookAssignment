/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Maria Boyle
 * Description: A JDBCFacebookDB class - Contains methods to work with the Facebook database
 * Date: 21/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class FacebookDB implements DBOperations{
	// JDBC database URL, user name and password
	private final String DB_URL = "jdbc:mysql://localhost/";
	private final String USER_NAME = "root";
	private final String PASSWORD = "password";

	private Connection conn;

	// Constructor
	public FacebookDB(){
		conn=null;
	}

	// createConnection() - Opens a connection to DB_URL
	public void createConnection(String dbUrl){
		try{
			// STEP 1 - Open a connection
			//          Use the DriverManager.getConnection() method to create a Connection object,
			//          which represents a physical connection with the database server.
			System.out.print("CONNECTING TO DATABASE.. ");
			conn = DriverManager.getConnection(dbUrl, USER_NAME, PASSWORD);
			System.out.println("SUCCESS - Connection obtained..");
		}
		catch (SQLException e) {
			System.out.println("FAILED - Cannot create connection.\n" + e.getMessage());
		}
	}

	// createDatabase() - Calls createConnection(), creates a database called facebook with a table called user
	public void createDatabase(){
		try{
			// createConnection() to localhost
			createConnection(DB_URL);

			// Create Statement object
			Statement stmt = conn.createStatement();
			System.out.println("COMPLETE - Statement object created...");

			// Execute Update to Create a Database called facebook
			String createDatabase = "CREATE DATABASE IF NOT EXISTS facebook";
			stmt.executeUpdate(createDatabase);
			System.out.println("COMPLETE - Update executed and facebook database created...");

			// Execute Update to Create a Table called user
			String createTable = "CREATE TABLE IF NOT EXISTS user " +
					"(emailaddress VARCHAR(24) not NULL, " +
					" password VARCHAR(18), " +
					" firstname VARCHAR(20), " +
					" lastname VARCHAR(20), " +
					" PRIMARY KEY (emailaddress))";

			stmt.executeUpdate("USE facebook");
			stmt.executeUpdate(createTable);
			System.out.println("COMPLETE - Update executed and user table added to facebook database...");

			// closeConnection()
			closeConnection();
		}
		catch (SQLException e){
			System.out.println("Problem with SQL.\n" + e.getMessage());
		}
	}

	// closeConnection() - Closes the connection
	public void closeConnection(){
		try{
			if(conn != null){
				System.out.print("CLOSING CONNECTING TO DATABASE.. ");
				conn.close();
				System.out.println("SUCCESS - Connection closed.");
			}
		}
		catch (SQLException e){
			System.out.println("FAILED - Could not close connection.\n" + e.getMessage());
		}
	}

	/* -- API-FacebookAssignment PART 2 [8%] -- */
	/** insertIntoDatabase() - creates statement object -> executes INSERT, UPDATE, or DELETE statements */
	public boolean insertIntoDatabase(String sqlString){  // sqlString = DML statement: INSERT, UPDATE or DELETE; or void DDL statement.

		boolean operationsComplete = true;
		createConnection(DB_URL);  // connect to the facebook database
		try{
			System.out.print("CREATING STATEMENT OBJECT.. ");
			Statement statement = conn.createStatement();  // create statement object
			System.out.println("SUCCESS - Object created.. ");
			try{
				System.out.print("EXECUTING SQL STATEMENT.. ");
            	statement.executeUpdate("USE facebook;");
				statement.executeUpdate(sqlString);  // Execute update statement
				System.out.println("SUCCESS - SQL statement executed.. ");
			}
			catch(SQLException e){
				System.out.println("FAILED - Problem with SQL: statement.executeUpdate(sqlString);.\n" + e.getMessage());
				operationsComplete = false;
			}
		}
		catch(SQLException e){
			System.out.println("FAILED - Problem with SQL: Statement stmt = conn.createStatement();.\n" + e.getMessage());
			operationsComplete = false;
		}
		closeConnection();  // Closes the connection

		// return to user signup button event with flag to create dialog for successful sign operation or not
		return operationsComplete;
	}

	/* -- API-FacebookAssignment PART 4 [18%] -- */
	/** getUserPasswordFromDatabase() - take an email address as a parameter ->
	 	connect to the facebook database -> query the database for all users with the
	 	required email address (there should be at most one) ->
	    extract and return the password for that email address. */
	public String getUserPasswordFromDatabase(String email){

		ResultSet resultSet = null;
		String password = "undetected";
		String sqlString = "SELECT `password` FROM facebook.user WHERE emailaddress = '"+email+"';";

		createConnection(DB_URL);  // connect to the facebook database
		try{
			System.out.print("CREATING STATEMENT OBJECT.. ");
			Statement statement = conn.createStatement();  // create statement object
			System.out.println("SUCCESS - Object created.. ");
			try{
				System.out.print("EXECUTING SQL EMAIL-PASSWORD SEARCH STATEMENT.. ");
				resultSet = statement.executeQuery(sqlString);
				System.out.println("SUCCESS - SQL statement executed.. ");
			}
			catch(SQLException e){
				System.out.println("FAILED - Problem with SQL: statement.executeUpdate(sqlString);.\n" + e.getMessage());
			}
		}
		catch(SQLException e){
			System.out.println("FAILED - Problem with SQL: Statement stmt = conn.createStatement();.\n" + e.getMessage());
		}

		// retrieve password if present
		try{
			if(resultSet.next())
				password = resultSet.getString("password");
			else {
				System.out.println("NO ACCOUNT FOUND!");

			}
		}
		catch(SQLException e){
			System.out.println("Problem with SQL: password = resultSet.getString(password);\n"+e.getMessage());
		}
		catch(NullPointerException e){
			System.out.println("Problem with SQL: password = resultSet.getString(password);\n"+e.getMessage());
		}
		System.out.println(password);
		closeConnection();  // Closes the connection
		return password;
	}
}