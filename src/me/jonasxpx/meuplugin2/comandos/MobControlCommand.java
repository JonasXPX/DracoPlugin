package me.jonasxpx.meuplugin2.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.jonasxpx.meuplugin2.mobcontrol.MobControl;

public class MobControlCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("§cNão pode ser executado pelo console.");
			return true;
		}
		if(!sender.isOp() || !sender.hasPermission("dracoplugin.mobcontrol")){
			sender.sendMessage("§cSem permissão");
			return true;
		}
		if(args.length == 2){
			if(args[0].equalsIgnoreCase("set")){
				if(EntityType.fromName(args[1]) == null){
					sender.sendMessage("§cNome inválido.");
					return true;
				}
				MobControl.save(EntityType.fromName(args[1]), ((Player)sender).getLocation());
				sender.sendMessage("§bSalvo com sucesso.");
				return true;
			}
			if(args[0].equalsIgnoreCase("remove")){
				MobControl.delete(Integer.parseInt(args[1]));
				return true;
			}
		}
		if(args.length == 1){
			if(args[0].equalsIgnoreCase("list")){
				sender.sendMessage(MobControl.toStaticString());
				return true;
			}
		}
		sender.sendMessage("§c/mobcontrol set <mobtype>|remove <id>|list ");
		return true;
	}
	
}
