/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.strategy;

/**
 *
 * @author Chihoon
 */
public interface Strategy {
    public int choisirCarte(int id);
    
    public int choisirAction();
    
    public int choisirCible(int nbjoueur, int id); //id = pour identifier quel joueur virtuel
    
    
    //test2
}
