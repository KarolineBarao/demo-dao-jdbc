package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
    private static Connection conn=null;
    
    public static Connection conexaoDB(){
        
        
        
        try {
            
            String url = "jdbc:mysql://localhost:3306/bancobk?user=root&password=rootbr22";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        
        return conn;                
              
    }
    
      public static void closeConnection(){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw  new DbException(e.getMessage());
            }
        }
    }
      
      
    private static Properties carregarPropriedades(){
        
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            
            throw new DbException(e.getMessage());
        }        
    }
    
    public static void closeStatement(Statement st){
            if(st != null){
                try {
                    st.close();
                } catch (SQLException ex) {
                    throw new DbException(ex.getMessage());
                }
            }
    }
    
    public static void closeResultSet(ResultSet rs){
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw new DbException(ex.getMessage());
                }
            }
    }
     
     
    
}
