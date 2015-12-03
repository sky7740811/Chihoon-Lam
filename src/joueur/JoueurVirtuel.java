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
                carteAlliee.detruireMenhir(carteAlliee.valeurs[saison],champ.get(cible-1));
                if(cible==1){
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
                int cible2 = strategy.choisirCible(nbjoueur, this.idJoueur);
                carteIngredient.get(choix).jouerFarfadets(carteIngredient.get(choix).valeursFarfadets[saison], champ.get(this.idJoueur-1), champ.get(cible2-1));
                if(cible2==1){
                    System.out.println("\n"+this.getNomJoueur()+" a volé " + carteIngredient.get(choix).getGraineAvoler() + " graine(s) de votre!\n");  
                }
                else{
                    System.out.println("\n"+this.getNomJoueur()+" a volé " + carteIngredient.get(choix).getGraineAvoler() + " graine(s) du Joueur " + (cible2) + "\n");//Carte choisie au tour j au saison i
                }
            }
             carteIngredient.get(choix).setUsage(true); //la carte qui vient d'etre joué devient en etat utilisé
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
