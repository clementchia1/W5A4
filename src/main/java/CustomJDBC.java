import java.sql.*;
import java.util.Scanner;

public class CustomJDBC {

	public static void main(String[] args) throws SQLException {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/cinema_booking_system?serverTimezone=UTC";
		String user = "root";
		String pass = "password";

		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(dbUrl, user, pass);

			// 2. Create a statement
			myStmt = myConn.createStatement();
			String[] menu_array = new String[] { "Insert", "Delete", "Update", "View", "Quit" };

			for (short choice = 0; choice != 5;) {
				for (int i = 0; i < menu_array.length; i++) {
					System.out.println((i + 1) + ". " + menu_array[i]);
				}
				System.out.print("Please choose:");
				Scanner myObj = new Scanner(System.in);
				String input_str = myObj.nextLine();

				choice = input_str.compareTo("") != 0 ? Short.parseShort(input_str) : 0;

				switch (choice) {
				case 1:
					// insertsql(myStmt, myRs);
					break;
				case 2:
					// deletesql(myStmt, myRs);
					break;
				case 3:
					// updatesql(myStmt, myRs);
					break;
				case 4:
					// show(myStmt, myRs);
					show2(myStmt, myRs);
					break;
				case 5:
					System.out.println("Quitting...");
					Thread.sleep(2000);
					break;
				default:
					break;
				}
				myObj.nextLine();
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}
	}

	static void insertsql(Statement myStmt, ResultSet myRs) throws SQLException {
		// Insert a new employee
		show(myStmt, myRs);
		Scanner myObj = new Scanner(System.in);
		System.out.print("Last name:");
		String lastname = myObj.nextLine();
		System.out.print("First name:");
		String firstname = myObj.nextLine();
		System.out.print("email:");
		String email = myObj.nextLine();
		System.out.print("department:");
		String department = myObj.nextLine();
		System.out.print("salary:");
		int salary = Integer.parseInt(myObj.nextLine());

		System.out.println("Inserting a new employee to database\n");
		int rowsAffected = myStmt.executeUpdate(
				"insert into employees " + "(last_name, first_name, email, department, salary) " + "values " + "('"
						+ lastname + "', '" + firstname + "', '" + email + "', '" + department + "', " + salary + ")");

//			"insert into employees " +
//			"(last_name, first_name, email, department, salary) " + 
//			"values " + 
//			"('Chia', 'Clement', '2021534@cognizant.com', 'CDE PPS', 00001.00)");

		show(myStmt, myRs);
	}

	static void deletesql(Statement myStmt, ResultSet myRs) throws SQLException {
		show(myStmt, myRs);
		System.out.print("Delete by id:");
		Scanner myObj = new Scanner(System.in);
		int deleteid = Integer.parseInt(myObj.nextLine());

		int rowsAffected = myStmt.executeUpdate("delete from employees " + "where id=" + deleteid);
		show(myStmt, myRs);

	}

	static void updatesql(Statement myStmt, ResultSet myRs) throws SQLException {
		show(myStmt, myRs);
		System.out.print("edit by id:");
		Scanner myObj = new Scanner(System.in);
		int updateid = Integer.parseInt(myObj.nextLine());
		System.out.print("first name to change:");
		String updatefirstname = myObj.nextLine();

		int rowsAffected = myStmt.executeUpdate(
				"update employees " + "set first_name = " + "'" + updatefirstname + "'" + " where id= " + updateid);
		show(myStmt, myRs);
	}

	static void show(Statement myStmt, ResultSet myRs) throws SQLException {
		// 4. Verify this by getting a list of employees
		myRs = myStmt.executeQuery("select * from employees order by id");

		// 5. Process the result set
		System.out.println("\n");
		while (myRs.next()) {
			System.out.println(
					myRs.getString("id") + ". " + myRs.getString("last_name") + ", " + myRs.getString("first_name"));
		}
	}

	static void show2(Statement myStmt, ResultSet myRs) throws SQLException {
		// 4. Verify this by getting a list of employees
		myRs = myStmt.executeQuery("Show tables");

		// 5. Process the result set 
	      while(myRs.next()) {
	         System.out.print(myRs.getString(1));
	         System.out.println();
	      }
	}

}
