package net.snailz.portalcommand;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Snailz
 */
public class PortalCommand extends JavaPlugin implements CommandExecutor, Listener{
    @Override
    public void onEnable(){
        this.saveDefaultConfig();
        
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("pc")){
            if (args[0].equalsIgnoreCase("p1")){
                Player player = (Player) sender;
                int pos1x = (int) player.getLocation().getX();
                this.getConfig().set(args[1], pos1x);
                int pos1y = (int) player.getLocation().getY();
                this.getConfig().set(args[1], pos1y);
                int pos1z = (int) player.getLocation().getZ();
                this.getConfig().set(args[1], pos1z);
                player.sendMessage(Color.YELLOW + "[PortalCommand] " + Color.GREEN + "Position 1 Set for " + args[1] + "!");
                this.saveConfig();
                return true;
            } else if (args[0].equalsIgnoreCase("p2")){
                Player player = (Player) sender;
                int pos1x = (int) player.getLocation().getX();
                this.getConfig().set("regions." + args[1], pos1x);
                int pos1y = (int) player.getLocation().getY();
                this.getConfig().set("regions." + args[1], pos1y);
                int pos1z = (int) player.getLocation().getZ();
                this.getConfig().set("regions." + args[1], pos1z);
                player.sendMessage(ChatColor.YELLOW + "[PortalCommand] " + ChatColor.GREEN + "Position 2 Set for " + args[1] + "!");
                this.saveConfig();
                return true;
            } else if (args[0].equalsIgnoreCase("command")){
                this.getConfig().set("regions." + args[1], args[2]);
                sender.sendMessage(ChatColor.YELLOW + "[PortalCommand] " + ChatColor.GREEN + "Command Set for " + args[1] + "!");
                this.saveConfig();
                
            } else if (args[0].equalsIgnoreCase("help")){
                sender.sendMessage(ChatColor.BLUE + "----------" + ChatColor.GREEN + "PortalCommand Help" + ChatColor.BLUE + "----------");
                sender.sendMessage(ChatColor.BLUE + "help:");
                sender.sendMessage(ChatColor.GREEN + "Syntax: /pc help");
                sender.sendMessage(ChatColor.GREEN + "Use: Show this page");
                sender.sendMessage(ChatColor.BLUE + "pl:");
                sender.sendMessage(ChatColor.GREEN + "Syntax: /pc p1 <portalname>");
                sender.sendMessage(ChatColor.GREEN + "Use: Set position 1 of a portal");
                sender.sendMessage(ChatColor.BLUE + "p2:");
                sender.sendMessage(ChatColor.GREEN + "Syntax: /pc p2 <portalname>");
                sender.sendMessage(ChatColor.GREEN + "Use: Set position 2 of a portal");
                sender.sendMessage(ChatColor.BLUE + "command:");
                sender.sendMessage(ChatColor.GREEN + "Syntax: /pc command <portalcommand> <command>");
                sender.sendMessage(ChatColor.GREEN + "Use: Set the command to be executed when a player enters the portal");
                return true;
            }
        }
        return false;
    }
    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event){
        if (event.getPlayer().hasPermission("portalcommand.use")){
            
        }
            
        
    }
    
}
   
