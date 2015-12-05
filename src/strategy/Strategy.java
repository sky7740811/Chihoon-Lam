/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;
import java.util.ArrayList;
import carte.Ingredient;
import carte.Alliee;
import partie.Champ;
/**
 *
 * @author Chihoon
 */
public interface Strategy {
    public int choisirCarte(int id, int saison, ArrayList<Ingredient> carteIngredient);
    
    public int choisirAction(int id, int nbjoueur, int saison, Ingredient carteIngredient, ArrayList<Champ> champ);
    
    public int choisirCible(int nbjoueur, int id, int cible); //id = pour identifier quel joueur virtuel , cible = il sert pour le niveau intermediaire
    
    
    public int choixDepart();
    
    public int jouerTaupeGeant();
    
    public int jouerChienGarde();
    
    //Uniquement pour niveau Intermediaire
 
    public int getCible();
    //test2
}
