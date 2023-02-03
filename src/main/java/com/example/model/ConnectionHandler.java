package com.example.model;

import javax.sql.StatementEvent;
import java.sql.*;

public class ConnectionHandler {

    private static ConnectionHandler instance;

    private Connection con;

    private Statement statement;

    public void setCon(Connection connection){
        con = connection;
    }

    public static ConnectionHandler getInstance(){
        if(instance == null){
            instance = new ConnectionHandler();
        }
        return instance;
    }

    public void startConnection() throws SQLException {
        setCon(DriverManager.getConnection("jdbc:derby://localhost:1527/localhost","user","user"));
    }

    public void stopConnection(){

    }

    public void insertInto(){

    }

}
