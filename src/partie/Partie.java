
package partie;
import carte.Carte;
import java.util.Arrays;
import java.util.Scanner;
import carte.Ingredient;
import joueur.Joueur;
import joueur.JoueurReel;
import joueur.JoueurVirtuel;
import strategy.Debutant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.LinkedList;
import carte.Ingredient;
import carte.Alliee;
import carte.TaupeGeante;
import carte.ChienDeGarde;
       

public class Partie {
    //modeJeu : 1 = Partie rapide ; 2 = Partie avancée
    public static int nbJoueur;
    public static int modeJeu;
    public int niveauJeu;
    public String saison[] = {"Printemps","Ete","Automne","Hiver"};
    public int manche;
    public int tour;
    public static int joueurActuel;
    public static ArrayList<Ingredient> carteIngredient = new ArrayList<Ingredient>();
    public static ArrayList<Alliee> carteAlliee = new ArrayList<Alliee>();
    public Scanner input = new Scanner(System.in);
    
    /**
    * Liste des joueurs
    */
    public static ArrayList<Joueur> collectionJoueurs = new ArrayList<Joueur>();
     
    /*-----------------------------------------CONSTRUCTEUR----------------------------------------------------------*/

	/**
	 * Constructeur Partie
	 * 
	 * <p>Permet d'initialiser le jeu. 3 paramètres doivent être passés :
	 * <ul>
	 * <li>nomJoueur : Nom du joueur réel de la partie</li>
	 * <li>nbJoueur : Nombre de joueurs virtuels dans la partie</li>
         * <li>modeJeu : Mode de jeu dans la partie</li>
	 * </ul>
	 * 
	 * @param nomJoueur
	 * @param nbJoueur
         * @param modeJeu
	 */
    
    public Partie(String nomJoueur, int nbJoueur, int modeJeu){
        Partie.nbJoueur = nbJoueur;
        Partie.modeJeu = modeJeu;
        String[] nomJoueurs = {nomJoueur, "Joueur 2", "Joueur 3", "Joueur 4", "Joueur 5", "Joueur 6"};
        Joueur joueur;
        
        
        for(int i = 0; i <nbJoueur; i++){
            if(i == 0){
                joueur = new JoueurReel(nomJoueurs[i],1);
            }
            else{
                joueur = new JoueurVirtuel(nomJoueurs[i], (i+1), new Debutant());
            }                
            collectionJoueurs.add(joueur);
        }
            
    }
    /*-----------------------Creation des cartes-------------------------------------*/
    
     /**
     * On crée 4 * nbjoueurs cartes ingrédients .
     */
    public void creerDeck(){
        Ingredient ingredient;
        Alliee alliee;
        int nbcartesIngredient = this.getNbJoueur()*4;
        for(int i = 0; i<nbcartesIngredient; i++){
            ingredient = new Ingredient();
            carteIngredient.add(ingredient);
        }
        /**
         * pour partie avancée, on créera 6 cartes alliées dont 3 geants 3 chiens de garde.
         */
        if(modeJeu==2){ 
            for(int i = 0; i<3; i++){
                alliee = new TaupeGeante();
                carteAlliee.add(alliee);
            }
            for(int i = 0; i<3; i++){
                alliee = new ChienDeGarde();
                carteAlliee.add(alliee);
            }
        } 
    }

    public int getModeJeu(){
        return modeJeu;
    }
 
    
    public int getNbJoueur(){
        return nbJoueur;
    }
    
    
    public int getNiveauJeu(){
        return niveauJeu;
    }
    
    
    public void lancerPartie(){
  
        /*-----------------------Partie Rapide----------------------------------*/
        if(this.getModeJeu()==1){
            //2 cailloux pour chaque joueur
            Champ champ[] = new Champ[this.getNbJoueur()];
            for(int i=0;i<this.getNbJoueur();i++){
                champ[i] = new Champ();
                champ[i].ajouter("graine", 2);
            }
            
            //Creation des cartes
           this.creerDeck();
            /*
            int nbcartes = this.getNbJoueur()*4;
            Ingredient carteIngredient[] = new Ingredient[nbcartes];
            for(int i=0;i<nbcartes;i++){ //joueur1 : carte[0]~carte[3] , joueur2: carte[4]~carte[7] ...
                carteIngredient[i] = new Ingredient();            
            }*/
            
            //Deroulement de la partie
            int i = 0; //compteur pour saison
            while(i<=3){
            System.out.println("\nSaison : " +saison[i]);
                int j = 0;  //compteur pour tour
                while(j<this.getNbJoueur()){
                   System.out.print("\nTour : " + collectionJoueurs.get(j).getNomJoueur() + "\n\n");
                   
                   //*******************Tour du Joueur reel********************************
                   if(j==0){ 
                       //Etat des champs de tous les joueurs
                        for(int k=0;k<this.getNbJoueur();k++){
                        System.out.println("\n"+collectionJoueurs.get(k).getNomJoueur()+" : ");
                        champ[k].afficher();
                        }
                            
                        System.out.println("\nVotre main: ");
                        for(int k=0;k<4;k++){
                            if(carteIngredient.get(k)!=null){
                                System.out.println("Carte "+ (k+1));
                                carteIngredient.get(k).afficher();
                            }
                        }
                        collectionJoueurs.get(0).jouerCarte(carteIngredient,champ,this.getNbJoueur(),i);
                    }
                   //****************************Tour des joueurs virtuels**********************************
                    else{
                       collectionJoueurs.get(j).jouerCarte(carteIngredient,champ,this.getNbJoueur(),i);
                    }
                    j++;   
                }//fin saison
                i++;
            }//fin partie
            for(int k=0;k<this.getNbJoueur();k++){
                        System.out.println("\n"+collectionJoueurs.get(k).getNomJoueur()+" : ");
                        champ[k].afficher();
            }
            
           
            System.out.println("\nLe vainqueur est "+collectionJoueurs.get(estGagnant(champ)).getNomJoueur()+ "!");
        }
        //**************Partie avancée**************************************
        else{//partie avancee
            
        }
    }
    
    public int estGagnant(Champ[] champ){
        int max=0;
       // max : id joueur ayant le plus de points
        for(int i=1; i<champ.length;i++){
            if(champ[i].nbMenhir>champ[max].nbMenhir){
                max=i;
            }
        } 
        //Maintenant on connait le max de nombre menhirs obtenus, on verifie si il y a autre joueurs ayant meme nombre de menhirs
        for(int i=0;i<champ.length;i++){
            if(i!=max){//pour ne pas comparer entre soi-meme
                if(champ[i].nbMenhir==champ[max].nbMenhir){ //Si on trouve deux joueurs ayant meme nombre de menhirs, on compare leur nombre de graines
                    if(champ[i].nbGraine>champ[max].nbGraine){
                        max=i;
                    }
                }
            }
        }
        return max;
    }
    
    //public void partieRapide(){}
    
    
    //public void partieAvancee(){}
    
    //public int aQuileTour(){}
}
