package net.relaved.test;

import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Dominik48N on 29.01.2021
 */
public class TestPlugin extends JavaPlugin {

    @Getter private static TestPlugin instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

        // Register all commands and listeners
        this.registerListeners();
        this.registerCommands();

    }

    @Override
    public void onDisable() {

    }

    /**
     * Register all listeners
     */
    private void registerListeners() {
        final PluginManager pluginManager = this.getServer().getPluginManager();

    }

    /**
     * Register all commands
     */
    private void registerCommands() {

    }
}
