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
    
    public void jouerCarte(ArrayList<Ingredient> carteIngredient, ArrayList<Champ> champ, int nbjoueur,int saison,int modeJeu,Alliee carteAlliee){ //i : saison
       
        
        if(this.aPiocheAlliee && carteAlliee!=null && carteAlliee.getType()==1){ //si il possede un Taupe Geante
            int jouerTaupe = strategy.jouerTaupeGeant(); // 1 si il joue Taupe Geant
            if(jouerTaupe==1){
                System.out.println("\nCarte Choisie: ");
                carteAlliee.afficher();
                int cible = strategy.choisirCible(nbjoueur, this.idJoueur);
                cible -= 1;
                carteAlliee.detruireMenhir(carteAlliee.valeurs[saison],champ.get(cible));
                if(cible==0){
                    System.out.println(this.getNomJoueur()+" a détruit "+carteAlliee.getMenhirDetruits()+" menhir(s) de votre!\n");
                }
                else{
                    System.out.println(this.getNomJoueur()+" a détruit "+carteAlliee.getMenhirDetruits()+" menhir(s) du Joueur "+ (cible+1)+ "\n");
                }
                this.setaPiocheAlliee(false);
            }
        }
        int choix = 0;
        do{ //refaire tant que le joueur choisisse une carte valide
        choix=strategy.choisirCarte(this.idJoueur);
        }while(carteIngredient.get(choix).estUtilise);
        
        System.out.println("\nCarte Choisie: ");
        carteIngredient.get(choix).afficher();

        int choix2 = strategy.choisirAction();
            if(choix2==1){
                carteIngredient.get(choix).jouerGeant(carteIngredient.get(choix).valeursGeant[saison], champ.get(this.getIdJoueur()-1));
                System.out.println("\n"+this.getNomJoueur()+" a récupéré " + carteIngredient.get(choix).valeursGeant[saison]+ " graine(s)\n");
            }
            else if(choix2==2){
                carteIngredient.get(choix).jouerEngrais(carteIngredient.get(choix).valeursEngrais[saison], champ.get(this.getIdJoueur()-1));
                System.out.println("\n"+this.getNomJoueur()+" a poussé " + carteIngredient.get(choix).getMenhirApousser() + " menhir(s)\n");
            }
            else{
                int choix3 = strategy.choisirCible(nbjoueur, this.idJoueur);
                int graineAvoler = 0;
                choix3-=1; //car la table joueur commence par joueur[0]
                if(carteIngredient.get(choix).valeursFarfadets[saison]<=champ.get(choix3).nbGraine){ //champ[choix3].nbGraine : Nombre graines de l'adversaire ciblé
                    graineAvoler=carteIngredient.get(choix).valeursFarfadets[saison];
                }
                else{
                    graineAvoler=champ.get(choix3).nbGraine;
                }
                if(choix3==0){
                    System.out.println("\n"+this.getNomJoueur()+" a volé " + graineAvoler + " graine(s) de votre!\n");  
                }
                else{
                    System.out.println("\n"+this.getNomJoueur()+" a volé " + graineAvoler + " graine(s) du Joueur " + (choix3 + 1) + "\n");//Carte choisie au tour j au saison i
                }
                champ.get(this.idJoueur-1).ajouter("graine", graineAvoler);
                champ.get(choix3).enlever("graine", graineAvoler);
            }
             carteIngredient.get(choix).setUsage(true); //la carte qui vient d'etre joué est en etat utilisé
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
    
    public int choixDepart(){
        int choix=strategy.choixDepart();
        return choix;
    }
    
    public int jouerTaupeGeant(){
        return 0;
    }
            
  
}
