package com.example.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConnectionHandler {

    private static ConnectionHandler instance;

    private Connection con;

    String preparedSQLPerson = "insert into PERSON (id, name) values (?, ?)";
    String preparedSQLCity = "insert into ADRESSE (id, wohnort) values (?, ?)";

    ArrayList<Person> list = new ArrayList<>();

    private Statement statement;

    private void setCon(Connection connection) {
        con = connection;
    }

    public Connection getCon() {
        return con;
    }

    public static ConnectionHandler getInstance() {
        if (instance == null) {
            instance = new ConnectionHandler();
        }
        return instance;
    }

    public void startConnection() throws SQLException {
        setCon(DriverManager.getConnection("jdbc:derby://localhost:1527/localhost","SCHEMA","user"));
    }

    public void stopConnection() throws SQLException {
        con.close();
    }

    public ArrayList<Person> refresh() throws SQLException {

        list.clear();

        String statement = "select * from Person join Adresse using(id)";

        PreparedStatement st = con.prepareStatement(statement);

        ResultSet rs = st.executeQuery();

        while (rs.next()){

            Person p = new Person();
            p.setId(rs.getInt(1));
            p.setName(rs.getString(2));
            p.setWohnort(rs.getString(3));

            list.add(p);
        }

        return list;

    }

    public void insertInto(String s) throws SQLException {

        String[] values = s.split(",");

        PreparedStatement st = con.prepareStatement(preparedSQLPerson);
        PreparedStatement stc = con.prepareStatement(preparedSQLCity);
        con.setAutoCommit(false);

        System.out.println(values);

        st.setInt(1, Integer.parseInt(values[0]));
        st.setString(2, values[1]);

        stc.setInt(1,Integer.parseInt(values[0]));
        stc.setString(2,values[2]);

        int rs = st.executeUpdate();
        rs = stc.executeUpdate();
//        ResultSet rsc = stc.executeQuery();

//        while (rs.next()){
//            int id = rs.getInt(1);
//            String name = rs.getString(2);
//            System.out.println(id +  "\t" + name);
//        }

//        while (rsc.next()){
//            int id = rs.getInt(1);
//            String city = rs.getString(3);
//            System.out.println(id +  "\t" + city);
//        }

        con.commit();
    }

}
