/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.snailz.portalcommand;

import java.io.UnsupportedEncodingException;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 *
 * @author user
 */
public class EnterRegion extends PortalCommand implements Listener{
    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event) throws UnsupportedEncodingException{
        int count = this.getRegionsFile().getInt("count");
        int i = 0;
        if (count > 0){
            for (i = 0 ; i < count; i++){
            String num = Integer.toString(i);
            int x1 = this.getRegionsFile().getInt("regions." + num + ".cordnates.1.x");
            int y1 = this.getRegionsFile().getInt("regions." + num + ".cordnates.1.y");
            int z1 = this.getRegionsFile().getInt("regions." + num + ".cordnates.1.z");
            int x2 = this.getRegionsFile().getInt("regions." + num + ".cordnates.2.x");
            int y2 = this.getRegionsFile().getInt("regions." + num + ".cordnates.2.y");
            int z2 = this.getRegionsFile().getInt("regions." + num + ".cordnates.2.z");
            if (event.getTo().getBlockX() >= x1){
                if (event.getTo().getBlockX() <= x2){
                    if (event.getTo().getBlockY() >= y1){
                        if (event.getTo().getBlockY() <= y2){
                            if (event.getTo().getBlockZ() >= z1){
                                if (event.getTo().getBlockZ() <= z2){
                                    String cmd = this.getRegionsFile().getString("regions." + num +".command");
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                                }
                            }
                        }
                    }
                }
            }
            
        }
        }

    }
    
}
