package fr.ohnreihen.skyblock.Joueur.exceptionPerso;

@SuppressWarnings("serial")
public class MoneyInsuffisantException extends Exception{
	
	public MoneyInsuffisantException() {

		super("Le joueur n'a pas suffisament de money");
	}
}
