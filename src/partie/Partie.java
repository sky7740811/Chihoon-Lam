
package partie;
import carte.Carte;
import java.util.Arrays;
import java.util.Scanner;
import carte.Ingredient;
import joueur.Joueur;
import joueur.JoueurReel;
import joueur.JoueurVirtuel;
import strategy.Debutant;
import strategy.Strategy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.LinkedList;
import carte.Ingredient;
import carte.Alliee;
import carte.TaupeGeante;
import carte.ChienDeGarde;
import java.util.InputMismatchException;
       

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
            ingredient = new Ingredient(i);
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
    /**
     * Methode debut Manche permet de melanger l'ordre des tours des joueurs et faire le choix depart
     */
    public void debutManche(){
        /*melanger l'ordre des tours*/
        Collections.shuffle(ordreJoueur);
        
        //on melange l'ensemble des cartes ingredients
        Collections.shuffle(carteIngredient);
        for(int i=0;i<carteIngredient.size();i++){ //on remet toutes les cartes en etat non utilisé (car apres une manche elles sont tous en etat utilisé
            carteIngredient.get(i).setUsage(false);
        }
        //verification si le joueur qui commence a deja jouer en premier
        while(aDejaJouerPremier.get(ordreJoueur.get(0))){//true si le joueur a deja commencé en premier 
            Collections.shuffle(ordreJoueur);//On mélange tant qu'on trouve le joueur ayant pas encore commencé en premier
        }
        aDejaJouerPremier.set(ordreJoueur.get(0),true); //Ce joueur ne commencera plus en premier aux prochaines manches

        //Choix départ
        if(this.getModeJeu()==1){ //partie rapide : 2 cailloux pour chaque joueur
            for(int i=0;i<this.getNbJoueur();i++){
                listechamp.get(i).ajouter("graine", 2);
            }
        }
        else{ //Partie avancée
            Collections.shuffle(carteAlliee); // on melange les cartes alliées
            for(int i=0;i<carteAlliee.size();i++){
                carteAlliee.get(i).afficher();
            }
            for(int i=0;i<this.getNbJoueur();i++){
                collectionJoueurs.get(i).setaPiocheAlliee(false);//tous les joueurs perdent leur carte alliee non jouée
                if(collectionJoueurs.get(i).idJoueur==1){
                    System.out.print("\nChoix depart: 1. Prendre 2 graines 2. Piocher une carte alliée\n> ");
                    int choix=0;
                    try{
                        choix = input.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Saisie Incorrecte.\n");
                        debutManche();
                    }
                    if(choix>2){
                        System.out.println("Le nombre doit être inférieur ou égal à 2\n");
                        debutManche();
                    }
                    
                    if(choix==1){
                        System.out.println("Vous avez pris 2 graines");
                        listechamp.get(i).ajouter("graine", 2);
                    }
                    else{
                        System.out.println("Vous avez pioché une carte alliée");
                        collectionJoueurs.get(i).setaPiocheAlliee(true);
                    }
                }
                else{ //cas des joueurs virtuels
                    int choix = collectionJoueurs.get(i).choixDepart();
                     if(choix==1){
                        System.out.println(collectionJoueurs.get(i).getNomJoueur()+" a pris 2 graines");
                        listechamp.get(i).ajouter("graine", 2);
                    }
                    else{
                        System.out.println(collectionJoueurs.get(i).getNomJoueur()+" a pioché une carte alliée");
                        collectionJoueurs.get(i).setaPiocheAlliee(true);
                    }
                }
                      
            }
        }
        deroulementManche();
    }
    
    public void deroulementManche(){
       
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
                        if(carteIngredient.get(k).estUtilise==false){
                            System.out.println("Carte "+ (k+1));
                            carteIngredient.get(k).afficher();
                        }
                    }
                    if(collectionJoueurs.get(0).aPiocheAlliee){//si le joueur a piocher une alliee
                        carteAlliee.get(0).afficher();
                        collectionJoueurs.get(0).jouerCarte(carteIngredient,listechamp,this.getNbJoueur(),isaison,2,carteAlliee.get(0));
                    }
                    else{ //le joueur n'a pas piocher une alliee ou c'est une partie rapide
                       collectionJoueurs.get(0).jouerCarte(carteIngredient,listechamp,this.getNbJoueur(),isaison,1,null); 
                    }
                }
               //****************************Tour des joueurs virtuels**********************************
                else{
                    if(collectionJoueurs.get(ordreJoueur.get(j)).aPiocheAlliee){//si le joueur a piocher une alliee
                        collectionJoueurs.get(ordreJoueur.get(j)).jouerCarte(carteIngredient,listechamp,this.getNbJoueur(),isaison,1,carteAlliee.get(ordreJoueur.get(j)));
                        jouerTaupeGeante(isaison, ordreJoueur.get(j));
                        
                    }
                    else{ //le joueur n'a pas piocher une alliee ou c'est une partie rapide
                        collectionJoueurs.get(ordreJoueur.get(j)).jouerCarte(carteIngredient,listechamp,this.getNbJoueur(),isaison,1,null);
                        jouerTaupeGeante(isaison, ordreJoueur.get(j));
                    }
                }   
            }//fin saison
            System.out.println("\n----------------------Fin "+saison[isaison]+"--------------------------------\n");
            isaison++;
        }//fin partie
        for(int k=0;k<this.getNbJoueur();k++){
                    System.out.println("\n"+collectionJoueurs.get(k).getNomJoueur()+" : ");
                    listechamp.get(k).afficher();
        }
    }
    public void lancerPartie(){
  
       
        //Creation des cartes
        if(modeJeu==1){ //parite rapide 
            manche=1;
        }
        else{
            manche=this.getNbJoueur();
            System.out.println("nbjoueur: "+manche);
        }
        this.creerDeck(); //Creation de l'ensemble des cartes 
        //Initialisation de champ
        Champ champ;
        for(int i=0;i<this.getNbJoueur();i++){
            champ = new Champ();
            listechamp.add(champ);
        }
        
        for(int i=0;i<manche;i++){
            if(modeJeu==2){
                System.out.println("\nManche "+(i+1)+": ");
            }
            debutManche();
            System.out.println("\n------------------------------------Fin Manche"+(i+1)+"-----------------------------------------\n");
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
    
    //Methode permettant le joueur réel à jouer Taupe Geante pendant les tours d'adversaires si il a Taupe Geante en main
    public void jouerTaupeGeante(int saison, int idJoueur){ 
        if(collectionJoueurs.get(0).aPiocheAlliee && carteAlliee.get(0).getType()==1){ // Durant le tour des autres joueurs, si le joueur reel possede un geant
        System.out.print("Souhaitez-vous jouer utiliser votre ");
        carteAlliee.get(0).afficher();
        System.out.print("? (1. oui 2. non)\n> ");
            int choix =input.nextInt();
            while(choix<1 || choix>2){
                System.out.print("Veuillez saisir entre 1 et 2\n> ");
                choix = input.nextInt();
            }
            if(choix==1){
                carteAlliee.get(0).detruireMenhir(carteAlliee.get(0).valeurs[saison],listechamp.get(idJoueur));
                System.out.println("Vous avez détruit "+carteAlliee.get(0).getMenhirDetruits()+" menhir(s) du Joueur"+ (idJoueur+1)+ "\n");
                collectionJoueurs.get(0).setaPiocheAlliee(false);
            }
        }
    }
}
