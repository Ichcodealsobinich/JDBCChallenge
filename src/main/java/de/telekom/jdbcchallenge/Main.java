package de.telekom.jdbcchallenge;

import java.sql.*;


public class Main {

	public static void main(String[] args) {	
		/* Load driver // throws ClassNotFoundException*/
		try {
			final String DRIVER = "org.mariadb.jdbc.Driver";
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {}
		
		/* Establish connection using DriverManager */
		try (Connection connection = DriverManager
				.getConnection("jdbc:mariadb://localhost:3306/seadb?user=seauser&password=seapass");){			
				
			/*create the statement and execute it*/
			try (Statement statement = connection.createStatement();){
				
				String  query = "INSERT INTO personen "
					+ "(id, anrede, vorname, nachname) "
					+ "VALUES (3,0,'Paula', 'Paulsen')";
				statement.executeQuery(query);
				query = "SELECT * FROM personen";				
				try (ResultSet resultSet = statement.executeQuery(query);) {			
				
					/*iterate through the resultSet and print the data*/
					while (resultSet.next())  {
						System.out.println(resultSet.getString(1));
						System.out.println(resultSet.getString(2));
						System.out.println(resultSet.getString(3));
						System.out.println(resultSet.getString(4));
					}
				} catch (Exception e) {	}//resultSet.close() will be called automatically
			}catch (Exception e) { } //statement.close() will be called automatically					
		} catch (Exception e) {	} //connection.close() will be called automatically
	}
}
