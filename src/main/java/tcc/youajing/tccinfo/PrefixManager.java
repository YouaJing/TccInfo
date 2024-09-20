package tcc.youajing.tccinfo;

import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;

/**
 * 管理玩家前缀的类
 * 负责更新和获取玩家的前缀信息，使用YAML文件进行存储
 */
public class PrefixManager {

    // 存储前缀信息的文件
    private  final File file;
    // 静态Yaml配置对象，用于操作前缀信息
    private static YamlConfiguration config = new YamlConfiguration();

    /**
     * 构造函数，初始化前缀管理器
     *
     * @param dataFolder 数据文件夹，用于存储prefix.yml文件
     */
    public PrefixManager(File dataFolder) {
        // 初始化文件对象
        this.file = new File(dataFolder, "prefix.yml");
        // 加载Yaml配置
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * 更新玩家的前缀并保存到文件中
     *
     * @param playerName 玩家名称
     * @param prefix     新的前缀
     */
    public void updatePrefix(String playerName, String prefix) {
        // 在配置中设置玩家的前缀
        config.set(playerName + ".prefix", prefix);
        // 尝试保存配置到文件
        try {
            config.save(file);
        } catch (IOException e) {
            // 如果保存失败，打印异常信息
            e.printStackTrace();
        }
    }

    /**
     * 获取玩家的前缀
     *
     * @param playerName 玩家名称
     * @return 玩家的前缀，如果不存在则返回null
     */
    public static String getPrefix(String playerName) {
        // 从配置中获取玩家的前缀
        return config.getString(playerName + ".prefix", null);
    }
}
