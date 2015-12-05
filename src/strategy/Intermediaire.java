
package strategy;
import java.util.ArrayList;
import carte.Ingredient;
import carte.Alliee;
import partie.Champ;
/**
 *
 * @author Chihoon
 */
public class Intermediaire implements Strategy {
    public int joueurcible=0;
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

    
    public int choisirAction(int id, int nbjoueur,int saison, Ingredient carteIngredient, ArrayList<Champ> champ) {
        int choixAction=0;
        int maxGraine = 0;
        int imaxGraine=0; // index du champ ayant le plus de graines
            if(champ.get(id-1).nbGraine<2 || carteIngredient.valeursEngrais[saison]==0){ //Si le joueur possede 1 graine ou moins, ou si la valeur d'engrais est 0, il jouera soit geant, soit farfadet
                if(carteIngredient.valeursGeant[saison]>carteIngredient.valeursFarfadets[saison]){ // Si la valeur de geant est plus haute, il jouera geant
                    choixAction = 1; 
                }
                else if(carteIngredient.valeursGeant[saison]<carteIngredient.valeursFarfadets[saison]){ // Si la valeur de farfadet est plus grande, on verifie l'etat du champ des autres
                    for(int i = 0 ; i<nbjoueur; i++){
                        if(i!=(id-1)){ //On exclut le champ du joueur qui joue
                            if(champ.get(i).nbGraine>maxGraine){ // On essaye de trouver un joueur ayant le plus de graines
                                maxGraine = champ.get(i).nbGraine;
                                imaxGraine = i;
                            }
                        }
                    }
                    if(maxGraine == 0){ //Si tout le monde a 0 graines, le joueur joue Geant
                        choixAction = 1;
                    }
                    else{ //Sinon il joue farfadet
                        choixAction = 3;
                        setCible(imaxGraine+1); // on enregistre l'index du joueur ciblé
                    }
                }
                else{ //si valeur geant est egale à celle du farfadet, on joue geant
                    choixAction = 1;
                }
            }
            else{ //Le joueur possede plus de 2 graines, ayant la valeur d'engrais positive
                    choixAction = 2;
                }
        return choixAction;
    }
    
    public int choisirCible(int id, int nbjoueur, int cible){
        
        int choixCible= cible;
        return choixCible;
    }
    //tous les joueurs virtuels piocheront carte alliée
    public int choixDepart() {
        return 2;
    }
    
    //Le joueur attaquera en priorité le joueur ayant le plus de menhirs
    public int jouerTaupeGeant(int id, int nbjoueur,int saison, int valeur, ArrayList<Champ> champ){
        int choix=0;
        if(valeur==0){ // si la valeur est 0, le joueur ne joue pas taupe geant
            choix = 0;
        }
        else{
            int maxMenhir = 0;
            int imaxMenhir = 0;
            for(int i = 0 ; i<nbjoueur; i++){
                if(i!=(id-1)){ //On exclut le champ du joueur qui joue
                    if(champ.get(i).nbMenhir>maxMenhir){ // On essaye de trouver un joueur ayant le plus de menhir
                        maxMenhir = champ.get(i).nbMenhir;
                        imaxMenhir = i;
                    }
                }
            }
            if(maxMenhir==0){ //Si tout le monde a 0 menhir, le joueur ne joue pas taupe geante
                choix = 0;
            }
            else{
                choix = 1;
                setCible(imaxMenhir+1);
            }
        }
        
        return choix;
    }
    
    //Si la valeur du chien de garde est positif, le joueur joue chien de garde
    public int jouerChienGarde(int valeur){
        int choix = 0;
        if(valeur>0){
            choix = 1;
        }
        return choix;
    }
    
    public void setCible(int cible){
        joueurcible = cible;
    }
    
    public int getCible(){
        return joueurcible;
    }
}
