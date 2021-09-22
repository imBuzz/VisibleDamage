package me.imbuzz.dev.visibledamage.nms;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ServerProtocols {

    public static String getServerVersion() {
        String name = Bukkit.getServer().getClass().getPackage().getName();
        return name.substring(name.lastIndexOf('.') + 1);
    }

    public static ProtocolHandler getProtocolHandler() {
        try {
            return (ProtocolHandler) Class.forName("me.imbuzz.dev.visibledamage.nms." + getServerVersion() + ".ProtocolHandlerImpl").newInstance();
        } catch (Exception exc) {
            exc.printStackTrace();
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "You are running VisibleDamage on an unsupported NMS version " + getServerVersion());
            return null;
        }
    }

}
