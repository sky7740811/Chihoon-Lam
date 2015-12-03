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
public class ChienDeGarde extends Alliee{
    public int grainesProtege;
    public ChienDeGarde(){
        super();
    }
     
    public void afficher(){
       System.out.print("Chien de garde: ");
       for(int i=0;i<4;i++){
           System.out.print(valeurs[i]+ " ");
       }
       System.out.println("");
    }
    
    public String getNomCarte(){
        return "Chien de garde";
    }
    
    public int getType(){
        return 2;
    }
    
    public int protegerGraine(int valeurChien, int valeurFarfadet){
        int grainesP; //nb graines proteges
        int grainesV; //nb graines a voler
        if(valeurChien>=valeurFarfadet){
            grainesP = valeurFarfadet;
            grainesV = 0;
        }
        else{
            grainesP = valeurChien;
            grainesV = valeurFarfadet - valeurChien;
        }
        setGraineProtege(grainesP);
        return grainesV;
    }
    
    public void setGraineProtege(int graines){
        grainesProtege = graines;
    }
    
    public int getGraineProtege(){
        return grainesProtege;
    }
}
