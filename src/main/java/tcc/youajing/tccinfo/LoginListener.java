package tcc.youajing.tccinfo;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 监听玩家登录事件的类
 * 当玩家加入服务器时，该监听器会获取玩家的前缀并进行相应处理
 */
public class LoginListener implements Listener {
    // 前缀管理器的实例，用于更新玩家的前缀
    private final PrefixManager prefixManager;

    /**
     * LoginListener的构造函数
     * @param plugin TccInfo插件的实例，未在该构造函数中直接使用，但可能是初始化或其他方法调用中的一部分
     * @param prefixManager 前缀管理器，用于管理玩家前缀的更新和维护
     */
    public LoginListener(TccInfo plugin, PrefixManager prefixManager) {
        this.prefixManager = prefixManager;
    }

    /**
     * 当玩家加入服务器时触发的事件处理方法
     * 该方法从PlaceholderAPI获取玩家的前缀，并使用正则表达式提取前缀中的颜色代码
     * 最后，使用PrefixManager更新玩家的前缀信息
     *
     * @param event 玩家加入事件的实例，从中可以获取加入的玩家信息
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // 获取加入事件的玩家实例
        Player player = event.getPlayer();

        // 使用PlaceholderAPI设置玩家前缀，替代前缀中的占位符
        String prefix = PlaceholderAPI.setPlaceholders(player, "%vault_prefix%");

        // 获取玩家首次加入时间戳并转换为日期格式
        String firstJoinTimestampStr = PlaceholderAPI.setPlaceholders(player, "%player_first_join%");
//        int mineBlocks = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statistic_mine_block%"));
//        String mineBlocksString;
//        if (mineBlocks >= 10000) {
//            double mineBlocksInW = mineBlocks / 10000.0;
//            mineBlocksString = String.format("%.1fw", mineBlocksInW);
//        }else {
//            mineBlocksString = String.valueOf(mineBlocks);
//        }
//        mineBlocksString = String.format("{\"mineBlocks\":\"%s\"}", mineBlocksString);

        long firstJoinTimestamp = Long.parseLong(firstJoinTimestampStr);
        Date firstJoinDate = new Date(firstJoinTimestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedFirstJoinDate = dateFormat.format(firstJoinDate);
        String firstJoinDateJson = String.format("{\"firstJoinDate\":\"%s\"}", formattedFirstJoinDate);

        // 提取并保留前缀中的颜色代码
        prefix = extractColors(prefix);

        // 更新并存储玩家的前缀和首次加入日期
        prefixManager.updatePrefix(player.getName(), prefix);
        prefixManager.updateFirstJoinDate(player.getName(), firstJoinDateJson);
//        prefixManager.updateMineBlocks(player.getName(), mineBlocksString);
    }



    /**
     * 从输入字符串中提取颜色代码和文本内容
     *
     * @param input 输入的字符串，可能包含十六进制颜色代码和HTML标签
     * @return 返回一个JSON格式的字符串，包含提取的颜色代码和文本内容
     */
    public static String extractColors(String input) {
        // 正则表达式用于匹配十六进制颜色代码
        String colorRegex = "#[0-9A-Fa-f]{6}";
        Pattern colorPattern = Pattern.compile(colorRegex);
        Matcher colorMatcher = colorPattern.matcher(input);

        StringBuilder colorCodes = new StringBuilder();
        // 遍历输入字符串，查找并收集所有匹配的颜色代码
        while (colorMatcher.find()) {
            if (colorCodes.length() > 0) {
                colorCodes.append(",");
            }
            colorCodes.append(colorMatcher.group());
        }

        // 如果没有提取到颜色代码，默认设置为 #495057,#495057，淡黑色
        if (colorCodes.length() == 0) {
            colorCodes.append("#495057,#495057");
        } else {
            // 检查是否有且只有一个颜色代码，如果是，则复制一个相同的颜色代码
            String[] colors = colorCodes.toString().split(",");
            if (colors.length == 1) {
                colorCodes.append(",").append(colors[0]);
            }
        }

        // 正则表达式用于匹配并提取文本内容，忽略所有的HTML标签
        String textRegex = "<[^>]+>|([^<>]+)";
        Pattern textPattern = Pattern.compile(textRegex);
        Matcher textMatcher = textPattern.matcher(input);
        StringBuilder text = new StringBuilder();
        // 遍历输入字符串，收集并拼接所有提取的文本内容
        while (textMatcher.find()) {
            if (textMatcher.group(1) != null) {
                text.append(textMatcher.group(1));
            }
        }

        // 如果没有提取到文本内容，默认设置为 "你还没有称号捏"
        if (text.length() == 0) {
            text.append("你还没有称号捏");
        }

        // 返回包含颜色代码和文本内容的JSON格式字符串
        return String.format("{\"color_code\":\"%s\",\"text\":\"%s\"}", colorCodes, text);
    }



}
