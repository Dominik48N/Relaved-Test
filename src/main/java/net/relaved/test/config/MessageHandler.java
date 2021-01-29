package net.relaved.test.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

/**
 * Created by Dominik48N on 29.01.2021
 */
public class MessageHandler {

    private final Map< String, List< String > > messageListCache;
    private final Map< String, String > messageCache;

    private final YamlConfiguration yamlConfiguration;

    public MessageHandler( final Plugin plugin ) {
        this.messageCache = Maps.newConcurrentMap();
        this.messageListCache = Maps.newConcurrentMap();

        this.yamlConfiguration = this.mkdirs( plugin );
    }

    /**
     * Put all messages from the messages file in the local cache
     */
    public void loadMessages() {
        final String prefix = ChatColor.translateAlternateColorCodes( '&', this.yamlConfiguration.getString( "prefix" ) );

        for ( final String key : this.yamlConfiguration.getKeys( true ) ) {

            if ( this.yamlConfiguration.isString( key ) ) {
                this.messageCache.put( key, ChatColor.translateAlternateColorCodes( '&', this.yamlConfiguration.getString( key )
                        .replace( "{0}", prefix ) ) );
                continue;
            }

            if ( this.yamlConfiguration.isList( key ) ) this.messageListCache.put( key, this.yamlConfiguration.getStringList( key ) );
        }

    }

    /**
     * Get a cached message by the specific key and replace the specific arguments
     *
     * @param key          the message key
     * @param replacements the replacement objects
     *
     * @return the replaced message
     */
    public String getMessage( final String key, final Object... replacements ) {
        String message = this.messageCache.get( key );

        if ( message == null ) return "§4Not Found \"" + key + "\"";
        if ( replacements.length == 0 ) return message;
        int i = 1;

        for ( final Object o : replacements ) {
            message = message.replace( "{" + i + "}", o.toString() );
            i++;
        }

        return message;
    }

    /**
     * Returns a cached message by the specific key
     *
     * @param key the message list key
     *
     * @return the message list
     */
    public List< String > getList( final String key ) {
        final List< String > list = this.messageListCache.get( key );

        return list == null ? Lists.newArrayList() : list;
    }

    private YamlConfiguration mkdirs( final Plugin plugin ) {
        final File file = new File( plugin.getDataFolder(), "messages.yml" );

        if ( !file.exists() ) {
            file.getParentFile().mkdirs();
            plugin.saveResource( "messages.yml", false );
        }

        return YamlConfiguration.loadConfiguration( file );
    }

}
