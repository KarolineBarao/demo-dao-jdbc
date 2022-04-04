/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import db.DB;
import model.dao.impl.VendedorDaoJDBC;

/**
 *
 * @author Karol Dev
 */
public class DaoFactory {
    
    public static VendedorDAO createVendedorDAO(){
        return new VendedorDaoJDBC(DB.conexaoDB());
    }
    
}
