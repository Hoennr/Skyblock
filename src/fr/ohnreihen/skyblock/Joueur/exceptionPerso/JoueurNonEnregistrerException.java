package fr.ohnreihen.skyblock.Joueur.exceptionPerso;

@SuppressWarnings("serial")
public class JoueurNonEnregistrerException extends Exception {
	
	public JoueurNonEnregistrerException() {

		super("Le joueur n'est pas encore enregistré !");
	}
	
}
