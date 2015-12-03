/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;


/**
 *
 * @author Chihoon
 */
public class Debutant implements Strategy {

    
    public int choisirCarte(int id) {
       int choixCarte = 0;
        switch(id){//Pour chaque joueur 
            case 2:{ //Joueur 2 : carteingredient[4]~[7]
                choixCarte=(int)(Math.random()*(4))+4;
                break;
            }
            case 3:{ //Joueur 3 : carteingredient[8]~[11]
                choixCarte=(int)(Math.random()*(4))+8;
                break;
            }
            case 4:{ //Joueur 4 : carteingredient[12]~[15]
                choixCarte=(int)(Math.random()*(4))+12;
                break;
            }
            case 5:{ //Joueur 5 : carteingredient[16]~[19]
                choixCarte=(int)(Math.random()*(4))+16;
                break;
            }
            case 6:{ //Joueur 6 : carteingredient[20]~[23]
                choixCarte=(int)(Math.random()*(4))+20;
                break;
            }
                
        }
        return choixCarte;
    }

    
    public int choisirAction() {
        int choixAction=(int)(Math.random()*(3))+1;
        return choixAction;
    }
    
    public int choisirCible(int nbjoueur, int id){
        
        int choixCible=(int)(Math.random()*(nbjoueur))+1; //ex. nbjoueur 5 : entre 1 à 5
        while(choixCible==id){
            choixCible=(int)(Math.random()*(nbjoueur))+1;
        }
        return choixCible;
    }
    
    public int choixDepart() {
        int choix=(int)(Math.random()*(2));
        return choix;
    }
    
    public int jouerTaupeGeant(){
        int choix=(int)(Math.random()*(2));
        return choix;
    }
    
    public int jouerChienGarde(){
        int choix=(int)(Math.random()*(2));
        return choix;
    }
}
