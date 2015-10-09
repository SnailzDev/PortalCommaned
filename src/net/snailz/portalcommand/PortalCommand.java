package net.snailz.portalcommand;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Snailz
 */
public class PortalCommand extends JavaPlugin implements CommandExecutor, Listener{
    private FileConfiguration regions = null;
    private File regionsfile = null;
    
    public void reloadRegionsFile() throws UnsupportedEncodingException {
    if (regionsfile == null) {
    regionsfile = new File(getDataFolder(), "regions.yml");
    }
    regions = YamlConfiguration.loadConfiguration(regionsfile);
 
    // Look for defaults in the jar
    Reader defConfigStream;
    defConfigStream = new InputStreamReader(this.getResource("regions.yml"), "UTF8");
    if (defConfigStream != null) {
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        regions.setDefaults(defConfig);
    }
    
    
}
    
    public FileConfiguration getRegionsFile() throws UnsupportedEncodingException {
    if (regions == null) {
        reloadRegionsFile();
    }
    return regions;
}
    
    public void saveRegionsFile() {
    if (regions == null || regionsfile == null) {
        return;
    }
    try {
        getRegionsFile().save(regionsfile);
    } catch (IOException ex) {
        getLogger().log(SEVERE, "Could not save config to " + regionsfile + " Please report this to Snailz", ex);
    }
}
    public void saveDefaultRegionsFile() {
    if (regionsfile == null) {
        regionsfile = new File(getDataFolder(), "regions.yml");
    }
    if (!regionsfile.exists()) {            
         this.saveResource("regions.yml", false);
     }
}
    @Override
    public void onEnable(){
        this.saveDefaultConfig();
        this.saveDefaultRegionsFile();
    }  
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("pc")){
            if (args[0].equalsIgnoreCase("p1")){
            }if (args[0].equalsIgnoreCase("p2")){
                
            } else if (args[0].equalsIgnoreCase("command")){
                try {
                    if (args.length == 2){
                        int count = this.getRegionsFile().getInt("count");
                        this.getRegionsFile().set("regions." + count + ".command", args[1]);
                        sender.sendMessage(ChatColor.YELLOW + "[PortalCommand] " + ChatColor.GREEN + "Command Set for " + args[1] + "!");
                        count = count + 1;
                        this.getRegionsFile().set("count", count);
                        this.saveRegionsFile();
                        return true;
                    }
                    this.getRegionsFile().set("regions." + args[2] + ".command", args[1]);
                    sender.sendMessage(ChatColor.YELLOW + "[PortalCommand] " + ChatColor.GREEN + "Command Set for " + args[1] + "!");
                    this.saveRegionsFile();
                    return true;
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(PortalCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                
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
                sender.sendMessage(ChatColor.GREEN + "Syntax: /pc command <portalname> <command>");
                sender.sendMessage(ChatColor.GREEN + "Use: Set the command to be executed when a player enters the portal");
                return true;
            }
        }
        return false;
    }
    }

            
            
        

    

   
