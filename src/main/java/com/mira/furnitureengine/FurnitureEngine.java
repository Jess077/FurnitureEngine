package com.mira.furnitureengine;

import com.mira.furnitureengine.commands.CoreCommand;
import com.mira.furnitureengine.commands.CoreCommandTabCompleter;
import com.mira.furnitureengine.furniture.FurnitureManager;
import com.mira.furnitureengine.furniture.functions.FunctionManager;
import com.mira.furnitureengine.listeners.*;
import com.mira.furnitureengine.utils.UpdateChecker;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class FurnitureEngine extends JavaPlugin {
    public static FurnitureEngine getInstance() {
        return instance;
    }

    private static FurnitureEngine instance;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("FurnitureEngine has been enabled!");

        // Load config
        loadConfig();

        // Static access... doing this so that it instantiates the classes
        FurnitureManager.getInstance();
        FunctionManager.getInstance();

        // Register commands
        getCommand("furnitureengine").setExecutor(new CoreCommand());
        getCommand("furnitureengine").setTabCompleter(new CoreCommandTabCompleter());

        // Register events
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
