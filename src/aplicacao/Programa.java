/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacao;

import java.util.Date;
import model.entidades.Departamento;
import model.entidades.Vendedor;

/**
 *
 * @author Karol Dev
 */
public class Programa {
    public static void main(String[] args) {
        Departamento obj = new Departamento(1,"Books");
        Vendedor vend = new Vendedor(1, "Pablo", "pa@gmail.com", new Date(), 2000.0, obj);
        
        System.out.println(vend);
    }
}
