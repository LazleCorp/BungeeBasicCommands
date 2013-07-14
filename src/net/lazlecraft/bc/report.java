package net.lazlecraft.bc;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class report extends Command {
	
	BasicCommands plugin;

	public report(BasicCommands plugin) {
		super("report");
		this.plugin = plugin;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (ProxyServer.getInstance().getConfigurationAdapter().getBoolean("online_mode",true)) {
		if (sender.hasPermission("basiccommands.report")) {
		ProxiedPlayer sndr = (ProxiedPlayer) sender;
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Usage:" + ChatColor.GREEN + " /report <player> <reason>");
		} else if (args.length >= 2) {
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < args.length; i++){
			sb.append(args[i]).append(" ");
			} 
			String reportReason = sb.toString().trim();
			System.out.println(sender + " reported " +  args[0] + " for " + reportReason + " at "+ sndr.getServer().getInfo().getName());
			for (ProxiedPlayer p : plugin.getProxy().getPlayers()) {
				if (p.hasPermission("basiccommands.staff")) {
					p.sendMessage(plugin.prefix + ChatColor.RED + "New report from " + ChatColor.GREEN + sender);
					p.sendMessage(plugin.prefix + ChatColor.GOLD + "Player: " + ChatColor.GREEN + args[0]);
					p.sendMessage(plugin.prefix + ChatColor.GOLD + "Server: " + ChatColor.GREEN + sndr.getServer().getInfo().getName());
					p.sendMessage(plugin.prefix + ChatColor.GOLD + "Reason: " + ChatColor.GREEN + reportReason);
				}
				}
			}
			}
		}	
		}
}