/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partie;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * <b>Main est la classe qui permet d'executer le jeu Menhir.</b>
 * 
 * @author Chihoon Lee & Lam Pham
 * @version 1.0
 *
 *
 */
public class Main {
    private static Scanner input;
    
    /**
    * Méthode main qui permet d'executer la partie.
    * @param args
    */
    public static void main(String[] args) {
        input = new Scanner(System.in);
        System.out.println("Bienvenu au jeu Menhir!\n");
        //Nom du joueur
        System.out.print("Quel est votre nom? \n> ");
        String nomJoueur = input.nextLine();
        
        //Mode de Jeu
        System.out.print("Choisissez le mode de jeu : 1. Partie rapide 2. Partie avancée\n> ");
        int modeJeu = 0;
        try{
            modeJeu = input.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Saisie Incorrecte.\n");
            main(args);
        }
        if(modeJeu>2){
            System.out.println("Le nombre doit être entre 1 et 2\n");
            main(args);
        }
        if(modeJeu==1){
            System.out.println("Vous avez choisi la partie rapide\n");
        }
        else{
            System.out.println("Vous avez choisi la partie avancée\n");
        }
        
        //Nombre de joueurs
         System.out.print("Choisissez le nombre de joueurs virtuels (entre 1 et 5) : \n> ");
        int nbJoueur = 0;
        try{
            nbJoueur = input.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Saisie Incorrecte.\n");
            main(args);
        }
        if(nbJoueur>5){
            System.out.println("Le nombre doit être inférieur ou égal à 5\n");
            main(args);
        }
        System.out.println("Vous avez choisi de joueur contre "+nbJoueur+" joueurs virtuels\n");
        nbJoueur++; //pour avoir le nombre total de joueurs (y compris le joueur reel)
        
        Partie nouvellePartie = new Partie(nomJoueur,nbJoueur,modeJeu);
        nouvellePartie.lancerPartie();
    }
}
