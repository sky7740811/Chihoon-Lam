/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joueur;
import carte.Carte;
import java.util.Scanner;
import partie.Champ;
import carte.Ingredient;
import java.util.ArrayList;

public abstract class Joueur {
    private String nomJoueur;
    public int pointsTotal;
    public int idJoueur;
    public String ChoixDepart;
    public Scanner input = new Scanner(System.in);
    
    public Joueur(String nom, int id){
        this.nomJoueur = nom;
        this.idJoueur = id;
    }
    
    public void setNomJoueur(String nom){
        this.nomJoueur = nom;
    }
    
    public String getNomJoueur(){
        return this.nomJoueur;
    }
    
    public void finirTour(){
        
    }
    
    public void jouerCarte(Ingredient carteIngredient[], Champ champ[], int nbjoueur,int i){
        
    }

    public void jouerCarte(ArrayList<Ingredient> carteIngredient, ArrayList<Champ> champ, int nbJoueur, int i) {
       
    }
}
