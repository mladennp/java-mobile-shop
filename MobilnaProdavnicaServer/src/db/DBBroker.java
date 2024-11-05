/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;


import domen.AbstractDomainObject;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;





public class DBBroker {
   
    public static DBBroker instance;
    private Connection connection;
    
    
    private DBBroker() {
          try {
                Properties properties = new Properties();
                properties.load(new FileInputStream("dbconfig.properties"));
                String url = properties.getProperty("url");
                String username = properties.getProperty("username");
                String password = properties.getProperty("password");
                connection= DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
                System.out.println("uspesno konekcija!");
            } catch (Exception ex) {
                System.out.println("nije uspesno povezano");
                ex.printStackTrace();
                
            }       
            
    }

    public static DBBroker getInstance() {
         if(instance==null){
                instance= new DBBroker();
         }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
        
      
        public ArrayList<AbstractDomainObject> select(AbstractDomainObject ado) throws SQLException {
        String upit = "SELECT * FROM " + ado.nazivTabele() + " " + ado.alijas()
                + " " + ado.join() + " " + ado.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.vratiListu(rs);
    }

    public PreparedStatement insert(AbstractDomainObject ado) throws SQLException {
        String upit = "INSERT INTO " + ado.nazivTabele() + " "
                + ado.koloneZaInsert() + " VALUES (" + ado.vrednostiZaInsert() + ")";
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit,Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        System.out.println("Uspesno ubaceno u bazu");
        return ps;
    }

    public void update(AbstractDomainObject ado) throws SQLException {
        String upit = "UPDATE " + ado.nazivTabele() + " SET "
                + ado.vrednostiZaUpdate() + " WHERE " + ado.vrednostZaPrimarniKljuc();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }

    public void delete(AbstractDomainObject ado) throws SQLException {
        String upit = "DELETE FROM " + ado.nazivTabele() + " WHERE " + ado.vrednostZaPrimarniKljuc();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }


    }
    

