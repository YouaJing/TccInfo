package tcc.youajing.tccinfo;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;


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
        } else {
            double killsInW = mobKills / 10000.0;
            return String.format("%.1fw", killsInW);
        }
    }
    public int getDragonKills(OfflinePlayer player) {
        return player.getStatistic(Statistic.KILL_ENTITY, EntityType.ENDER_DRAGON);
    }
    public int getNetheriteCraft(OfflinePlayer player) {
        return player.getStatistic(Statistic.CRAFT_ITEM, Material.NETHERITE_INGOT);
    }

    public String getTotalDistance(OfflinePlayer player) {
        int totalDistanceInCm = player.getStatistic(Statistic.SWIM_ONE_CM)
                + player.getStatistic(Statistic.SPRINT_ONE_CM)
                + player.getStatistic(Statistic.WALK_ONE_CM)
                + player.getStatistic(Statistic.WALK_ON_WATER_ONE_CM)
                + player.getStatistic(Statistic.WALK_UNDER_WATER_ONE_CM)
                + player.getStatistic(Statistic.CLIMB_ONE_CM)
                + player.getStatistic(Statistic.CROUCH_ONE_CM);

        int totalDistanceInBlocks = totalDistanceInCm / 100; // 转换为方块

        if (totalDistanceInBlocks >= 10000) {
            double distanceInKm = totalDistanceInBlocks / 10000.0;
            return String.format("%.1fw", distanceInKm);
        } else {
            return String.valueOf(totalDistanceInBlocks);
        }
    }


//    public String getMinedBlocks(OfflinePlayer player) {
//        int minedBlocks = player.getStatistic(Statistic.MINE_BLOCK);
//        if (minedBlocks < 10000) {
//            return String.valueOf(minedBlocks);
//        } else {
//            double MinedBlocksInW = minedBlocks / 10000.0;
//            return String.format("%.1fw", MinedBlocksInW);
//        }
////        return String.valueOf(player.getStatistic(Statistic.MINE_BLOCK));
//    }


    public String getElytraDistance(OfflinePlayer player) {
        int elytraDistance = player.getStatistic(Statistic.AVIATE_ONE_CM) / 100;
        if (elytraDistance < 10000) {
            return String.valueOf(elytraDistance);
        } else {
            double elytraDistanceInW = elytraDistance / 10000.0;
            return String.format("%.1fw", elytraDistanceInW);
        }
    }

    public String getVillagerTrades(OfflinePlayer player) {
        int villagerTrades = player.getStatistic(Statistic.TRADED_WITH_VILLAGER);
        if (villagerTrades < 10000) {
            return String.valueOf(villagerTrades);
        } else {
            double vilagerTradesInW = villagerTrades / 10000.0;
            return String.format("%.1fw", vilagerTradesInW);
        }
    }

    public String getChestOpened(OfflinePlayer player) {
        int chestOpened = player.getStatistic(Statistic.CHEST_OPENED);
        if (chestOpened < 10000) {
            return String.valueOf(chestOpened);
        } else {
            double chestOpenedInW = chestOpened / 10000.0;
            return String.format("%.1fw", chestOpenedInW);
        }
    }

    public String getEnderChestOpened(OfflinePlayer player) {
        int enderChestOpened = player.getStatistic(Statistic.ENDERCHEST_OPENED);
        if (enderChestOpened < 10000) {
            return String.valueOf(enderChestOpened);
        } else {
            double enderChestOpenedInW = enderChestOpened / 10000.0;
            return String.format("%.1fw", enderChestOpenedInW);
        }
    }

    public String getDamageTaken(OfflinePlayer player) {
        int damageTaken = player.getStatistic(Statistic.DAMAGE_TAKEN);
        if (damageTaken < 10000) {
            return String.valueOf(damageTaken);
        } else {
            double damageTakenInW = damageTaken / 10000.0;
            return String.format("%.1fw", damageTakenInW);
        }
    }
    public String getFishingCaught(OfflinePlayer player) {
        int fishingCaught = player.getStatistic(Statistic.FISH_CAUGHT);
        if (fishingCaught < 10000) {
            return String.valueOf(fishingCaught);
        } else {
            double fishingCaughtInW = fishingCaught / 10000.0;
            return String.format("%.1fw", fishingCaughtInW);
        }
    }

    public String getRaidTriggered(OfflinePlayer player) {
        int raidTriggered = player.getStatistic(Statistic.RAID_TRIGGER);
        if (raidTriggered < 10000) {
            return String.valueOf(raidTriggered);
        } else {
            double raidTriggeredInW = raidTriggered / 10000.0;
            return String.format("%.1fw", raidTriggeredInW);
        }
    }

    public String getFlowerPotted(OfflinePlayer player) {
        int flowerPotted = player.getStatistic(Statistic.FLOWER_POTTED);
        if (flowerPotted < 10000) {
            return String.valueOf(flowerPotted);
        } else {
            double flowerPottedInW = flowerPotted / 10000.0;
            return String.format("%.1fw", flowerPottedInW);
        }
    }

    public HashMap<String, String> getAllStats(OfflinePlayer player) {
        HashMap<String, String> stats = new HashMap<>();
        stats.put("playtime", String.valueOf(getPlayTime(player)));
        stats.put("bedcount", String.valueOf(getBedCount(player)));
        stats.put("deathcount", String.valueOf(getDeathCount(player)));
        stats.put("mobkills", getMobKills(player));
        stats.put("dragonkills", String.valueOf(getDragonKills(player)));
        stats.put("netheritecraft", String.valueOf(getNetheriteCraft(player)));
        stats.put("distancewalked", getTotalDistance(player));
//        stats.put("minedblocks", getMinedBlocks(player));
        stats.put("elytradistance", getElytraDistance(player));
        stats.put("villagertrades", getVillagerTrades(player));
        stats.put("chestopened", getChestOpened(player));
        stats.put("enderchestopened", getEnderChestOpened(player));
        stats.put("damagetaken", getDamageTaken(player));
        stats.put("fishingcaught", getFishingCaught(player));
        stats.put("raidtriggered", getRaidTriggered(player));
        stats.put("flowerpotted", getFlowerPotted(player));
        return stats;
    }


    public static String getPrefixOffline(OfflinePlayer player) {
        String prefix = PrefixManager.getPrefix(player.getName());
        return Objects.requireNonNullElse(prefix, "{\"color_code\":\"#495057,#495057\",\"text\":\"连接服务器刷新数据\"}");
    }

    public static String getFirstJoinDateOffline(OfflinePlayer player) {
        String firstJoinDate = PrefixManager.getFirstJoinDate(player.getName());
        return Objects.requireNonNullElse(firstJoinDate, "{\"joindate\":\"连接服务器刷新数据\"}");
    }
}
