package net.snailz.portalcommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
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
                int pos1 = (int) player.getLocation().getX();
                this.getConfig().set(args[1], pos1); 
            } //Do Pos2
        }
        return false;
    }
   
}
