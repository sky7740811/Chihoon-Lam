/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joueur;

import partie.Champ;
import carte.Ingredient;
import carte.Alliee;
import java.util.ArrayList;
import java.util.InputMismatchException;
/**
 *
 * @author Chihoon
 */
public class JoueurReel extends Joueur{
    
    public JoueurReel(String nom, int id){
        super(nom,id);
    }
    
    public void jouerCarte(ArrayList<Ingredient> carteIngredient, ArrayList<Joueur> collectionJoueurs, ArrayList<Champ> champ, int nbjoueur,int saison,int modeJeu, ArrayList<Alliee> collectionAlliee){ //i : saison

      int choix = 0;
      Alliee carteAlliee = collectionAlliee.get(0);
      //boolean choixnull = true; //false si le joueur choisit une carte valide.
     // do{
            System.out.println("\nQuelle carte souhaitez-vous de jouer?");
            for(int k=0;k<4;k++){
                if(carteIngredient.get(k).estUtilise==false){
                   System.out.print("Carte "+ (k+1) + " ");
                }
            }
            if(this.aPiocheAlliee && carteAlliee!=null && carteAlliee.getType()==1){ //si il possede un Taupe Geante
                System.out.print("Carte 5("+ carteAlliee.getNomCarte()+")");
            }
            System.out.print("\n> ");
            choix = input.nextInt();
            if(this.aPiocheAlliee && choix==5){//le joueur a choisi Taupe Geant
                System.out.println("Choisissez votre cible: ");
                int cible=0;
                try{
                    cible = input.nextInt();
                }catch(InputMismatchException e){
                   System.out.println("Saisie Incorrecte.\n");
                    jouerCarte(carteIngredient, collectionJoueurs, champ, nbjoueur, saison, modeJeu, collectionAlliee); 
                }
                if(cible<2 || cible>nbjoueur){
                    System.out.println("Veuillez cibler entre le joueur 2 et "+ nbjoueur);
                    jouerCarte(carteIngredient, collectionJoueurs, champ, nbjoueur, saison, modeJeu, collectionAlliee); 
                }
                else{
                    cible-=1; 
                    carteAlliee.detruireMenhir(carteAlliee.valeurs[saison],champ.get(cible));
                    System.out.println("Vous avez détruit "+carteAlliee.getMenhirDetruits()+" menhir(s) du Joueur"+ (cible+1)+ "\n");
                    this.setaPiocheAlliee(false);
                    jouerCarte(carteIngredient, collectionJoueurs, champ, nbjoueur, saison, modeJeu, collectionAlliee); 
                }
            }
            else{
                choix -= 1; // car carte 1 = carteIngredient.get(0)

                if(carteIngredient.get(choix).estUtilise==false){ //Si la carte ingredient choisie existe dans la main
                    
                    //choisir l'action
                    System.out.println("\nCarte Choisie: ");
                    carteIngredient.get(choix).afficher();
                    System.out.print("\nQuelle action souhaitez-vous de jouer? 1: Geant, 2: Engrais, 3: Farfadets");
                    System.out.print("\n> ");
                    int choix2 = 0;
                    try{
                        choix2 = input.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Saisie Incorrecte.\n");
                        jouerCarte(carteIngredient, collectionJoueurs, champ, nbjoueur, saison, modeJeu, collectionAlliee);
                    }
                    //Jouer Geant
                    if(choix2==1){
                        carteIngredient.get(choix).jouerGeant(carteIngredient.get(choix).valeursGeant[saison], champ.get(this.getIdJoueur()-1));
                        System.out.println("\nVous avez récupéré " + carteIngredient.get(choix).valeursGeant[saison]+ " graine(s)\n");
                    }
                    //Jouer Engrais
                    else if(choix2==2){
                         carteIngredient.get(choix).jouerEngrais(carteIngredient.get(choix).valeursEngrais[saison], champ.get(this.getIdJoueur()-1));
                         System.out.println("\nVous avez poussé " + carteIngredient.get(choix).getMenhirApousser() + " menhir(s)\n");
                    }
                    //Jouer Farfadets
                    else if(choix2==3){
                        System.out.print("\nChoisissez votre cible (entre Joueur2 ~ " + nbjoueur +") : \n> "); 
                        int cible2 = input.nextInt();
                        while(cible2<2 || cible2>nbjoueur){
                            System.out.print("\nVeuillez choisir un cible valide\n> ");
                            cible2 = input.nextInt();
                        }
                        carteIngredient.get(choix).jouerFarfadets(carteIngredient.get(choix).valeursFarfadets[saison], champ.get(this.getIdJoueur()-1), champ.get(cible2-1));
                        System.out.println("\nVous avez volé " + carteIngredient.get(choix).getGraineAvoler() + " graine(s) du Joueur" + (cible2) + "\n");
                    }
                    else{
                        System.out.println("Veuillez choisir entre 1 et 3");
                        jouerCarte(carteIngredient, collectionJoueurs, champ, nbjoueur, saison, modeJeu,collectionAlliee);
                    }
                carteIngredient.get(choix).setUsage(true);
                }
                else{
                    System.out.println("\nVeuillez choisir une carte valide");
                    jouerCarte(carteIngredient, collectionJoueurs, champ, nbjoueur, saison, modeJeu, collectionAlliee); 
                }
            }
      //  }while(choixnull);
    }
}

