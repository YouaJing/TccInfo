package tcc.youajing.tccinfo;


import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GetInfo {

    public int getPlayTime(OfflinePlayer player) {
        return player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 72000;
    }
    public int getBedCount(OfflinePlayer player) {
        return player.getStatistic(Statistic.SLEEP_IN_BED);
    }
    public int getDeathCount(OfflinePlayer player) {
        return player.getStatistic(Statistic.DEATHS);
    }
    public String getMobKills(OfflinePlayer player) {
        int mobKills = player.getStatistic(Statistic.MOB_KILLS);
        if (mobKills < 10000) {
            return String.valueOf(mobKills);
        }else {
            int w = mobKills / 10000;
            int k = (mobKills % 10000) / 1000;
            return w + (k > 0 ? "." + k : "") + "w";
        }
    }
    public int getDragonKills(OfflinePlayer player) {
        return player.getStatistic(Statistic.KILL_ENTITY, EntityType.ENDER_DRAGON);
    }
    public int getNetheriteCraft(OfflinePlayer player) {
        return player.getStatistic(Statistic.CRAFT_ITEM, Material.NETHERITE_INGOT);
    }
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

    //离线玩家
    public static String getPrefixOffline(OfflinePlayer player) {
        String prefix = PrefixManager.getPrefix(player.getName());
        if (prefix != null) {
            return prefix.replaceAll("<[^>]+>", "");
        }else {
            return "-";
        }
    }

}
