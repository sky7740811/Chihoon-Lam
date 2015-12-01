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
public class Alliee extends Carte {
    public static int valeurs[] = new int[4];
    public boolean estUtilise;
  
     public Alliee(){
        for(int i=0;i<4;i++){
            valeurs[i]=(int)(Math.random()*(4))+0;
        }
        estUtilise=false;
    }
     
     public void afficher(){
        for(int i=0;i<4;i++){
            System.out.print(" "+ valeurs[i]);
        }
     }
     
      public void setUsage(boolean bool){
        estUtilise=bool;
    }
    
    public boolean getUsage(){
        return estUtilise;
    }
}
