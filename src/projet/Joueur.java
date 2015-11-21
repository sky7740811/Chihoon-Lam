/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;
import java.util.Scanner;

public abstract class Joueur {
    public String nomJoueur;
    public int pointsTotal;
    public int idJoueur;
    public String ChoixDepart;
    public Scanner input = new Scanner(System.in);
    
    public Joueur(String nom, int id){
        this.nomJoueur = nom;
        this.idJoueur = id;
    }
    
    public void setNomJoueur(){
        
    }
    
    public String getNomJoueur(){
        return this.nomJoueur;
    }
    
    public void finirTour(){
        
    }
    
    public void jouerCarte(Ingredient carteIngredient[], Champ champ[], int nbjoueur,int i){
        
    }
}
