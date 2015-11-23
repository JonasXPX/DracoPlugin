package me.jonasxpx.meuplugin2.comandos.warps;

import me.jonasxpx.meuplugin2.managers.StringUtils;
import me.jonasxpx.meuplugin2.managers.Warp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.util.ChatPaginator;
import org.bukkit.util.ChatPaginator.ChatPage;

public class ListWarps implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("§6Todas as Warps: ");
		for(String st : Warp.getWarps()){
			sb.append("§f" + st + "§6, ");
		}
		sb.append(".");
		if(args.length == 0){
			ChatPage cp = ChatPaginator.paginate(sb.toString(), 1);
			for(String sx : cp.getLines())sender.sendMessage(sx);
			sender.sendMessage("§6Página 1 de " + cp.getTotalPages());
			return true;
		}
		try{
			ChatPage cp = ChatPaginator.paginate(sb.toString(), Integer.parseInt(args[0]));
			 sender.sendMessage(StringUtils.parceString(cp.getLines()) + "\n§6Página " + args[0] + " de " + cp.getTotalPages());
		}catch(NumberFormatException e){}
		return true;
	}
}
