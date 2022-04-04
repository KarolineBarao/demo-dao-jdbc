/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.dao;

import java.util.List;
import model.entidades.Departamento;

/**
 *
 * @author Karol Dev
 */
public interface DepartamentoDAO {
    
    public void insert(Departamento departament);
    public void update(Departamento departament);
    public void deleteById(Integer id);
    public Departamento findById(Integer id);
    public List<Departamento> findAll();
    
}
