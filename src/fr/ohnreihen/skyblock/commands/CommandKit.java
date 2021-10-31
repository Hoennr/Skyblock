package fr.ohnreihen.skyblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.ohnreihen.skyblock.Joueur.Joueur;

public class CommandKit implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		
		if(sender instanceof Player) {
			if(arg1.getName().equalsIgnoreCase("kit")) {
				Player player = (Player) sender;
				Joueur.donnerKit(player);
			}
			return true;
		}else return false;

	}

}
