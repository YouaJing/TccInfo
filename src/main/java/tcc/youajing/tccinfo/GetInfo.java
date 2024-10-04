package tcc.youajing.tccinfo;


import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Objects;


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

    public String getOnlineStatus(OfflinePlayer player) {
        if (player.isOnline()) {
            return "在线";
        }else return "离线";
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
//    public int getNetheriteCraft(OfflinePlayer player) {
//        return player.getStatistic(Statistic.CRAFT_ITEM, Material.NETHERITE_INGOT);
//    }

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
            double distanceInw = totalDistanceInBlocks / 10000.0;
            return String.format("%.1fw", distanceInw);
        } else {
            return String.valueOf(totalDistanceInBlocks);
        }
    }


    public static String getMinedDebris(OfflinePlayer player) {
        int minedDebris = player.getStatistic(Statistic.MINE_BLOCK, Material.ANCIENT_DEBRIS);
        int placeDebris = player.getStatistic(Statistic.USE_ITEM, Material.ANCIENT_DEBRIS);
        int totalDebris = minedDebris - placeDebris;
        if (totalDebris >= 10000) {
            double totalDebrisInw = totalDebris / 10000.0;
            return String.format("%.1fw", totalDebrisInw);
        } else {
            return String.valueOf(totalDebris);
        }
    }



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

//    public static String getMineBlocks(OfflinePlayer player) {
//        // 初始化一个Map来缓存Material和对应的挖掘数量
//        Map<Material, Integer> mineCounts = new HashMap<>();
//
//        // 使用Material.values()一次获取所有可能的材料并计算总挖掘数
//        for(Material material : Material.values()) {
//            if (material.isBlock()) {
//                mineCounts.put(material, player.getStatistic(Statistic.MINE_BLOCK, material));
//            }
//        }
//
//        // 计算总挖掘数
//        int totalMineBlocks = mineCounts.values().stream().mapToInt(Integer::intValue).sum();
//
//        // 根据总挖掘数返回不同的格式化字符串
//        if (totalMineBlocks < 10000) {
//            return String.valueOf(totalMineBlocks);
//        } else {
//            double mineBlocksInW = totalMineBlocks / 10000.0;
//            return String.format("%.1fw", mineBlocksInW);
//        }
//    }
//
//    public static String getPlaceBlocks(OfflinePlayer player) {
//        // 初始化一个Map来缓存Material和对应的挖掘数量
//        Map<Material, Integer> placeCounts = new HashMap<>();
//
//        // 使用Material.values()一次获取所有可能的材料并计算总挖掘数
//        for(Material material : Material.values()) {
//            if (material.isBlock()) {
//                placeCounts.put(material, player.getStatistic(Statistic.USE_ITEM, material));
//            }
//        }
//
//        // 计算总挖掘数
//        int totalPlaceBlocks = placeCounts.values().stream().mapToInt(Integer::intValue).sum();
//
//        // 根据总挖掘数返回不同的格式化字符串
//        if (totalPlaceBlocks < 10000) {
//            return String.valueOf(totalPlaceBlocks);
//        } else {
//            double placeBlocksInW = totalPlaceBlocks / 10000.0;
//            return String.format("%.1fw", placeBlocksInW);
//        }
//    }


    public String getDamageDealt(OfflinePlayer player) {
        int damageDealt = player.getStatistic(Statistic.DAMAGE_DEALT);
        if (damageDealt < 10000) {
            return String.valueOf(damageDealt);
        } else {
            double damageDealtInW = damageDealt / 10000.0;
            return String.format("%.1fw", damageDealtInW);
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

    public String getAnimalsBred(OfflinePlayer player){
        int animalsBred = player.getStatistic(Statistic.ANIMALS_BRED);
        if (animalsBred < 10000) {
            return String.valueOf(animalsBred);
        } else {
            double animalsBredInW = animalsBred / 10000.0;
            return String.format("%.1fw", animalsBredInW);
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
        stats.put("OnlineStatus", getOnlineStatus(player));
        stats.put("playTime", String.valueOf(getPlayTime(player)));
        stats.put("bedCount", String.valueOf(getBedCount(player)));
        stats.put("deathCount", String.valueOf(getDeathCount(player)));
        stats.put("mobKills", getMobKills(player));
        stats.put("dragonKills", String.valueOf(getDragonKills(player)));
//        stats.put("mineBlocks", getMineBlocks(player));
//        stats.put("placeBlocks", getPlaceBlocks(player));
        stats.put("minedDebris", getMinedDebris(player));
        stats.put("distanceWalked", getTotalDistance(player));
        stats.put("elytraDistance", getElytraDistance(player));
        stats.put("villagerTrades", getVillagerTrades(player));
        stats.put("chestOpened", getChestOpened(player));
        stats.put("enderChestOpened", getEnderChestOpened(player));
        stats.put("damageTaken", getDamageTaken(player));
        stats.put("damageDealt", getDamageDealt(player));
        stats.put("fishingCaught", getFishingCaught(player));
        stats.put("animalsBred", getAnimalsBred(player));
        stats.put("raidTriggered", getRaidTriggered(player));
        stats.put("flowerPotted", getFlowerPotted(player));
        return stats;
    }


    public static String getPrefixOffline(OfflinePlayer player) {
        String prefix = PrefixManager.getPrefix(player.getName());
        return Objects.requireNonNullElse(prefix, "{\"color_code\":\"#495057,#495057\",\"text\":\"连接服务器刷新数据\"}");
    }

    public static String getFirstJoinDateOffline(OfflinePlayer player) {
        String firstJoinDate = PrefixManager.getFirstJoinDate(player.getName());
        return Objects.requireNonNullElse(firstJoinDate, "{\"firstJoinDate\":\"连接服务器刷新数据\"}");
    }
    public static String getMineBlocksOffline(OfflinePlayer player) {
        String mineBlocks = PrefixManager.getMineBlocks(player.getName());
        return Objects.requireNonNullElse(mineBlocks, "{\"mineBlocks\":\"连接服务器刷新数据\"}");
    }
}
