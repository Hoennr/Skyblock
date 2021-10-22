package fr.ohnreihen.skyblock.Joueur.exceptionPerso;

@SuppressWarnings("serial")
public class ProduitInexistantException extends Exception {
	
	public ProduitInexistantException() {

		super("Le produit n'existe pas");
	}

}
