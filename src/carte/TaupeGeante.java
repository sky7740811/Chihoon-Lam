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
public class TaupeGeante extends Alliee{
    public int menhirDetruits;
    public TaupeGeante(){
        super();
    }
     
    public void afficher(){
       System.out.print("Taupe Geante: ");
       for(int i=0;i<4;i++){
           System.out.print(valeurs[i]+ " ");
       }
       System.out.println("");
    }
    
    public String getNomCarte(){
        return "Taupe Géante";
    }
     public int getType(){
        return 1;
    }
    
    public void detruireMenhir(int valeur, Champ champ){
        int menhirs;
        if(champ.nbMenhir<valeur){
            menhirs= champ.nbMenhir;
        }
        else if(champ.nbMenhir==0){
            menhirs= 0;
        }
        else{
            menhirs = valeur;
        }
        setMenhirDetruits(menhirs);
        
        champ.nbMenhir-=valeur; 
        if(champ.nbMenhir<0){
            champ.nbMenhir=0;
        }
    }
    public void setMenhirDetruits(int menhirs){
        menhirDetruits = menhirs;
    }
    
    public int getMenhirDetruits(){
        return menhirDetruits;
    }
}
