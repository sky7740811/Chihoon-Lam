/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;
import java.util.ArrayList;
import carte.Ingredient;
import carte.Alliee;
/**
 *
 * @author Chihoon
 */
public interface Strategy {
    public int choisirCarte(int id, int saison, ArrayList<Ingredient> carteIngredient);
    
    public int choisirAction();
    
    public int choisirCible(int nbjoueur, int id); //id = pour identifier quel joueur virtuel
    
    public int choixDepart();
    
    public int jouerTaupeGeant();
    
    public int jouerChienGarde();
    
    //test2
}
