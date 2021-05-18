package de.telekom.jdbcchallenge;

import java.sql.*;

public class Main {
	public static void main(String[] args) {	
		/* Load driver // throws ClassNotFoundException*/
		try {
			final String DRIVER = "org.mariadb.jdbc.Driver";
			Class.forName(DRIVER);
		} catch (Exception e) {e.printStackTrace();}
		
		/* Establish connection using DriverManager */
		try (Connection connection = DriverManager
				.getConnection("jdbc:mariadb://localhost:3306/seadb?user=seauser&password=seapass");){			
				
			/*create a preparedStatement for writing and execute it*/
			String sql = "INSERT INTO personen ( ID, ANREDE, VORNAME, NACHNAME) VALUES ( ?, ?, ?, ? )";
			try (PreparedStatement ps = connection.prepareStatement(sql);){
				ps.setString(1, "1");
				ps.setString(2, "1");
				ps.setString(3, "Paul");
				ps.setString(4, "Paulsen");
				ps.executeQuery();
				
				/*create a "normal" statement for reading*/
				String query= "SELECT * FROM personen";					
				try(Statement statement = connection.createStatement()) {
					try (ResultSet resultSet = statement.executeQuery(query);) {			
						
						/*iterate through the resultSet and print the data*/
						while (resultSet.next())  {
							System.out.println(resultSet.getLong(1));
							System.out.println(resultSet.getShort(2));
							System.out.println(resultSet.getString(3));
							System.out.println(resultSet.getString(4));
						}
					} catch (Exception e) {	} //resultSet.close() will be called automatically				
				}catch (Exception e) { } //statement.close() will be called automatically
			}catch (Exception e) {e.printStackTrace();} //preparedStatement.close() will be called automatically
		} catch (Exception e) {	} //connection.close() will be called automatically
	}
}
