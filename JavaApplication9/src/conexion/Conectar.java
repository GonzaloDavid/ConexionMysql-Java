
package conexion;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conectar {
    //NO OLVIDAR CONFIGURAR SERVICES EN MYSQL PROBANDO EL TEST DE CONEXION Y AÑADIR MYSQL-JDBC DIRECTO DESDE MYSQL
    Connection con=null;
    public Connection Conexion(){
        try{
           //cargar driver
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/bolsaempleo","root","david");
            System.out.println("Coneccion establecida");
           
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("Error de coneccion");
            JOptionPane.showMessageDialog(null, "Error de coneccion"+e);
        }
        return con;
    }
    
    public ResultSet Consulta(String consulta){
        Conexion();
        Statement declara;
        try{
            declara = con.createStatement();
            ResultSet respuesta= declara.executeQuery(consulta);
            return respuesta;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error:"+ e.getMessage(),"Error de Conecion",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public void EjecutarSQL(String orden) {
        Conexion();
        Statement declara;
        try{
           declara= con.createStatement(); 
           declara.execute(orden);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error:"+ e.getMessage(),"Error de Conecion",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
      public static void main(String[] args) {
        // TODO code application logic here
        Conectar conectar=new Conectar();
        ResultSet consulta=conectar.Consulta("select *from artesano");
       try{
            while(consulta.next()){
                
                System.out.println(consulta.getString(4));
                 
            }    
        }catch(SQLException e){   
            System.out.println("hay error en el while");
        }   

        
       
    }
}
