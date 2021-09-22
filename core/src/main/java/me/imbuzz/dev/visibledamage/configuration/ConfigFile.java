package me.imbuzz.dev.visibledamage.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class ConfigFile {

    private FileConfiguration fileConfiguration;
    private File file;

    public ConfigFile(JavaPlugin pluginModule) {
        file = new File(pluginModule.getDataFolder(), "config.yml");
        if (!file.exists())
            pluginModule.saveResource("config.yml", true);
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public int getInt(String path) {
        return fileConfiguration.getInt(path);
    }

    public double getDouble(String path) {
        return fileConfiguration.getDouble(path);
    }

    public String getString(String path) {
        return fileConfiguration.getString(path);
    }

    public List<String> getList(String path) {
        return fileConfiguration.getStringList(path);
    }

    public boolean getBoolean(String path) {
        return fileConfiguration.getBoolean(path);
    }


}
