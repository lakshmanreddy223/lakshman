package com.icm.pogeneration.util;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	public static Connection getConnection() {

		Properties props = new Properties();
		FileInputStream fis = null;
		Connection con = null;
		
		try {
			URL resource = DBConnection.class.getClassLoader().getResource("db.properties");
			fis = new FileInputStream(resource.getFile().replace("%20", " "));
			System.out.println(resource.getFile());
			props.load(fis);

			// load the Driver Class
			Class.forName(props.getProperty("DB_DRIVER_CLASS"));

			// create the connection now
			con = DriverManager.getConnection(props.getProperty("DB_URL"),
					props.getProperty("DB_USERNAME"),
					props.getProperty("DB_PASSWORD"));
			initPOFormat();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
    }

	// close all resources
	public static void closeResources(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
		try {
			if (resultSet != null && resultSet.isClosed() == false) {
				resultSet.close();
			}
			if (preparedStatement != null
					&& preparedStatement.isClosed() == false) {
				preparedStatement.close();
			}
			if (connection != null && connection.isClosed() == false) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void initPOFormat(){
		try {
			Properties props = new Properties();
			FileInputStream fis = null;
			URL resource = DBConnection.class.getClassLoader().getResource("po.properties");
			fis = new FileInputStream(resource.getFile().replace("%20", " "));
			System.out.println(resource.getFile());
			
			props.load(fis);
			
			
			POProperties.setCompanyname(props.getProperty("COMPANYNAME"));
			POProperties.setCompanyname2(props.getProperty("COMPANYNAME2"));
			POProperties.setCin(props.getProperty("CIN"));
			POProperties.setCompanystamp(props.getProperty("COMPANYSTAMP"));
			POProperties.setCompanystamp1(props.getProperty("COMPANYSTAMP1"));
			POProperties.setTermandcondition(props.getProperty("TERMANDCONDITION"));
			POProperties.setAddress1(props.getProperty("ADDRESS1"));
			POProperties.setAddress2(props.getProperty("ADDRESS2"));
			POProperties.setSignature_message(props.getProperty("SIGNATURE_MESSAGE"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
