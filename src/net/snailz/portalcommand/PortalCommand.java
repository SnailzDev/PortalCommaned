package net.snailz.portalcommand;

import java.util.Vector;
import javax.swing.border.Border;
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
                return true;
            } else if (args[0].equalsIgnoreCase("p2")){
                Player player = (Player) sender;
                int pos1x = (int) player.getLocation().getX();
                this.getConfig().set(args[1], pos1x);
                int pos1y = (int) player.getLocation().getY();
                this.getConfig().set("regions." + args[1], pos1y);
                int pos1z = (int) player.getLocation().getZ();
                this.getConfig().set(args[1], pos1z);
                player.sendMessage(Color.YELLOW + "[PortalCommand] " + Color.GREEN + "Position 2 Set for " + args[1] + "!");
                return true;
            } else if (args[0].equalsIgnoreCase("command")){
                Player player = (Player) sender;
                this.getConfig().set(args[1], args[2]);
                player.sendMessage(Color.YELLOW + "[PortalCommand] " + Color.GREEN + "Command Set for " + args[1] + "!");
                
            } else if (args[0].equalsIgnoreCase("help")){
                Player player = (Player) sender;
                player.sendMessage(Color.BLUE + "----------" + Color.GREEN + "PortalCommand Help" + Color.BLUE + "----------");
                player.sendMessage(Color.BLUE + "p1:");
                player.sendMessage(Color.GREEN + "Syntax: /pc help");
                player.sendMessage(Color.GREEN + "Use: Show this page");
                player.sendMessage(Color.GREEN + "Syntax: /pc p1 <portalname>");
                player.sendMessage(Color.GREEN + "Use: Set position 1 of a portal");
                player.sendMessage(Color.GREEN + "Syntax: /pc p2 <portalname>");
                player.sendMessage(Color.GREEN + "Use: Set position 2 of a portal");
                //Add more help here
                player.sendMessage(Color.BLUE + "----------------------------");
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
   
