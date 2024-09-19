package tcc.youajing.tccinfo;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginListener implements org.bukkit.event.Listener {
    private final PrefixManager prefixManager;

    public LoginListener(TccInfo plugin, PrefixManager prefixManager) {
        this.prefixManager = prefixManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String prefix = PlaceholderAPI.setPlaceholders(player, "%vault_prefix%");
        if (prefix != null) {
            prefix = extractColors(prefix);
        }
//        player.sendMessage(prefix);
        prefixManager.updatePrefix(player.getName(), prefix);
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

        // 如果没有提取到颜色代码，默认设置为 #FFFFFF
        if (colorCodes.length() == 0) {
            colorCodes.append("#FFFFFF");
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
        return String.format("{\"color_code\":\"%s\", \"text\":\"%s\"}", colorCodes, text);
    }


}
