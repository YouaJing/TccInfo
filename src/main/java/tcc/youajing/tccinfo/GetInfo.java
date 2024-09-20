package tcc.youajing.tccinfo;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;

/**
 * 玩家信息获取类
 * 提供各种方法来获取玩家的游戏内统计信息
 */
public class GetInfo {

    /**
     * 获取玩家的游戏时间（小时）
     *
     * @param player 离线玩家对象
     * @return 玩家的游戏时间（小时）
     */
    public int getPlayTime(OfflinePlayer player) {
        return player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 72000;
    }

    /**
     * 获取玩家睡觉次数
     *
     * @param player 离线玩家对象
     * @return 玩家睡觉次数
     */
    public int getBedCount(OfflinePlayer player) {
        return player.getStatistic(Statistic.SLEEP_IN_BED);
    }

    /**
     * 获取玩家死亡次数
     *
     * @param player 离线玩家对象
     * @return 玩家死亡次数
     */
    public int getDeathCount(OfflinePlayer player) {
        return player.getStatistic(Statistic.DEATHS);
    }

    /**
     * 获取玩家击杀怪物数量
     *
     * @param player 离线玩家对象
     * @return 玩家击杀怪物数量，当数量大于等于10000时，以“万”为单位显示
     */
    public String getMobKills(OfflinePlayer player) {
        int mobKills = player.getStatistic(Statistic.MOB_KILLS);
        if (mobKills < 10000) {
            return String.valueOf(mobKills);
        } else {
            int w = mobKills / 10000;
            int k = (mobKills % 10000) / 1000;
            return w + (k > 0 ? "." + k : "") + "w";
        }
    }

    /**
     * 获取玩家击杀末影龙次数
     *
     * @param player 离线玩家对象
     * @return 玩家击杀末影龙次数
     */
    public int getDragonKills(OfflinePlayer player) {
        return player.getStatistic(Statistic.KILL_ENTITY, EntityType.ENDER_DRAGON);
    }

    /**
     * 获取玩家合成下界合金次数
     *
     * @param player 离线玩家对象
     * @return 玩家合成下界合金次数
     */
    public int getNetheriteCraft(OfflinePlayer player) {
        return player.getStatistic(Statistic.CRAFT_ITEM, Material.NETHERITE_INGOT);
    }

    /**
     * 获取玩家的所有统计信息
     *
     * @param player 离线玩家对象
     * @return 包含玩家各种统计信息的键值对映射表
     */
    public HashMap<String, String> getAllStats(OfflinePlayer player) {
        HashMap<String, String> stats = new HashMap<>();
        stats.put("playtime", String.valueOf(getPlayTime(player)));
        stats.put("bedcount", String.valueOf(getBedCount(player)));
        stats.put("deathcount", String.valueOf(getDeathCount(player)));
        stats.put("mobkills", getMobKills(player));
        stats.put("dragonkills", String.valueOf(getDragonKills(player)));
        stats.put("netheritecraft", String.valueOf(getNetheriteCraft(player)));
        return stats;
    }

    /**
     * 获取离线玩家的前缀
     *
     * @param player 离线玩家对象
     * @return 玩家的前缀，如果不存在则返回默认提示信息
     */
    public static String getPrefixOffline(OfflinePlayer player) {
        String prefix = PrefixManager.getPrefix(player.getName());
        return Objects.requireNonNullElse(prefix, "{\"color_code\":\"#000000,#000000\",\"text\":\"你还没有称号捏\"}");
    }

}
