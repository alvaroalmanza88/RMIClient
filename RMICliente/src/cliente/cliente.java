/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmiserver.*;

/**
 *
 * @author Usuario
 */
public class cliente {
       

    public static void main(String[] args) throws NotBoundException {
        String usuario = "";
        String pass = "";
        int opcion = 0;
        Registry miRegistro;
        RmiInterface s;
        boolean continuar = false;
        
        
        try {
            miRegistro = LocateRegistry.getRegistry("127.0.0.1", 1023);
            s = (RmiInterface) miRegistro.lookup("Tienda");
            
            Scanner reader = new Scanner(System.in);
        
            while (!continuar)
            {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.print("Introduzca su usuario (email): ");
                usuario = reader.next();
                System.out.print("Introduzca su contraseña: ");
                pass = reader.next();
                continuar = s.doUserLogin(usuario, pass);
            }
            
            while (continuar)
            {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("1 - Nuevo usuario.");
                System.out.println("2 - Insertar producto.");
                System.out.println("3 - Borrar producto.");
                System.out.println("4 - Mostrar productos.");
                System.out.println();
                System.out.print("Introduzca una opción:");
                opcion = reader.nextInt();
                
                switch (opcion)
                {
                    case 0:
                        continuar = false;
                        break;                    
                    case 1:
                        nuevoUsuario(s, reader);
                        break;
                    case 2:
                        nuevoProducto(s, reader);
                        break;
                    case 3:
                        borrarProducto(s, reader);
                        break;
                    case 4:
                        mostrarProductos(s, reader);
                        break;
                    default:
                        break;
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    /**
     *
     * @param s
     * @param reader
     */
    public static void nuevoUsuario (RmiInterface s, Scanner reader)
    {
        try {
            String nombre;
            String email;
            String email2;
            String pass;
            String telefono;
            String respuesta;
            
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.print("Introduzca su nombre: ");
            nombre = reader.next();
            System.out.print("Introduzca su telefono: ");
            telefono = reader.next();
            System.out.print("Introduzca su email: ");
            email = reader.next();
            System.out.print("Vuelva a introducir su email: ");
            email2 = reader.next();
            System.out.print("Introduzca su contraseña: ");
            pass = reader.next();
            
            respuesta = s.registerNewUser(nombre, pass, email, email2, telefono);
            
            if (respuesta.equals("200"))
            {
                System.out.println();
                System.out.println("USUARIO INSERTADO");
            }
            else
            {
                System.out.println();
                System.out.println(respuesta);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param s
     * @param reader
     */
    public static void nuevoProducto(RmiInterface s, Scanner reader)
    {
try {
            String id;
            String producto;
            String precio;
            String respuesta;
            
            
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.print("Introduzca el producto: ");
            producto = reader.next();
            System.out.print("Introduzca su precio: ");
            precio = reader.next();
            
            respuesta = s.registerNewProduct(producto, precio);
            
            if (respuesta.equals("200"))
            {
                System.out.println();
                System.out.println("PRODUCTO INSERTADO");
            }
            else
            {
                System.out.println();
                System.out.println(respuesta);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
    }

    /**
     *
     * @param s
     * @param reader
     */
    public static void borrarProducto(RmiInterface s, Scanner reader)
    {

    }

    /**
     *
     * @param s
     * @param reader
     */
    public static void mostrarProductos(RmiInterface s, Scanner reader)
    {

    }
}
