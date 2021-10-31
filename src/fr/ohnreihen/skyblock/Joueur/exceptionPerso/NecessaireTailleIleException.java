package fr.ohnreihen.skyblock.Joueur.exceptionPerso;

@SuppressWarnings("serial")
public class NecessaireTailleIleException  extends Exception{

	public NecessaireTailleIleException() {
		
		super("Le joueur n'a pas le necessaire pour upgrader la taille de son ile");
	}
}
