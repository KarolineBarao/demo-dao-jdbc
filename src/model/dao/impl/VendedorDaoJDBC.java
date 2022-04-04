/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import db.DB;
import java.util.List;
import model.dao.VendedorDAO;
import model.entidades.Vendedor;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import model.entidades.Departamento;
import db.DbException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Karol Dev
 */
public class VendedorDaoJDBC implements VendedorDAO{
    
    private Connection conn;

    public VendedorDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    

    @Override
    public void insert(Vendedor vend) {
        PreparedStatement st = null;
        
        try {
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, vend.getName());
            st.setString(2, vend.getEmail());
            st.setDate(3, new java.sql.Date(vend.getBirthDate().getTime()));
            st.setDouble(4, vend.getSalary());
            st.setInt(5, vend.getDepartament().getId());
            
            int linhasAfetadas = st.executeUpdate();
            
            if(linhasAfetadas > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    vend.setId(id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("Erro! Nenhuma linha foi afetada");
            }
        } catch (SQLException e) {
            
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
        }
        


    }

    @Override
    public void update(Vendedor vend) {
               PreparedStatement st = null;
        
        try {
            st = conn.prepareStatement(
                    "UPDATE seller "
                    + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                    + "WHERE Id = ? ");
            
            st.setString(1, vend.getName());
            st.setString(2, vend.getEmail());
            st.setDate(3, new java.sql.Date(vend.getBirthDate().getTime()));
            st.setDouble(4, vend.getSalary());
            st.setInt(5, vend.getDepartament().getId());
            st.setInt(6, vend.getId());
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
        }
        
        


    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        
                try {
            st = conn.prepareStatement(
                    "DELETE FROM seller WHERE Id = ? ");             
            
            st.setInt(1, id);
            
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
        }
        
        
       

    }

    @Override
    public Vendedor findById(Integer id) { // busca vendedor por id
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Departamento dep = instanciaDepartament(rs);
                
                Vendedor obj = instanciaVendedor(rs, dep);
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);

        }
        
    }

    @Override
    public List<Vendedor> findAll() { //busca todos os vendedores ordenado por nome
         PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "ORDER BY Name");
          
            rs = st.executeQuery();
            
            List<Vendedor> list = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>(); // map para gardar qualquer departamento que for instanciado
            
            while(rs.next()){
                
                Departamento dep = map.get(rs.getInt("DepartmentId")); // teste para verificar se o departamento já existe
                
                if(dep == null){ // caso não exista departamento intancia um novo departamento
                    dep = instanciaDepartament(rs); 
                    map.put(rs.getInt("DepartmentId"), dep); // salva o departamento no map
                }
                
                       
                Vendedor obj = instanciaVendedor(rs, dep);
                list.add(obj);
            }
            return list;
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);

        }
        
    }

    private Departamento instanciaDepartament(ResultSet rs) throws SQLException {
        
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    private Vendedor instanciaVendedor(ResultSet rs, Departamento dep) throws SQLException {
        Vendedor obj = new Vendedor();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setSalary(rs.getDouble("BaseSalary"));
        obj.setDepartament(dep);
        return obj;
    }

    @Override
    public List<Vendedor> findByDepartament(Departamento departament) { //busca vendedor por departamento
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name");
            
            
            st.setInt(1, departament.getId());
            rs = st.executeQuery();
            
            List<Vendedor> list = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>(); // map para gardar qualquer departamento que for instanciado
            
            while(rs.next()){
                
                Departamento dep = map.get(rs.getInt("DepartmentId")); // teste para verificar se o departamento já existe
                
                if(dep == null){ // caso não exista departamento intancia um novo departamento
                    dep = instanciaDepartament(rs); 
                    map.put(rs.getInt("DepartmentId"), dep); // salva o departamento no map
                }
                
                       
                Vendedor obj = instanciaVendedor(rs, dep);
                list.add(obj);
            }
            return list;
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);

        }
        
    }
    
}
