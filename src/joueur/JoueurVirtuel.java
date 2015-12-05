/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joueur;

import partie.Champ;
import carte.Ingredient;
import carte.Alliee;
import strategy.Strategy;
import java.util.ArrayList;


public class JoueurVirtuel extends Joueur implements Strategy{
    private Strategy strategy;
    
    public JoueurVirtuel(String nom, int id, Strategy strategy) {
        super(nom, id);
        this.strategy = strategy;
    }
    
    public void jouerCarte(ArrayList<Ingredient> carteIngredient, ArrayList<Joueur> collectionJoueurs, ArrayList<Champ> champ, int nbjoueur,int saison,int modeJeu,ArrayList<Alliee> collectionAlliee){ //i : saison
        if(modeJeu==2){ //jouer taupe geante en partie avancee
           Alliee carteAlliee = collectionAlliee.get(this.getIdJoueur()-1); // ex. Joueur 2 : collectionAlliee.get(2-1)
             if(this.aPiocheAlliee && carteAlliee!=null && carteAlliee.getType()==1){ //si il possede un Taupe Geante
            int jouerTaupe = strategy.jouerTaupeGeant(this.idJoueur, nbjoueur, saison, carteAlliee.valeurs[saison], champ ); // 1 si il joue Taupe Geant
                if(jouerTaupe==1){
                    System.out.println("\nCarte Choisie: ");
                    carteAlliee.afficher();
                    int cible = strategy.choisirCible(nbjoueur, this.idJoueur, strategy.getCible());
                    carteAlliee.detruireMenhir(carteAlliee.valeurs[saison],champ.get(cible-1));
                    if(cible==1){
                        System.out.println(this.getNomJoueur()+" a détruit "+carteAlliee.getMenhirDetruits()+" menhir(s) de votre!\n");
                    }
                    else{
                        System.out.println(this.getNomJoueur()+" a détruit "+carteAlliee.getMenhirDetruits()+" menhir(s) du " + collectionJoueurs.get(cible-1).getNomJoueur()+"\n");
                    }
                    this.setaPiocheAlliee(false);
                }
            }
        } 
      
       // --------------------------Jouer une carte ingredient
        int choix = 0;
        do{ //refaire tant que le joueur choisisse une carte valide
        choix=strategy.choisirCarte(this.idJoueur,saison,carteIngredient);
        }while(carteIngredient.get(choix).estUtilise);
        
        System.out.println("\nCarte Choisie: ");
        carteIngredient.get(choix).afficher();

        int choix2 = strategy.choisirAction(this.idJoueur, nbjoueur, saison, carteIngredient.get(choix), champ);
        System.out.println("Action choisie: " + choix2);
        //-----------------Jouer Geant------------------------
            if(choix2==1){
                carteIngredient.get(choix).jouerGeant(carteIngredient.get(choix).valeursGeant[saison], champ.get(this.getIdJoueur()-1));
                System.out.println("\n"+this.getNomJoueur()+" a récupéré " + carteIngredient.get(choix).valeursGeant[saison]+ " graine(s)\n");
            }
        //----------------Jouer Engrais--------------------------
            else if(choix2==2){
                carteIngredient.get(choix).jouerEngrais(carteIngredient.get(choix).valeursEngrais[saison], champ.get(this.getIdJoueur()-1));
                System.out.println("\n"+this.getNomJoueur()+" a poussé " + carteIngredient.get(choix).getMenhirApousser() + " menhir(s)\n");
            }
        //---------------Jouer Farfadets--------------------------
            else{
                int cible2 = strategy.choisirCible(nbjoueur, this.idJoueur,strategy.getCible());
                System.out.println("Cible: "+cible2);
                //-------------------------------Attaquer le joueur reel----------------------
                if(cible2==1){
                    if(collectionJoueurs.get(cible2-1).aPiocheAlliee && collectionAlliee.get(cible2-1).getType()==2){ // le joueur reel a un chien de garde
                        System.out.println("\n"+this.getNomJoueur()+ " essaye de voler "+carteIngredient.get(choix).valeursFarfadets[saison]+" graines!");
                        System.out.print("Souhaitez-vous d'utiliser votre ");
                        collectionAlliee.get(cible2-1).afficher();
                        System.out.print("?");
                        System.out.print("? (1. oui 2. non)\n> ");
                        int choix3 =input.nextInt();
                        while(choix3<1 || choix3>2){
                            System.out.print("Veuillez saisir entre 1 et 2\n> ");
                            choix3 = input.nextInt();
                        }
                        if(choix3==1){ // ------------------------Le joueur joue son chien de garde--------------------------------------
                            //Nb graines a voler a nouveau
                            int grainesAvoler = collectionAlliee.get(cible2-1).protegerGraine(collectionAlliee.get(cible2-1).valeurs[saison], carteIngredient.get(choix).valeursFarfadets[saison]);
                            //Nb graines a voler à nouveau
                            int grainesProtege = collectionAlliee.get(cible2-1).getGraineProtege();
                            System.out.println("\nVous avez protégé "+grainesProtege+ " graines!");
                            carteIngredient.get(choix).jouerFarfadets(grainesAvoler, champ.get(this.idJoueur-1), champ.get(cible2-1));
                            collectionJoueurs.get(cible2-1).setaPiocheAlliee(false);
                        }
                        else{ // --------------------------Le joueur ne joue pas son chien de garde
                            carteIngredient.get(choix).jouerFarfadets(carteIngredient.get(choix).valeursFarfadets[saison], champ.get(this.idJoueur-1), champ.get(cible2-1));
                        }
                        System.out.println("\n"+this.getNomJoueur()+" a volé " + carteIngredient.get(choix).getGraineAvoler() + " graine(s) de votre!\n");  
                    }
                    else{ // -------------------------Le joueur reel n'a pas de chien de garde
                        carteIngredient.get(choix).jouerFarfadets(carteIngredient.get(choix).valeursFarfadets[saison], champ.get(this.idJoueur-1), champ.get(cible2-1));
                        System.out.println("\n"+this.getNomJoueur()+" a volé " + carteIngredient.get(choix).getGraineAvoler() + " graine(s) de votre!\n");  
                    }
                }
                else{ //-----------------------le cible est un autre joueur virtuel
                    System.out.println(this.getNomJoueur()+" va attaquer "+ collectionJoueurs.get(cible2-1).getNomJoueur()+" ....");
                    if(cible2!=1 && collectionJoueurs.get(cible2-1).aPiocheAlliee && collectionAlliee.get(cible2-1).getType()==2){//le cible a un chien de garde
                        int jouerChienGarde = strategy.jouerChienGarde(collectionAlliee.get(cible2-1).valeurs[saison]);
                        if(jouerChienGarde==1){ // le cible joue chien de garde
                            //Nb graines a voler a nouveau
                            int grainesAvoler = collectionAlliee.get(cible2-1).protegerGraine(collectionAlliee.get(cible2-1).valeurs[saison], carteIngredient.get(choix).valeursFarfadets[saison]);
                            //Nb graines a voler à nouveau
                            int grainesProtege = collectionAlliee.get(cible2-1).getGraineProtege();
                            System.out.println("\n" + collectionJoueurs.get(cible2-1).getNomJoueur()+" a protégé "+grainesProtege+ " graines!");
                            carteIngredient.get(choix).jouerFarfadets(grainesAvoler, champ.get(this.idJoueur-1), champ.get(cible2-1));
                            collectionJoueurs.get(cible2-1).setaPiocheAlliee(false);
                        }
                        else{  // ------------------le cible ne joue pas chien de garde
                            carteIngredient.get(choix).jouerFarfadets(carteIngredient.get(choix).valeursFarfadets[saison], champ.get(this.idJoueur-1), champ.get(cible2-1));
                        }
                    }
                    else{// -----------------------le cible n'a pas chien de garde
                        carteIngredient.get(choix).jouerFarfadets(carteIngredient.get(choix).valeursFarfadets[saison], champ.get(this.idJoueur-1), champ.get(cible2-1));
                    }
                    System.out.println("\n"+this.getNomJoueur()+" a volé " + carteIngredient.get(choix).getGraineAvoler() + " graine(s) du " + collectionJoueurs.get(cible2-1).getNomJoueur()+"\n"); 
                    
                    
                }
             carteIngredient.get(choix).setUsage(true); //la carte qui vient d'etre joué devient en etat utilisé
          }
    }

    
    public int choisirCarte(int id, int saison, ArrayList<Ingredient> carteIngredient) {
        return 0;
    }

    
    public int choisirAction(int id, int nbjoueur, int saison, Ingredient carteIngredient, ArrayList<Champ> champ) {
       return 0;
    }
    
    public int choisirCible(int nbjoueur, int id, int cible){
        return 0;
    }
    
    public int choixDepart(){
        int choix=strategy.choixDepart();
        return choix;
    }
    
    public int jouerTaupeGeant(int id, int nbjoueur,int saison, int valeur, ArrayList<Champ> champ){
        return 0;
    }
    
    public int jouerChienGarde(int valeur){
        return 0;
    }
    
    public int getCible(){
        return 0;
    }
            
  
}
