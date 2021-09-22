package me.imbuzz.dev.visibledamage;

import lombok.Getter;
import me.imbuzz.dev.visibledamage.configuration.ConfigFile;
import me.imbuzz.dev.visibledamage.listeners.EventHandler;
import me.imbuzz.dev.visibledamage.nms.ProtocolHandler;
import me.imbuzz.dev.visibledamage.nms.ServerProtocols;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class VisibleDamage extends JavaPlugin implements Listener {

    private ProtocolHandler protocolHandler;
    private ConfigFile configFile;

    @Override
    public void onEnable() {
        protocolHandler = ServerProtocols.getProtocolHandler();
        configFile = new ConfigFile(this);

        Bukkit.getPluginManager().registerEvents(new EventHandler(this), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
