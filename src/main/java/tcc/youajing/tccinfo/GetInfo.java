package tcc.youajing.tccinfo;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;

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
    public int getMobKills(OfflinePlayer player) {
        return player.getStatistic(Statistic.MOB_KILLS);
    }
    public int getDragonKills(OfflinePlayer player) {
        return player.getStatistic(Statistic.KILL_ENTITY, EntityType.ENDER_DRAGON);
    }
    public int getNetheriteCraft(OfflinePlayer player) {
        return player.getStatistic(Statistic.CRAFT_ITEM, Material.NETHERITE_INGOT);
    }

    public HashMap<String, Integer> getAllStats(OfflinePlayer player) {
        HashMap<String, Integer> stats = new HashMap<>();
        stats.put("playtime", getPlayTime(player));
        stats.put("bedcount", getBedCount(player));
        stats.put("deathcount", getDeathCount(player));
        stats.put("mobkills", getMobKills(player));
        stats.put("dragonkill", getDragonKills(player));
        stats.put("netheritecraft", getNetheriteCraft(player));
        return stats;
    }
}
