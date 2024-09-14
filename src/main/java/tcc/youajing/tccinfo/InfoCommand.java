package tcc.youajing.tccinfo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

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

        switch (subCommand) {
            case "playtime":
                if (args.length < 2) {
                    sender.sendMessage("用法: /tccinfo playtime <玩家ID>");
                    return true;
                }
                Player player = Bukkit.getPlayer(args[1]);
                if (player == null) {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                    int playTime = offlinePlayer.getStatistic(Statistic.PLAY_ONE_MINUTE) / 72000;
                    sender.sendMessage(String.valueOf(playTime));
                }else {
                    int playTime = player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 72000;
                    sender.sendMessage(String.valueOf(playTime));
                }
                return true;

            case "bedcount":
                if (args.length < 2) {
                    sender.sendMessage("用法: /tccinfo bedcount <玩家ID>");
                    return true;
                }
                player = Bukkit.getPlayer(args[1]);
                if (player == null) {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                    int bedCount = offlinePlayer.getStatistic(Statistic.SLEEP_IN_BED);
                    sender.sendMessage(String.valueOf(bedCount));
                }else {
                    int bedCount = player.getStatistic(Statistic.SLEEP_IN_BED);
                    sender.sendMessage(String.valueOf(bedCount));
                }
                return true;

            default:
                sender.sendMessage("未知子命令，请使用 /tccinfo playtime 或 /tccinfo bedcount。");
                return true;
        }
    }
}
