package me.gonzalociocca.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {



    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PluginListener(this), this);
    }

    @Override
    public void onDisable(){
        //this.saveConfig();
    }


}
