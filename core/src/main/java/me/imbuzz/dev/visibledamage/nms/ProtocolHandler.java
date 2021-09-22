package me.imbuzz.dev.visibledamage.nms;

import me.imbuzz.dev.visibledamage.objects.HoloOBJ;
import org.bukkit.Location;

public interface ProtocolHandler {

    HoloOBJ getHologram(Location location, String text);

    class HologramImpl {
    }

}
