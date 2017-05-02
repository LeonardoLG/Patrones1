/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcdataaccessaplication;
import java.sql.Connection;
import java.sql.DriverManager;
import oracle.jdbc.OracleDriver;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author F20LAB303-XXE
 */
public class JDBCDataAccessClass {
    private Connection _connection;
    
    public void initialize(){ 
        try {
            DriverManager.registerDriver(new OracleDriver());
            _connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","hr","hr");

        } catch (SQLException e) {
        System.out.println("Error al registrar el controlador"+ e.getMessage());
        }
    }
    
    public int listStaff(){
        Statement statement = null;
        try {
            statement = _connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT FIRST_NAME, LAST_NAME, EMPLOYEE_ID FROM EMPLOYEES");
            int count = 0;
            while (resultSet.next()) {
                count++;
                //System.out.println(resultSet.getString(1)+" "+resultSet.getString(2) +" "+resultSet.getInt(3));
            }
            return count;
        }catch (SQLException e) {
            System.out.println("Error crear la sentencia "+ e.getMessage());
        }
        return 0;
    }
    //Crear y LLenar ArrayList 
    public ArrayList<Empleado> Lista(){
        ArrayList<Empleado> LE = new ArrayList<Empleado>();
        Statement statement = null;
        Empleado E;
        try {
            statement = _connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT FIRST_NAME, LAST_NAME, EMPLOYEE_ID FROM EMPLOYEES");
            while (resultSet.next()){
                E = new Empleado(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3)); 
                LE.add(E);
                
            }
        }catch (SQLException e) {
            System.out.println("Error crear la sentencia "+ e.getMessage());
        }
        return LE; 
    }
    
    /*public static void main(String[] args){
        JDBCDataAccessClass jdbc = new JDBCDataAccessClass();
        jdbc.initialize();
        int empCount = jdbc.listStaff();
        System.out.println("El total de empleado es: " + empCount);
    }*/
}   