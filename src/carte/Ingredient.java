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
    public String action;
    
    
    public Ingredient(){
        for(int i=0;i<4;i++){
            valeursGeant[i]=(int)(Math.random()*(5))+0;
            valeursEngrais[i]=(int)(Math.random()*(5))+0;
            valeursFarfadets[i]=(int)(Math.random()*(5))+0;
        }
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
}