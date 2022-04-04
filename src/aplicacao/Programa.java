/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacao;

import java.util.Date;
import java.util.List;
import model.dao.DaoFactory;
import model.dao.VendedorDAO;
import model.entidades.Departamento;
import model.entidades.Vendedor;

/**
 *
 * @author Karol Dev
 */
public class Programa {
    public static void main(String[] args) {
          
        VendedorDAO vendDAO = DaoFactory.createVendedorDAO();
        
        System.out.println("=== Teste 1: Vendendor findById ===");        
        Vendedor vend = vendDAO.findById(3);        
        System.out.println(vend);
        
        System.out.println("\n=== Teste 2: Vendendor findByDepartament ===");
        Departamento departament = new Departamento(2, null);
        List<Vendedor> list = vendDAO.findByDepartament(departament);
        
        for (Vendedor obj : list) {
            System.out.println(obj);
            
        }
        
        
        System.out.println("\n=== Teste 3: Vendendor findALL ===");
       
        list = vendDAO.findAll();
        for (Vendedor obj : list) {
            System.out.println(obj);
            
        }
        
        System.out.println("\n=== Teste 4: Vendendor Insert ===");
       
        Vendedor novoVend = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 3000.0, departament);
        vendDAO.insert(novoVend);
        System.out.println("Inserido um novo id = " + novoVend.getId());
        
        System.out.println("\n=== Teste 5: Vendendor Update ===");
       
        vend = vendDAO.findById(1);
        vend.setEmail("mSilva@gmail.com");
        vendDAO.update(vend);
        System.out.println("Update Completo");
        
        
        System.out.println("\n=== Teste 6: Vendendor Delete ===");
        
        vendDAO.deleteById(12);
        System.out.println("Delete completo");
        
    }
    
}
