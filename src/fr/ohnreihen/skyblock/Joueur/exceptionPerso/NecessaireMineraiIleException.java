package fr.ohnreihen.skyblock.Joueur.exceptionPerso;

@SuppressWarnings("serial")
public class NecessaireMineraiIleException  extends Exception{

	public NecessaireMineraiIleException() {
		
		super("Le joueur n'a pas le necessaire pour upgrader les minerais de son ile");
	}
}
