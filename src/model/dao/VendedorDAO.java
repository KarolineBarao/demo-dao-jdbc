/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.dao;

import java.util.List;
import model.entidades.Departamento;
import model.entidades.Vendedor;



public interface VendedorDAO {
    public void insert(Vendedor vend);
    public void update(Vendedor vend);
    public void deleteById(Integer id);
    public Vendedor findById(Integer id);
    public List<Vendedor> findAll();
    public List<Vendedor> findByDepartament(Departamento departament);
    
    
}
