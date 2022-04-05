/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacao;


import java.util.List;
import model.dao.DaoFactory;
import model.dao.DepartamentoDAO;
import model.entidades.Departamento;

/**
 *
 * @author Karol Dev
 */
public class Programa2 {
    
    public static void main(String[] args) {
        
        DepartamentoDAO depDAO = DaoFactory.createDepartamentoDAO();
        
        System.out.println("=== Teste 1: Departamento findById ===");        
        Departamento dep = depDAO.findById(1);        
        System.out.println(dep);
        
        
        System.out.println("\n=== Teste 3: Departamento findALL ===");
        
         List<Departamento> list = depDAO.findAll();
       
        list = depDAO.findAll();
        for (Departamento obj : list) {
            System.out.println(obj);
            
        }
        
        
       /* System.out.println("\n=== Teste 4: Departamento Insert ===");
       
        Departamento novoDep = new Departamento(null, "Cosmeticos");
        depDAO.insert(novoDep);
        System.out.println("Inserido um novo id = " + novoDep.getId());*/
        
        
        System.out.println("\n=== Teste 5: Departamento Update ===");
       
        dep = depDAO.findById(1);
        dep.setName("Livros");
        depDAO.update(dep);
        System.out.println("Update Completo");
        
        
        System.out.println("\n=== Teste 6: Departamento Delete ===");
        
        depDAO.deleteById(7);
        System.out.println("Delete completo");
        
        
    }
    
}
