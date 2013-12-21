package com.maktashaf.taymiyyah.repository.jdbc.factory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author: Haroon
 */
public class ConnectionFactory {
  private static Logger logger = Logger.getLogger(ConnectionFactory.class);
  private static ConnectionFactory connectionFactory;

  private ConnectionFactory(){

  }

  public static ConnectionFactory getInstance(){
    if(connectionFactory == null){
      synchronized(ConnectionFactory.class){
        if(connectionFactory == null){
          connectionFactory = new ConnectionFactory();
        }
      }
    }
    return connectionFactory;
  }

  public Connection getConnection(){
    Connection connection = null;
    try {
      Properties properties = loadProperties();
      final String dbUrl = properties.getProperty("dbUrl");
      final String driver = properties.getProperty("driver");
      final String username = properties.getProperty("username");
      final String password = properties.getProperty("password");
      Class.forName(driver);

      connection = DriverManager.getConnection(dbUrl, username, password);

    } catch(Exception e){
      e.printStackTrace();
      logger.error(e.getMessage());
    }
    return connection;
  }

  private Properties loadProperties(){
    Properties properties = new Properties();

    try{
      InputStream is = getClass().getResourceAsStream("/repository.properties");
      properties.load(is);
      is.close();
    }catch(Exception e){
      e.printStackTrace();
      logger.error(e.getMessage());
    }

    return properties;
  }
}
