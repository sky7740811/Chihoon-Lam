
package strategy;
import java.util.ArrayList;
import carte.Ingredient;
import carte.Alliee;
import partie.Champ;

/**
 *
 * @author Chihoon
 */
public class Debutant implements Strategy {

    
    public int choisirCarte(int id, int saison, ArrayList<Ingredient> carteIngredient) {
       int choixCarte = 0;
       if(id==2){   //Joueur 2 : carteingredient[4]~[7]
           choixCarte=(int)(Math.random()*(4))+4;
        } 
       else if(id==3){ //Joueur 3 : carteingredient[8]~[11]
           choixCarte=(int)(Math.random()*(4))+8;
       }
       else if(id==4){ //Joueur 4 : carteingredient[12]~[15]
           choixCarte=(int)(Math.random()*(4))+12;
       }
       else if(id==5){//Joueur 5 : carteingredient[16]~[19]
        choixCarte=(int)(Math.random()*(4))+16;
       }
       else{ //Joueur 6 : carteingredient[20]~[23]
           choixCarte=(int)(Math.random()*(4))+20;
       }
       return choixCarte;
    }

    
    public int choisirAction(int id, int nbjoueur, int saison, Ingredient carteIngredient, ArrayList<Champ> champ) {
        int choixAction=(int)(Math.random()*(3))+1;
        return choixAction;
    }
    
    public int choisirCible(int nbjoueur, int id, int cible){
        
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
    
    public int jouerTaupeGeant(int id, int nbjoueur,int saison, int valeur, ArrayList<Champ> champ){
        int choix=(int)(Math.random()*(2));
        return choix;
    }
    
    public int jouerChienGarde(int valeur){
        int choix=(int)(Math.random()*(2));
        return choix;
    }
    
    public int getCible(){
        return 0;
    }
}
