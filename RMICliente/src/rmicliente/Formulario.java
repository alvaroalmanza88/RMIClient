/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmicliente;


import com.sun.istack.internal.Pool;
import rmicliente.Login;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Formulario {
	
    Pool metodospool = new Pool() {
        @Override
        public Object take() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void recycle(Object t) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    public int validar_ingreso(){
    
    String usuario = Login.txtUsuario.getText();
    String clave = String.valueOf(Login.jpassClave.getPassword());
    
    int resultado=0;
    
    String SSQL="SELECT * FROM usuarios WHERE usuario='"+usuario+"' AND clave=sha1('"+clave+"')";
    
    connection conect = null;
    
    try{
        conect = metodospool.dataSource.getConnection();
        Statement st = conect.createStatement();
        ResultSet rs = st.executeQuery(SSQL);
        
        if(rs.next()){
            
            resultado=1;
        }
        
    } catch (SQLException ex){
        JOptionPane.showMessageDialog(null, ex, "Error de conexion", JOptionPane.ERROR_MESSAGE);
        
    }finally{
        
        try {
            conect.close();
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
            
        }
    }
        return resultado;
    }
}
 

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    