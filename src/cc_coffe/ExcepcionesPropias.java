/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cc_coffe;

/**
 *
 * @author SaulCC
 */
public class ExcepcionesPropias {
    public void cantidad(int cant) throws Exception{
        if((cant<1) || (cant>10)){
            System.out.println("ERROR, cantidad no disponible");
            throw new Exception();
        }
    }
    
    public void telefono(String telefono) throws Exception{
        if(telefono.length()<10 || telefono.length()>10){
            System.out.println("Formato incorrecto");
            throw new Exception();
        }
    }
}
