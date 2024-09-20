package tcc.youajing.tccinfo;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class InfoCommand implements CommandExecutor {
    private final TccInfo plugin;

    public InfoCommand(TccInfo plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "哥们没睡醒是吧？");
            return true;
        }

        String subCommand = args[0].toLowerCase();
        GetInfo getInfo = new GetInfo();
        switch (subCommand) {
            case "playtime":
                if (args.length < 2) {
                    sender.sendMessage("用法: /tccinfo playtime <玩家ID>");
                    return true;
                }
                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                int playTime = getInfo.getPlayTime(player);
                sender.sendMessage(String.valueOf(playTime));
                return true;

            case "bedcount":
                if (args.length < 2) {
                    sender.sendMessage("用法: /tccinfo bedcount <玩家ID>");
                    return true;
                }
                player = Bukkit.getOfflinePlayer(args[1]);
                int bedCount = getInfo.getBedCount(player);
                sender.sendMessage(String.valueOf(bedCount));
                return true;

            case "deathcount":
                if (args.length < 2) {
                    sender.sendMessage("用法: /tccinfo deathcount <玩家ID>");
                    return true;
                }
                player = Bukkit.getOfflinePlayer(args[1]);
                int deathCount = getInfo.getDeathCount(player);
                sender.sendMessage(String.valueOf(deathCount));
                return true;

            case "mobkills":
                if (args.length < 2) {
                    sender.sendMessage("用法: /tccinfo mobkills <玩家ID>");
                    return true;
                }
                player = Bukkit.getOfflinePlayer(args[1]);
                String mobKills = getInfo.getMobKills(player);
                sender.sendMessage(mobKills);
                return true;

            case "dragonkills":
                if (args.length < 2) {
                    sender.sendMessage("用法: /tccinfo dragonkills <玩家ID>");
                    return true;
                }
                player = Bukkit.getOfflinePlayer(args[1]);
                int dragonKills = getInfo.getDragonKills(player);
                sender.sendMessage(String.valueOf(dragonKills));
                return true;

            case "netheritecraft":
                if (args.length < 2) {
                    sender.sendMessage("用法: /tccinfo netheritecraft <玩家ID>");
                    return true;
                }
                player = Bukkit.getOfflinePlayer(args[1]);
                int netheriteCraft = getInfo.getNetheriteCraft(player);
                sender.sendMessage(String.valueOf(netheriteCraft));
                return true;

            case "prefix":
                if (args.length < 2) {
                    sender.sendMessage("用法: /tccinfo prefix <玩家ID>");
                    return true;
                }
                sender.sendMessage(GetInfo.getPrefixOffline(Bukkit.getOfflinePlayer(args[1])));
                return true;


            // 当命令参数为"all"时，提供一个玩家的所有信息
            case "all":
                // 如果参数数量不足，提示正确用法并终止处理
                if (args.length < 2) {
                    sender.sendMessage("用法: /tccinfo all <玩家ID>");
                    return true;
                }
                // 根据提供的玩家ID获取离线玩家对象
                player = Bukkit.getOfflinePlayer(args[1]);
                // 调用方法getAllStats获取玩家的所有信息
                HashMap<String, String> allStats = getInfo.getAllStats(player);

                // 定义前缀信息
                String prefix = GetInfo.getPrefixOffline(Bukkit.getOfflinePlayer(args[1]));

                // 创建一个StringBuilder来构建JSON格式的字符串
                StringBuilder json = new StringBuilder("{");

                // 将前缀信息追加到JSON字符串中
                json.append(prefix, 1, prefix.length() - 1).append(",");

                // 遍历所有统计信息，将其添加到JSON字符串中
                for (String key : allStats.keySet()) {
                    json.append('"').append(key).append('"').append(":").append('"').append(allStats.get(key)).append('"').append(",");
                }

                // 删除最后一个逗号，以保证JSON格式的正确性
                json.deleteCharAt(json.length() - 1);

                // 添加JSON字符串结束的"}"
                json.append("}");

                // 发送构建好的JSON字符串作为消息给发送者
                sender.sendMessage(json.toString());
                return true;

            default:
                sender.sendMessage("未知子命令");
                return true;
        }
    }
}
