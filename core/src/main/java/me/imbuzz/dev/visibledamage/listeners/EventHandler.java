package me.imbuzz.dev.visibledamage.listeners;

import me.imbuzz.dev.visibledamage.VisibleDamage;
import me.imbuzz.dev.visibledamage.configuration.ConfigFile;
import me.imbuzz.dev.visibledamage.objects.HoloOBJ;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventHandler implements Listener {

    private final VisibleDamage visibleDamage;
    private final ConfigFile configFile;

    public EventHandler(VisibleDamage visibleDamage) {
        this.visibleDamage = visibleDamage;
        configFile = this.visibleDamage.getConfigFile();
    }

    public static double randomDouble(double min, double max) {
        return (Math.random() * ((max - min) + 1)) + min;
    }

    @org.bukkit.event.EventHandler(priority = EventPriority.MONITOR)
    public void entityDamage(EntityDamageEvent event) {

        if (configFile.getList("disabled-worlds").contains(event.getEntity().getWorld().getName())) return;
        if (configFile.getList("entity-type-blacklist").contains(event.getEntity().getType().toString())) return;

        String text = configFile.getString("damage-format").replaceAll("%damage%", event.getFinalDamage() + "");
        Location newLocation = event.getEntity().getLocation().clone().add(randomDouble(-1, 1), 0, randomDouble(-1, 1));

        HoloOBJ holoOBJ = visibleDamage.getProtocolHandler().getHologram(newLocation, ChatColor.translateAlternateColorCodes('&', text));
        holoOBJ.spawn(configFile.getInt("hologram-offset"));
        Bukkit.getScheduler().runTaskLater(visibleDamage, holoOBJ::remove, 20L * configFile.getInt("hologram-alive"));
    }


}
