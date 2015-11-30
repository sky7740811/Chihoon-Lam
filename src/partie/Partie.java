
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
    
  
    public static ArrayList<Ingredient> carteIngredient = new ArrayList<Ingredient>();
    public static ArrayList<Alliee> carteAlliee = new ArrayList<Alliee>();
    public static ArrayList<Champ> listechamp = new ArrayList<Champ>();

    /**
     *
     */
    public static ArrayList<Integer> ordreJoueur = new ArrayList<>();
    public static ArrayList<Boolean> aDejaJouerPremier = new ArrayList<>();//pour ne pas qu'un joueur commence 2 ou plusieurs fois en premier au debut d'une manche
   
    
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
            ordreJoueur.add(i);
            aDejaJouerPremier.add(false);
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
    
    public void debutManche(){
        
        Collections.shuffle(ordreJoueur);//pour chaque debut de manche on melange l'ordre des joueurs
        if(aDejaJouerPremier.get(ordreJoueur.get(0))){//verification si le joueur qui commence a deja jouer en premier
            debutManche();
        }
        else{
            aDejaJouerPremier.set(ordreJoueur.get(0),true);
        }
        Champ champ;
        for(int i=0;i<this.getNbJoueur();i++){
            champ = new Champ();
            listechamp.add(champ);
        }
        if(this.getModeJeu()==1){
            //2 cailloux pour chaque joueur
            
            for(int i=0;i<this.getNbJoueur();i++){
                listechamp.get(i).ajouter("graine", 2);
            }
        }
        else{
        }
        deroulementManche();
    }
    
    public void deroulementManche(){
        for(int j=0;j<ordreJoueur.size();j++){
            System.out.println("Derp "+ordreJoueur.get(j));
        }
               
        int isaison = 0; //compteur pour saison
        while(isaison<=3){
        System.out.println("\nSaison : " +saison[isaison]);
            for(int j=0;j<ordreJoueur.size();j++){
               System.out.print("\nTour : " + collectionJoueurs.get(ordreJoueur.get(j)).getNomJoueur() + "\n\n");
               

               //*******************Tour du Joueur reel********************************
               if(ordreJoueur.get(j)==0){ 
                   //Etat des champs de tous les joueurs
                    for(int k=0;k<this.getNbJoueur();k++){
                    System.out.println("\n"+collectionJoueurs.get(k).getNomJoueur()+" : ");
                    listechamp.get(k).afficher();
                    }

                    System.out.println("\nVotre main: ");
                    for(int k=0;k<4;k++){
                        if(carteIngredient.get(k)!=null){
                            System.out.println("Carte "+ (k+1));
                            carteIngredient.get(k).afficher();
                        }
                    }
                    collectionJoueurs.get(0).jouerCarte(carteIngredient,listechamp,this.getNbJoueur(),isaison);
                }
               //****************************Tour des joueurs virtuels**********************************
                else{
                   collectionJoueurs.get(ordreJoueur.get(j)).jouerCarte(carteIngredient,listechamp,this.getNbJoueur(),isaison);
                }   
            }//fin saison
            isaison++;
        }//fin partie
        for(int k=0;k<this.getNbJoueur();k++){
                    System.out.println("\n"+collectionJoueurs.get(k).getNomJoueur()+" : ");
                    listechamp.get(k).afficher();
        }
    }
    public void lancerPartie(){
  
        /*-----------------------Partie Rapide----------------------------------*/
        //Creation des cartes
        if(modeJeu==1){
            manche=1;
        }
        else{
            manche=this.getNbJoueur();
        }
        for(int i=0;i<manche;i++){
        //Creation des cartes
        this.creerDeck();
        if(modeJeu==2){
            System.out.println("Manche "+manche+": ");
        }
        this.debutManche();
        }
        
    
           
        System.out.println("\nLe vainqueur est "+collectionJoueurs.get(estGagnant()).getNomJoueur()+ "!");
        
        //**************Partie avancée**************************************
      
    }
    
    public int estGagnant(){
        int max=0;
       // max : id joueur ayant le plus de points
        for(int i=1; i<this.getNbJoueur();i++){
            if(listechamp.get(i).nbMenhir>listechamp.get(max).nbMenhir){
                max=i;
            }
        } 
        //Maintenant on connait le max de nombre menhirs obtenus, on verifie si il y a autre joueurs ayant meme nombre de menhirs
        for(int i=0;i<this.getNbJoueur();i++){
            if(i!=max){//pour ne pas comparer entre soi-meme
                if(listechamp.get(i).nbMenhir==listechamp.get(max).nbMenhir){ //Si on trouve deux joueurs ayant meme nombre de menhirs, on compare leur nombre de graines
                    if(listechamp.get(i).nbGraine>listechamp.get(max).nbGraine){
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
