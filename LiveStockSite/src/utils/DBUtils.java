package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import general.*;


public class DBUtils {

	private static final int CUSTOMER = 1;
	private static final int REP = 2;
	private static final int MANAGER = 3;

	public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException {
		System.out.println("Loooking for user: " + userName);
		String sql = "SELECT L.Usr, L.Pwd, L.AccType, L.Id FROM Login L "
				+ " WHERE BINARY L.Usr = ? AND BINARY L.Pwd = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			System.out.println("Found someone!");
			String usr = rs.getString("Usr");
			String pwd = rs.getString("Pwd");
			int accType = rs.getInt("AccType");
			int id = rs.getInt("Id");

			if(accType == CUSTOMER)
			{	
				System.out.println("CUSTOMER!");
				String sql2 = "SELECT C.FirstName, C.LastName, "
							+ "C.Address, C.City, C.State, "
							+ "C.ZipCode, C.Telephone, C.Email, "
							+ "C.Rating, C.CusId FROM Customer C "
							+ "WHERE C.CusId = ?";
				PreparedStatement pstm2 = conn.prepareStatement(sql2);
				pstm2.setInt(1, id);
				ResultSet rs2 = pstm2.executeQuery();
				
				if(rs2.next())
				{
					String first = rs2.getString("FirstName");
					String last = rs2.getString("LastName");
					String addr = rs2.getString("Address");
					String city = rs2.getString("City");
					String state = rs2.getString("State");
					String zip = rs2.getString("ZipCode");
					String phone = rs2.getString("Telephone");
					String email = rs2.getString("Email");
					int rating = rs2.getInt("Rating");
					int cusId = rs2.getInt("CusId");
					
					UserAccount user = new CustomerAccount(cusId, first,
										last, usr, pwd, addr, city, state,
										zip, phone, email, rating);
					System.out.println("Retrieved Customer Account");
					return user;
				}
			}
			else if(accType == REP || accType == MANAGER)
			{	
				System.out.println("EMPLOYEE!");
				String sql2 = "SELECT E.SSN, E.FirstName, E.LastName, "
						+ "E.Address, E.City, E.State, "
						+ "E.ZipCode, E.Telephone, E.HourlyRate, "
						+ "E.StartDate, E.EmpId, E.Position_ FROM Employee E "
						+ "WHERE E.EmpId = ?";
				PreparedStatement pstm2 = conn.prepareStatement(sql2);
				pstm2.setInt(1, id);
				ResultSet rs2 = pstm2.executeQuery();
				
				if(rs2.next())
				{
					UserAccount user = new EmployeeAccount();

					user.setUsername(usr);
					user.setPassword(pwd);
					((EmployeeAccount)user).setSsn(rs2.getString("SSN"));
					user.setLname(rs2.getString("LastName"));
					user.setFname(rs2.getString("FirstName"));
					user.setAddress(rs2.getString("Address"));
					user.setCity(rs2.getString("City"));
					user.setState(rs2.getString("State"));
					user.setZip(rs2.getString("ZipCode"));
					user.setPhone(rs2.getString("Telephone"));
					((EmployeeAccount)user).setStart(rs2.getTimestamp("StartDate"));
					((EmployeeAccount)user).setHourly(rs2.getInt("HourlyRate"));
					user.setId(rs2.getInt("EmpId"));
					((EmployeeAccount)user).setPos(rs2.getString("Position_"));
					
					System.out.println("Retrieved Employee Account");
					return user;
				}
			}
			else
			{
				return null;
			}
		}
		return null;
	}
	/** TO DO
	public static void insertCustomer(Connection conn, CustomerAccount customer) throws SQLException {
		String sql = "Insert into Customer(Code, Name,Price) values (?,?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, product.getCode());
		pstm.setString(2, product.getName());
		pstm.setFloat(3, product.getPrice());

		pstm.executeUpdate();
	}
	*/
}