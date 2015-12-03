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
    
    public void jouerCarte(ArrayList<Ingredient> carteIngredient, ArrayList<Champ> champ, int nbjoueur,int saison,int modeJeu, Alliee carteAlliee){ //i : saison

      int choix = 0;
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
                    jouerCarte(carteIngredient, champ, nbjoueur, saison, modeJeu, carteAlliee); 
                }
                if(cible<2 || cible>nbjoueur){
                    System.out.println("Veuillez cibler entre le joueur 2 et "+ nbjoueur);
                    jouerCarte(carteIngredient, champ, nbjoueur, saison, modeJeu, carteAlliee); 
                }
                else{
                    cible-=1; 
                    carteAlliee.detruireMenhir(carteAlliee.valeurs[saison],champ.get(cible));
                    System.out.println("Vous avez détruit "+carteAlliee.getMenhirDetruits()+" menhir(s) du Joueur"+ (cible+1)+ "\n");
                    this.setaPiocheAlliee(false);
                    jouerCarte(carteIngredient, champ, nbjoueur, saison, modeJeu, carteAlliee); 
                }
            }
            else{
                choix -= 1;

                if(carteIngredient.get(choix).estUtilise==false){ //Si la carte ingredient choisie existe dans la main
                    //choixnull = false;
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
                        jouerCarte(carteIngredient, champ, nbjoueur, saison, modeJeu, carteAlliee);
                    }
                    if(choix2==1){
                        System.out.println("\nVous avez récupéré " + carteIngredient.get(choix).valeursGeant[saison]+ " graine(s)\n");//Carte choisie au tour j au saison i
                        champ.get(0).ajouter("graine", carteIngredient.get(choix).valeursGeant[saison]);
                    }
                    else if(choix2==2){
                        int menhirApousser = 0;
                        if(carteIngredient.get(choix).valeursEngrais[saison]<=champ.get(0).nbGraine){
                        menhirApousser = carteIngredient.get(choix).valeursEngrais[saison];
                        }
                        else{
                                menhirApousser = champ.get(0).nbGraine;
                            }
                        System.out.println("\nVous avez poussé " + menhirApousser + " menhir(s)\n");//Carte choisie au tour j au saison i
                        champ.get(0).ajouter("menhir", menhirApousser);
                        champ.get(0).enlever("graine", menhirApousser);
                    }
                    else if(choix2==3){
                        System.out.print("\nChoisissez votre cible (entre Joueur2 ~ " + nbjoueur +") : \n> "); 
                        int choix3 = input.nextInt();
                        while(choix3<2 || choix3>nbjoueur){
                            System.out.print("\nVeuillez choisir un cible valide\n> ");
                            choix3 = input.nextInt();
                        }
                        int graineAvoler = 0;
                        choix3-=1; //car la table joueur commence par joueur[0]
                        if(carteIngredient.get(choix).valeursFarfadets[saison]<=champ.get(choix3).nbGraine){ //champ[choix3].nbGraine : Nombre graines de l'adversaire ciblé
                            graineAvoler=carteIngredient.get(choix).valeursFarfadets[saison];
                        }
                        else{
                            graineAvoler=champ.get(choix3).nbGraine;
                        }

                        System.out.println("\nVous avez volé " + graineAvoler + " graine(s) du Joueur" + (choix3 + 1) + "\n");//Carte choisie au tour j au saison i
                        champ.get(0).ajouter("graine", graineAvoler);
                        champ.get(choix3).enlever("graine", graineAvoler);
                    }
                    else{
                        System.out.println("Veuillez choisir entre 1 et 3");
                        jouerCarte(carteIngredient, champ, nbjoueur, saison, modeJeu,carteAlliee);
                    }
                carteIngredient.get(choix).setUsage(true);
                }
                else{
                    System.out.println("\nVeuillez choisir une carte valide");
                    jouerCarte(carteIngredient, champ, nbjoueur, saison, modeJeu, carteAlliee); 
                }
            }
      //  }while(choixnull);
    }
}

