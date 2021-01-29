package net.relaved.test;

import lombok.Getter;
import net.relaved.test.config.MessageHandler;
import net.relaved.test.listener.player.PlayerJoinListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Dominik48N on 29.01.2021
 */
@Getter
public class TestPlugin extends JavaPlugin {

    @Getter private static TestPlugin instance;

    private MessageHandler messageHandler;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

        // Load all messages
        this.messageHandler = new MessageHandler( this );
        this.messageHandler.loadMessages();

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

        // Player listeners
        pluginManager.registerEvents( new PlayerJoinListener( this ), this );
    }

    /**
     * Register all commands
     */
    private void registerCommands() {

    }
}
