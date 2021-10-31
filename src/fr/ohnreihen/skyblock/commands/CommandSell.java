package fr.ohnreihen.skyblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.ohnreihen.skyblock.Joueur.Joueur;

public class CommandSell implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		
		if(sender instanceof Player) {
			
			if(cmd.getName().equalsIgnoreCase("sell")) {
				Player player = (Player) sender;
				if(arg3.length==0) {
					ItemStack itemTenue = player.getInventory().getItemInMainHand();
					Joueur.vendreItem(player, itemTenue);
					
				
				}else if (arg3[0].equalsIgnoreCase("all")) {
					Joueur.toutVendre(player);
					
				}
				
			}
			return true;
		}else return false;

		
	}

}
