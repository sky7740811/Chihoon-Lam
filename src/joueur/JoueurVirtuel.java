/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joueur;

import partie.Champ;
import carte.Ingredient;
import strategy.Strategy;


public class JoueurVirtuel extends Joueur implements Strategy{
    private Strategy strategy;
    
    public JoueurVirtuel(String nom, int id, Strategy strategy) {
        super(nom, id);
        this.strategy = strategy;
    }
    
    public void jouerCarte(Ingredient carteIngredient[], Champ champ[], int nbjoueur,int i){ //i : saison

        int choix = 0;
        do{ //refaire tant que le joueur choisisse une carte valide
        choix=strategy.choisirCarte(this.idJoueur);
        }while(carteIngredient[choix]==null);
        
        System.out.println("\nCarte Choisie: ");
        carteIngredient[choix].afficher();

        int choix2 = strategy.choisirAction();
            switch(choix2){
                case 1:{ //Geant
                    System.out.println("\n"+this.getNomJoueur()+" a récupéré " + carteIngredient[choix].valeursGeant[i]+ " graine(s)\n");//Carte choisie au tour j au saison i
                    champ[this.idJoueur-1].ajouter("graine", carteIngredient[choix].valeursGeant[i]);
                    break;
                }
                case 2:{ //Engrais
                    int menhirApousser = 0;
                    if(carteIngredient[choix].valeursEngrais[i]<=champ[this.idJoueur-1].nbGraine){
                        menhirApousser = carteIngredient[choix].valeursEngrais[i];
                    }
                    else{
                        menhirApousser = champ[this.idJoueur-1].nbGraine;
                    }
                        System.out.println("\n"+this.getNomJoueur()+" a poussé " + menhirApousser + " menhir(s)\n");//Carte choisie au tour j au saison i
                        champ[this.idJoueur-1].ajouter("menhir", menhirApousser);
                        champ[this.idJoueur-1].enlever("graine", menhirApousser);
                    break;
                }
                case 3:{//farfadets
                    int choix3 = strategy.choisirCible(nbjoueur, this.idJoueur);
                    int graineAvoler = 0;
                    choix3-=1; //car la table joueur commence par joueur[0]
                    if(carteIngredient[choix].valeursFarfadets[i]<=champ[choix3].nbGraine){ //champ[choix3].nbGraine : Nombre graines de l'adversaire ciblé
                        graineAvoler=carteIngredient[choix].valeursFarfadets[i];
                    }
                    else{
                        graineAvoler=champ[choix3].nbGraine;
                    }
                    if(choix3==0){
                        System.out.println("\n"+this.getNomJoueur()+" a volé " + graineAvoler + " graine(s) de votre!\n");  
                    }
                    else{
                        System.out.println("\n"+this.getNomJoueur()+" a volé " + graineAvoler + " graine(s) du Joueur " + (choix3 + 1) + "\n");//Carte choisie au tour j au saison i
                    }
                    champ[this.idJoueur-1].ajouter("graine", graineAvoler);
                    champ[choix3].enlever("graine", graineAvoler);
                    break;
                }

            }
            carteIngredient[choix]=null;
          }

    
    public int choisirCarte(int id) {
        return 0;
    }

    
    public int choisirAction() {
       return 0;
    }
    
    public int choisirCible(int nbjoueur, int id){
        return 0;
    }
        
  
}
