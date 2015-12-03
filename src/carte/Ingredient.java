/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carte;


public class Ingredient extends Carte {
    public int valeursGeant[] = new int[4];
    public int valeursEngrais[] = new int[4];
    public int valeursFarfadets[] = new int[4];
    public boolean estUtilise;
    public String action;
    //liste des cartes ingredients : il y a 24 cartes
    public static final int Geant[][]={{1,1,1,1},{2,0,1,1},{0,0,4,0},{1,3,1,0},{2,1,1,1},{1,2,2,0},{2,1,1,2},{0,3,0,3},{1,2,1,2},{1,3,1,2},{2,2,0,3},{2,2,3,1},{2,2,3,1},{2,2,2,2},{3,1,3,1},{4,1,1,1},{2,3,2,0},{2,2,3,0},{3,1,4,1},{2,4,1,2},{3,3,3,0},{1,2,2,1},{4,0,1,1},{2,0,1,3}};
    public static final int Engrais[][]={{2,0,1,1},{1,3,0,0},{0,2,2,0},{1,2,1,1},{1,0,2,2},{1,1,2,1},{1,1,1,3},{2,1,3,0},{1,0,1,4},{2,1,2,2},{1,1,4,1},{2,3,0,3},{2,3,0,3},{0,4,4,0},{1,4,2,1},{1,2,1,3},{0,4,3,0},{1,1,1,4},{2,1,3,3},{2,2,2,3},{1,3,3,2},{1,2,3,0},{1,1,3,1},{0,3,0,3}};
    public static final int Farfadets[][]={{2,0,2,0},{0,1,2,1},{0,0,1,3},{0,1,4,0},{3,0,0,2},{2,0,1,2},{2,0,2,2},{1,1,3,1},{2,4,0,0},{0,0,3,4},{1,2,1,3},{1,1,3,3},{1,1,3,3},{1,3,2,2},{2,4,1,1},{1,2,2,2},{2,1,1,3},{2,0,3,2},{2,3,2,2},{1,4,3,1},{2,3,1,3},{0,2,2,2},{0,0,3,3},{1,2,2,1}};
    
    public Ingredient(int id){ //id = identifiant d'une carte (0 à 23)
        for(int i=0 ; i<4; i++){ // i est le compteur pour les valeurs d'une carte ingredient
            valeursGeant[i]=this.Geant[id][i];
            valeursEngrais[i]=this.Engrais[id][i];
            valeursFarfadets[i]=this.Farfadets[id][i];
        }
        estUtilise = false;
    }
    
    public void afficher(){
        System.out.print("Geant     :");
        for(int i=0;i<4;i++){
            System.out.print(" "+ valeursGeant[i]);
        }
        System.out.print("\nEngrais   :");
        for(int i=0;i<4;i++){
            System.out.print(" "+ valeursEngrais[i]);
        }
        System.out.print("\nFarfadets :");
        for(int i=0;i<4;i++){
            System.out.print(" "+ valeursFarfadets[i]);
        }
        System.out.println("\n");
    }
    
    public void setUsage(boolean bool){
        estUtilise=bool;
    }
    
    public boolean getUsage(){
        return estUtilise;
    }
}