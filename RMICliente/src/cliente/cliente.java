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
import java.util.ArrayList;
import java.util.List;
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
                System.out.println("0 - Salir.");
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
                        mostrarProductos(s);
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
            reader.nextLine();
            System.out.print("Introduzca su nombre: ");
            nombre = reader.nextLine();
            System.out.print("Introduzca su telefono: ");
            telefono = reader.nextLine();
            System.out.print("Introduzca su email: ");
            email = reader.nextLine();
            System.out.print("Vuelva a introducir su email: ");
            email2 = reader.nextLine();
            System.out.print("Introduzca su contraseña: ");
            pass = reader.nextLine();
            
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
           
            String producto;
            String precio;
            Boolean respuesta;
            
            
            System.out.println();
            System.out.println();
            System.out.println();
            reader.nextLine();
            System.out.print("Introduzca el producto: ");
            producto = reader.nextLine();
            System.out.print("Introduzca su precio: ");
            precio = reader.nextLine();
            
            respuesta = s.insertProductInShop(producto, precio);
            
            if (respuesta == true)
            {
                System.out.println();
                System.out.println("PRODUCTO INSERTADO");
            }
            else
            {
                System.out.println();
                System.out.println("Error de insercion de producto");
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
    public static void borrarProducto(RmiInterface s, Scanner reader)
    {
        try {
           
           
            Boolean respuesta;
            String producto;
            
            System.out.println();
            System.out.println();
            System.out.println();
            reader.nextLine();
            System.out.print("Introduzca el nombre del producto a eliminar : ");
            producto = reader.nextLine();
            
            
            respuesta = s.deleteProductoInShop (producto);
            
            if (respuesta == true)
            {
                System.out.println();
                System.out.println("PRODUCTO ELIMINADO");
            }
            else
            {
                System.out.println();
                System.out.println("Error al eliminar el producto");
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
    public static void mostrarProductos(RmiInterface s){
        try {
            List<String> resultado = new ArrayList<>();
            
            resultado = s.showProductsInShop();
            
            
            System.out.println();
            System.out.println();
            System.out.println("Listado de productos");
            System.out.println();
            int count = 0; 		
            while (resultado.size() > count) {
               System.out.println("   - " + resultado.get(count));
               count++;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
