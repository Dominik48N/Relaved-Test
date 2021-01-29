package net.relaved.test.listener.player;

import lombok.RequiredArgsConstructor;
import net.relaved.test.TestPlugin;
import net.relaved.test.player.TestPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by Dominik48N on 29.01.2021
 */
@RequiredArgsConstructor
public class PlayerJoinListener implements Listener {

    private final TestPlugin plugin;

    @EventHandler( priority = EventPriority.LOW )
    public void handlePlayerJoin( final PlayerJoinEvent event ) {
        final Player player = event.getPlayer();
        final TestPlayer testPlayer = new TestPlayer( player );

        player.setMetadata( "test-player", new FixedMetadataValue( this.plugin, testPlayer ) );

        testPlayer.setScoreboard();
    }

}
