
package strategy;
import java.util.ArrayList;
import carte.Ingredient;
import carte.Alliee;
/**
 *
 * @author Chihoon
 */
public class Intermediaire implements Strategy {

    //Le joueur virtuel va choisir la carte ayant plus grande somme des 3 valeurs (geant, engrais,farfadets) 
    public int choisirCarte(int id, int saison, ArrayList<Ingredient> carteIngredient) {
        int choixCarte = 0;
        
        int somme[] = new int[24];
        int max = 0;
        if(id==2){   //Joueur 2 : carteingredient.get(4)~(7)
            for(int i =4 ; i<8; i++){
                if(carteIngredient.get(i).getUsage()==false){
                    somme[i] = carteIngredient.get(i).valeursEngrais[saison] + carteIngredient.get(i).valeursGeant[saison] + carteIngredient.get(i).valeursFarfadets[saison]  ;
                    if(somme[i]>max){
                        max = somme[i];
                        choixCarte = i;
                    }
                }
            }
         } 
        else if(id==3){ //Joueur 3 : 8~11
            for(int i =8 ; i<11; i++){
                if(carteIngredient.get(i).getUsage()==false){
                    somme[i] = carteIngredient.get(i).valeursEngrais[saison] + carteIngredient.get(i).valeursGeant[saison] + carteIngredient.get(i).valeursFarfadets[saison]  ;
                    if(somme[i]>max){
                        max = somme[i];
                        choixCarte = i;
                    }
                }
            }
        }
        else if(id==4){ //Joueur 4 : carteingredient 12~15
            for(int i =12 ; i<15; i++){
                if(carteIngredient.get(i).getUsage()==false){
                    somme[i] = carteIngredient.get(i).valeursEngrais[saison] + carteIngredient.get(i).valeursGeant[saison] + carteIngredient.get(i).valeursFarfadets[saison]  ;
                    if(somme[i]>max){
                        max = somme[i];
                        choixCarte = i;
                    }
                }
            }
        }
        else if(id==5){//Joueur 5 : carteingredient 16~19
            for(int i =16 ; i<19; i++){
                if(carteIngredient.get(i).getUsage()==false){
                    somme[i] = carteIngredient.get(i).valeursEngrais[saison] + carteIngredient.get(i).valeursGeant[saison] + carteIngredient.get(i).valeursFarfadets[saison]  ;
                    if(somme[i]>max){
                        max = somme[i];
                        choixCarte = i;
                    }
                }
            }
        }
        else{ //Joueur 6 : carteingredient 20~23
           for(int i =16 ; i<19; i++){
                if(carteIngredient.get(i).getUsage()==false){
                    somme[i] = carteIngredient.get(i).valeursEngrais[saison] + carteIngredient.get(i).valeursGeant[saison] + carteIngredient.get(i).valeursFarfadets[saison]  ;
                    if(somme[i]>max){
                        max = somme[i];
                        choixCarte = i;
                    }
                }
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
