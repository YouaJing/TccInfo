package tcc.youajing.tccinfo;

import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;


public class PrefixManager {

    private  final File file;
    private static YamlConfiguration config = new YamlConfiguration();
    public PrefixManager(File dataFolder) {
        this.file = new File(dataFolder, "prefix.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void updatePrefix(String playerName, String prefix) {
        config.set(playerName + ".prefix", prefix);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPrefix(String playerName) {
        return config.getString(playerName + ".prefix", "-");
    }
}

