package net.snailz.regioncommand;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import net.snailz.regioncommand.cuboid.Cuboid;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;


public class RegionCommand extends JavaPlugin implements CommandExecutor, Listener{
    
    WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
    
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
        getServer().getPluginManager().registerEvents(this, this);
        
    }  
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("rgc")){
            if (args[0].equalsIgnoreCase("create")){
                    Player player = (Player) sender;
                        try {
                            int count = this.getRegionsFile().getInt("count");
                            Selection selection = worldEdit.getSelection(player);
                            Cuboid cuboid = new Cuboid(selection.getMaximumPoint(), selection.getMinimumPoint());
                            this.getRegionsFile().set("regions." + count + ".loc", cuboid.serialize());
                            sender.sendMessage("Region " + count + " has been created!");
                            count = count + 1;
                            this.getRegionsFile().set("count", count);
                            this.saveRegionsFile();
                            return true;
                        } catch (UnsupportedEncodingException ex) {
                            Logger.getLogger(RegionCommand.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
            } else if (args[0].equalsIgnoreCase("command")){
                try {
                    this.getRegionsFile().set("regions." + args[1] + ".command", args[2]);
                    this.saveRegionsFile();
                    sender.sendMessage("Command Set");
                    return true;
                    
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(RegionCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                
            } 
            else if (args[0].equalsIgnoreCase("help")){
                sender.sendMessage(ChatColor.BLUE + "----------" + ChatColor.GREEN + "RegionCommand Help" + ChatColor.BLUE + "----------");
                sender.sendMessage(ChatColor.BLUE + "help:");
                sender.sendMessage(ChatColor.GREEN + "Syntax: /rgc help");
                sender.sendMessage(ChatColor.GREEN + "Use: Show this page");
                sender.sendMessage(ChatColor.BLUE + "create:");
                sender.sendMessage(ChatColor.GREEN + "Syntax: /rgc create [regionnumber]");
                sender.sendMessage(ChatColor.GREEN + "Use: Set position 1 of a portal");
                sender.sendMessage(ChatColor.BLUE + "p2:");
                sender.sendMessage(ChatColor.BLUE + "command:");
                sender.sendMessage(ChatColor.GREEN + "Syntax: /rgc command <regionnumber> <command>");
                sender.sendMessage(ChatColor.GREEN + "Use: Set the command to be executed when a player enters the portal");
                return true;
            }
        return false;
        }
    
        @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event) throws UnsupportedEncodingException{
        if (event.getPlayer().hasPermission("portalcommand.use")){
            
            for (int x=0; x>this.getRegionsFile().getInt("count"); x++){
                Cuboid region = (Cuboid) this.getRegionsFile().get("regions." + x + ".loc");
                if (region.containsLocation(event.getTo()) && !region.containsLocation(event.getFrom())){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.getRegionsFile().getString("regions." + x + ".command"));
            }
            
        }
        }

    }

        
    }



            
            
        

    

   
