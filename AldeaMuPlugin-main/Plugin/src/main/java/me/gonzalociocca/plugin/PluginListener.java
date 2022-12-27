package me.gonzalociocca.plugin;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

public class PluginListener implements Listener {
    public Plugin main;
    public Logger logger;
    Random r = new Random();
    public PluginListener(Plugin pl){
        main=pl;
        logger=pl.getLogger();
    }

    HashMap<String,Long> rmap = new HashMap();
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        event.setRespawnLocation(event.getPlayer().getLastDeathLocation());
        rmap.put(event.getPlayer().getName(),System.currentTimeMillis()+5000L);
        event.getPlayer().sendMessage("Tienes invulnerabilidad por 5 segundos o hasta que ataques");
        event.getPlayer().sendMessage("Si quieres ir a otro lugar usa /home /warp /tpa");

    }

    @EventHandler
    public void onReceiveDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            if(rmap.containsKey(event.getEntity().getName())){
                if(rmap.get(event.getEntity().getName()) > System.currentTimeMillis()){
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            if(rmap.containsKey(event.getDamager().getName())){
                rmap.remove(event.getDamager().getName());
            }
        }
    }






}
