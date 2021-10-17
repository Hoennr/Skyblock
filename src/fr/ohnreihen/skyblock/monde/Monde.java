package fr.ohnreihen.skyblock.monde;

import java.io.File;
import java.io.IOException;

import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.entity.Player;

public class Monde {

	private String nomMonde;
	private long valeurMonde = 0;
	private float vitesseSpawn = 1;
	private float chanceDoubleLoot = 0;
	private int niveauMinerai = 0; //lv0= stone / lv1=charbon / lv2= redstone / lvl3= gold / lvl4= iron / lvl5= diamand
	//probabilité de minerai en fonction du niveau
	private float multiplicateurValeurItem = 1;
	public static final String TYPE_PVE= "PVE";
	public static final String TYPE_ILE= "ILE";
	
	
	
	
	
	public float getChanceDoubleLoot() {
		return chanceDoubleLoot;
	}public void setChanceDoubleLoot(float chanceDoubleLoot) {
		this.chanceDoubleLoot = chanceDoubleLoot;
	}public float getMultiplicateurValeurItem() {
		return multiplicateurValeurItem;
	}public void setMultiplicateurValeurItem(float multiplicateurValeurItem) {
		this.multiplicateurValeurItem = multiplicateurValeurItem;
	}public int getNiveauMinerai() {
		return niveauMinerai;
	}public void setNiveauMinerai(int niveauMinerai) {
		this.niveauMinerai = niveauMinerai;
	}public String getNomMonde() {
		return nomMonde;
	}public void setNomMonde(String nomMonde) {
		this.nomMonde = nomMonde;
	}public long getValeurMonde() {
		return valeurMonde;
	}public void setValeurMonde(long valeurMonde) {
		this.valeurMonde = valeurMonde;
	}public float getVitesseSpawn() {
		return vitesseSpawn;
	}public void setVitesseSpawn(float vitesseSpawn) {
		this.vitesseSpawn = vitesseSpawn;
	}
	
	
	public static World creerMonde(Player player, String type) {
		
		WorldCreator wc= new WorldCreator("");
		
		if (type.equals(TYPE_PVE)) {
			wc= new WorldCreator(TYPE_PVE + player.getDisplayName());
			wc.environment(Environment.NORMAL);
			wc.type(WorldType.NORMAL);
			
		} else if (type.equals(TYPE_ILE)) {
			
			wc= new WorldCreator(TYPE_ILE + player.getDisplayName());
			
		}
		return wc.createWorld();				
	}
	
	public static void creerIle(Player player) {
		
		try {
			FileUtils.copyDirectory(new File("backup skyblock map debut"), new File(Monde.TYPE_ILE+player.getDisplayName()));
		} catch (IOException e) {
			System.out.println("Le monde de bakup ile n'a pas pu etre copié");
			e.printStackTrace();
		}
		
	}
}