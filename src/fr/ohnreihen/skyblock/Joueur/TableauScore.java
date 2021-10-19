package fr.ohnreihen.skyblock.Joueur;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class TableauScore {

	public static void creerTableau(Joueur joueur) {
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard tableau = manager.getNewScoreboard();
		
		String titre = "Scorebooard";
		String criteria = "dummy";
		String nom = "§3§lMes scores";
		
		Objective obj = tableau.registerNewObjective(titre, criteria, nom);
		obj.setDisplaySlot(DisplaySlot.SIDEBAR); 
		
		
		String ligne8 = "§lᚚᚚᚚᚚᚚᚚᚚᚚᚚᚚ";
		Score score8 = obj.getScore(ligne8);
		score8.setScore(8);
		String ligne7 = " §b❙§rMoney : §b"+joueur.getMoney()+"￥";
		Score score7 = obj.getScore(ligne7);
		score7.setScore(7);
		String ligne6 = " §b❙§rEclat : §b"+joueur.getEclat()+"";
		Score score6 = obj.getScore(ligne6);
		score6.setScore(6);
		String ligne5 = " §b❙§rExp : §b"+ joueur.getPlayer().getTotalExperience();
		Score score5 = obj.getScore(ligne5);
		score5.setScore(5);
		String ligne4 = " §b❙§rChutes : §b" + joueur.getScoreChute();
		Score score4 = obj.getScore(ligne4);
		score4.setScore(4);
		String ligne3 = " ";
		Score score3 = obj.getScore(ligne3);
		score3.setScore(3);
		String ligne2 = "§3§lMon Ile";
		Score score2 = obj.getScore(ligne2);
		score2.setScore(2);
		String ligne1 = " §b§rTaille île : §b "+joueur.getMondeIle().getTaille()+"x"+joueur.getMondeIle().getTaille();
		Score score1 = obj.getScore(ligne1);
		score1.setScore(1);
		String ligne0 = "";
		Score score0 = obj.getScore(ligne0);
		score0.setScore(0);
		//String ligne7;
		//String ligne6;
		//String ligne5;
		//String ligne4;
		//String ligne3;
		//String ligne2;
		//String ligne1;
		
		//Score score = obj.getScore("  §b▌§rChutes : " + joueur.getScoreChute() );
		//score.setScore(1);
		//Score score2 = obj.getScore("  §b▌§rExp : " + joueur.getPlayer().getTotalExperience());
		//score2.setScore(0);
		
		joueur.getPlayer().setScoreboard(tableau);
		System.out.println("Tableau créer pour le joueur " + joueur.getPlayer().getName() );
		System.out.println("Il a " + joueur.getScoreChute() + " chutes au tableau" );


		
	}
	
}
