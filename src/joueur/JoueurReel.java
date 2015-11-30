/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joueur;

import partie.Champ;
import carte.Ingredient;
import java.util.ArrayList;
/**
 *
 * @author Chihoon
 */
public class JoueurReel extends Joueur{
    
    public JoueurReel(String nom, int id){
        super(nom,id);
    }
    
    public void jouerCarte(ArrayList<Ingredient> carteIngredient, Champ champ[], int nbjoueur,int i){ //i : saison

      int choix = 0;
      boolean choixnull = true; //false si le joueur choisit une carte valide.
      do{
            System.out.println("\nQuelle carte souhaitez-vous de jouer?");
            for(int k=0;k<4;k++){
                if(carteIngredient.get(k)!=null){
                   System.out.print("Carte "+ (k+1) + " ");
                }
            }
            System.out.print("\n> ");
            choix = input.nextInt();
            choix -= 1;
            if(carteIngredient.get(choix)!=null){ //Si la carte choisie existe dans la main
                choixnull = false;
                //choisir l'action
                System.out.println("\nCarte Choisie: ");
                carteIngredient.get(choix).afficher();
                System.out.print("\nQuelle action souhaitez-vous de jouer? 1: Geant, 2: Engrais, 3: Farfadets\n> ");
                int choix2 = input.nextInt();
                do{    
                    switch(choix2){
                        case 1:{ //Geant
                            System.out.println("\nVous avez récupéré " + carteIngredient.get(choix).valeursGeant[i]+ " graine(s)\n");//Carte choisie au tour j au saison i
                            champ[0].ajouter("graine", carteIngredient.get(choix).valeursGeant[i]);
                            break;
                        }
                        case 2:{ //Engrais
                            int menhirApousser = 0;
                            if(carteIngredient.get(choix).valeursEngrais[i]<=champ[0].nbGraine){
                                menhirApousser = carteIngredient.get(choix).valeursEngrais[i];
                            }
                            else{
                                menhirApousser = champ[0].nbGraine;
                            }
                                System.out.println("\nVous avez poussé " + menhirApousser + " menhir(s)\n");//Carte choisie au tour j au saison i
                                champ[0].ajouter("menhir", menhirApousser);
                                champ[0].enlever("graine", menhirApousser);
                            break;
                        }
                        case 3:{//farfadets
                            System.out.print("\nChoisissez votre cible (entre Joueur2 ~ " + nbjoueur +") : \n> "); 
                            int choix3 = input.nextInt();
                            while(choix3<2 || choix3>nbjoueur){
                                System.out.print("\nVeuillez choisir un cible valide\n> ");
                                choix3 = input.nextInt();
                            }
                            int graineAvoler = 0;
                            choix3-=1; //car la table joueur commence par joueur[0]
                            if(carteIngredient.get(choix).valeursFarfadets[i]<=champ[choix3].nbGraine){ //champ[choix3].nbGraine : Nombre graines de l'adversaire ciblé
                                graineAvoler=carteIngredient.get(choix).valeursFarfadets[i];
                            }
                            else{
                                graineAvoler=champ[choix3].nbGraine;
                            }

                            System.out.println("\nVous avez volé " + graineAvoler + " graine(s) du Joueur" + (choix3 + 1) + "\n");//Carte choisie au tour j au saison i
                            champ[0].ajouter("graine", graineAvoler);
                            champ[choix3].enlever("graine", graineAvoler);
                            break;
                        }
                        default: {
                            System.out.println("Veuillez choisir une action valide(entre 1 et 3)");
                            choix2 = input.nextInt();
                        }
                    }
                }while(choix2<1 || choix2>3);
                carteIngredient.set(choix,null);
            }
            else{
                System.out.println("\nVeuillez choisir une carte valide");
            }
        }while(choixnull);
    }
}

