package net.relaved.test.player;

import java.util.UUID;
import lombok.Getter;
import net.relaved.test.TestPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Created by Dominik48N on 29.01.2021
 */
@Getter
public class TestPlayer {

    private final Player player;

    private final UUID uniqueId;
    private final String name;

    public TestPlayer( final Player player ) {
        this.player = player;

        this.uniqueId = player.getUniqueId();
        this.name = player.getName();
    }

    /**
     * Set the scoreboard to the current player
     */
    public void setScoreboard() {
        final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective( "aaa", "bbb" );
        byte i = ( byte ) TestPlugin.getInstance().getMessageHandler().getList( "scoreboard.lines" ).size();

        objective.setDisplaySlot( DisplaySlot.SIDEBAR );
        objective.setDisplayName( TestPlugin.getInstance().getMessageHandler().getMessage( "scoreboard.display_name" ) );

        for ( String s : TestPlugin.getInstance().getMessageHandler().getList( "scoreboard.lines" ) ) {
            final Team team = scoreboard.registerNewTeam( "x" + i );
            final int length = s.length();

            s = this.replaceMessage( s );


            team.setPrefix( length > 16 ? s.substring( 0, 16 ) : s );
            if ( length > 16 ) team.setSuffix( s.substring( 16, Math.min( length, 32 ) ) );

            team.addEntry( this.getEntry( i ) );
            i--;
        }

        this.player.setScoreboard( scoreboard );
    }

    protected String getEntry( final byte id ) {

        switch ( id ) {

            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: return "§" + id;
            case 10: return "§a";
            case 11: return "§b";
            case 12: return "§c";
            case 13: return "§d";
            case 14: return "§e";
            case 15: return "§f";

        }

        return "§a";
    }

    protected String replaceMessage( final String message ) {
        return message.replace( "{0}", this.name );
    }

}
