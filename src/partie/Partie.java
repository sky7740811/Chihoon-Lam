
package partie;
import java.util.Arrays;
import java.util.Scanner;
import carte.Ingredient;
import joueur.Joueur;
import joueur.JoueurReel;
import joueur.JoueurVirtuel;
import strategy.Debutant;
       

public class Partie {
    //modeJeu : 1 = Partie rapide ; 2 = Partie avancée
    public int nbJoueur;
    public int modeJeu;
    public int niveauJeu;
    public String saison[] = {"Printemps","Ete","Automne","Hiver"};
    public int manche;
    public int tour;
    public int joueurActuel;
    public static Partie p = new Partie();
    public Scanner input = new Scanner(System.in);
     
    public Partie(){
        
    }
    
    public static void main(String[] args) {
     p.lancerPartie();
        
        
    }
    
    public void setModeJeu(){
        System.out.print("Choisissez le mode de jeu : 1. Partie rapide 2. Partie avancée\n> ");
        modeJeu = input.nextInt();
        while(modeJeu<1 || modeJeu>2){
            System.out.print("Tapez 1 ou 2 pour choisir le mode\n> ");
            modeJeu = input.nextInt();
        }
    }
    
    public int getModeJeu(){
        return modeJeu;
    }
        
    public void setNbJoueur(){
        System.out.print("Choisissez le nombre de joueur (entre 2 et 6) : \n> ");
        nbJoueur = input.nextInt();
        while(nbJoueur<2 || nbJoueur>6){
            System.out.print("Le nombre de joueurs doit être entre 2 et 6\n> ");
            nbJoueur = input.nextInt();
        }
    }
    
    public int getNbJoueur(){
        return nbJoueur;
    }
    
    public void setNiveauJeu(){
        System.out.print("Choisissez le niveau de jeu : 1. Facile 2. Difficile\n> ");
        niveauJeu = input.nextInt();
        while(niveauJeu<1 || niveauJeu>2){
            System.out.print("Tapez 1 ou 2 pour choisir le niveau\n> ");
            niveauJeu = input.nextInt();
        }
    }
    
    public int getNiveauJeu(){
        return niveauJeu;
    }
    
  /*  public void distribuerCartes(){
        if(this.getModeJeu()==1){
            int nbcartes = this.getNbJoueur()*4;
            Ingredient carteIngredient[] = new Ingredient[nbcartes];
            for(int i=0;i<nbcartes;i++){ //joueur1 : carte[0]~carte[3] , joueur2: carte[4]~carte[7] ...
                carteIngredient[i] = new Ingredient();            
            }
        }
        else{
        }
    }*/
    
    public void lancerPartie(){
        System.out.println("Bienvenu au jeu Menhir!");
        this.setModeJeu();
        this.setNbJoueur();
        this.setNiveauJeu();
        //this.distribuerCartes();
       
        //Nom joueur
        System.out.print("Insérez votre nom: \n> ");
        String nomJoueur = input.next();
      
        Joueur joueur[] = new Joueur[this.getNbJoueur()];
        joueur[0] = new JoueurReel(nomJoueur, 1);
        
        if(this.getNiveauJeu()==1){
            for(int i=1;i<this.getNbJoueur();i++){
                joueur[i] = new JoueurVirtuel("Joueur "+ (i+1), (i+1), new Debutant());                
            }
        }
        else{/*
            for(int i=1;i<this.getNbJoueur();i++){
                joueur[i] = new JoueurVirtuel("Joueur "+ (i+1), (i+1)), new Expert());                
            }*/
        }
           
        
        //***********Partie Rapide******************************
        if(this.getModeJeu()==1){
            //2 cailloux pour chaque joueur
            Champ champ[] = new Champ[this.getNbJoueur()];
            for(int i=0;i<this.getNbJoueur();i++){
                champ[i] = new Champ();
                champ[i].ajouter("graine", 2);
            }
            
            //Creation des cartes
            int nbcartes = this.getNbJoueur()*4;
            Ingredient carteIngredient[] = new Ingredient[nbcartes];
            for(int i=0;i<nbcartes;i++){ //joueur1 : carte[0]~carte[3] , joueur2: carte[4]~carte[7] ...
                carteIngredient[i] = new Ingredient();            
            }
            
            //Deroulement de la partie
            int i = 0; //compteur pour saison
            while(i<=3){
            System.out.println("\nSaison : " +saison[i]);
                int j = 0;  //compteur pour tour
                while(j<this.getNbJoueur()){
                   System.out.print("\nTour : " +joueur[j].getNomJoueur()+ "\n\n");
                   
                   //*******************Tour du Joueur reel********************************
                   if(j==0){ 
                       //Etat des champs de tous les joueurs
                        for(int k=0;k<this.getNbJoueur();k++){
                        System.out.println("\n"+joueur[k].getNomJoueur()+" : ");
                        champ[k].afficher();
                        }
                            
                        System.out.println("\nVotre main: ");
                        for(int k=0;k<4;k++){
                            if(carteIngredient[k]!=null){
                                System.out.println("Carte "+ (k+1));
                                carteIngredient[k].afficher();
                            }
                        }
                        joueur[0].jouerCarte(carteIngredient,champ,this.getNbJoueur(),i);
                    }
                   //****************************Tour des joueurs virtuels**********************************
                    else{
                       joueur[j].jouerCarte(carteIngredient,champ,this.getNbJoueur(),i);
                    }
                    j++;   
                }//fin saison
                i++;
            }//fin partie
            for(int k=0;k<this.getNbJoueur();k++){
                        System.out.println("\n"+joueur[k].getNomJoueur()+" : ");
                        champ[k].afficher();
            }
            
           
            System.out.println("\nLe vainqueur est "+joueur[estGagnant(champ)].getNomJoueur()+ "!");
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
