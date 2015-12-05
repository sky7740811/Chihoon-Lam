/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carte;
import partie.Champ;

/**
 *
 * @author Chihoon
 */
public class Alliee extends Carte {
    public int valeurs[] = new int[4];
    public boolean estUtilise;
  
     
     public Alliee(int id){
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
    
    public String getNomCarte(){
        return null;
    }
    
    public int getType(){
        return 0;
    }
    
    public void detruireMenhir(int valeur, Champ champ){}
    
    public int protegerGraine(int valeurChien, int valeurFarfadet){
        return 0;
    }
    
    public int getMenhirDetruits(){
        return 0;
    }
    
     public void setGraineProtege(int graines){
  
    }
    
    public int getGraineProtege(){
        return 0;
    }
}
