/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carte;

/**
 *
 * @author Chihoon
 */
public class TaupeGeante extends Alliee{
    public TaupeGeante(){
        super();
    }
     
    public void afficher(){
       System.out.println("Taupe Geante: ");
       for(int i=0;i<4;i++){
           System.out.print(" "+ valeurs[i]);
       }
    }
    
}