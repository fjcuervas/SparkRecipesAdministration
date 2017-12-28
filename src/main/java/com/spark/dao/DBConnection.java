package com.spark.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class used to connect to the database. The information of the connexion is 
 * extracted from a properties file
 * 
 * @author Javi Cuervas
 * @version 1.0 December 2017
 * */
public class DBConnection {

	protected static Connection connection;

	public static Connection connect() {

		if (connection != null) {
			return connection;
		}

		InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
		Properties properties = new Properties();

		try {
			properties.load(inputStream);
			
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			
			Class.forName(driver);
			
			connection = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return connection;
	}

}
