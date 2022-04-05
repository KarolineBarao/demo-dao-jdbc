/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;


import db.DB;
import db.DbException;
import java.util.List;
import model.dao.DepartamentoDAO;
import model.entidades.Departamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

/**
 *
 * @author Karol Dev
 */
public class DepartamentoDaoJDBC implements DepartamentoDAO{
    
    private Connection conn;

    public DepartamentoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Departamento departament) {
        
        PreparedStatement st = null;
        
        try {
            
            st = conn.prepareStatement(
                    "INSERT INTO department (Name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, departament.getName());
            
            int linhasAfetadas = st.executeUpdate();
            
            if(linhasAfetadas > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    departament.setId(id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("Erro! Nenhuma linha foi afetada");
            }
                 
        }catch (SQLException e) {
            
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
        } 
    }

    @Override
    public void update(Departamento departament) {
        
        PreparedStatement st = null;
        
        try {
            st = conn.prepareStatement(
                    "UPDATE department SET Name = ? WHERE Id = ?");
            
            st.setString(1, departament.getName());            
            st.setInt(2, departament.getId());
            
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
                    "DELETE FROM department WHERE Id = ? ");             
            
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
    public Departamento findById(Integer id) { //Buscar departamento por id
        
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department WHERE id = ?");
            
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                
                Departamento dep = new Departamento();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));
                return dep;
            }
            
            return null;
            
        } catch (SQLException e) {
            
            throw new db.DbException(e.getMessage());
            
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
                
                
    }

    @Override
    public List<Departamento> findAll() {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department ORDER BY Name" );
            rs = st.executeQuery();
            List<Departamento> list = new ArrayList<>();
            
            
            while(rs.next()){
                 Departamento dep = new Departamento();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));
            
                list.add(dep);
                
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
