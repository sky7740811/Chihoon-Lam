/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partie;

/**
 *
 * @author Chihoon
 */
public class Champ {
    public int nbGraine=0;
    public int nbMenhir=0;
    
    public void ajouter(String s,int nb){ //s = graine ou menhir
        if(s=="graine"){
            nbGraine+=nb;
        }
        else{
            nbMenhir+=nb;
        }
        
    }
    
    public void enlever(String s,int nb){
        if(s=="graine"){
            nbGraine-=nb;
        }
        else{
            nbMenhir-=nb;
        }
    }
 
    public void afficher(){
        System.out.println("Nombre de graines : "+nbGraine);
        System.out.println("Nombre de menhir : "+nbMenhir);
    }
    
    
    
}
