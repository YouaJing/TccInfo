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
        } else {
            prefix = "-";
        }
//        player.sendMessage(prefix);
        prefixManager.updatePrefix(player.getName(), prefix);
    }

    public static String extractColors(String input) {
        // 提取所有十六进制颜色代码
        String colorRegex = "#[0-9A-Fa-f]{6}";
        Pattern colorPattern = Pattern.compile(colorRegex);
        Matcher colorMatcher = colorPattern.matcher(input);

        StringBuilder colorCodes = new StringBuilder();
        while (colorMatcher.find()) {
            if (colorCodes.length() > 0) {
                colorCodes.append(",");
            }
            colorCodes.append(colorMatcher.group());
        }

        // 提取文本内容，忽略所有<>标签
        String textRegex = "<[^>]+>|([^<>]+)";
        Pattern textPattern = Pattern.compile(textRegex);
        Matcher textMatcher = textPattern.matcher(input);
        StringBuilder text = new StringBuilder();
        while (textMatcher.find()) {
            if (textMatcher.group(1) != null) {
                text.append(textMatcher.group(1));
            }
        }

        return String.format("{\"color_code\":\"%s\", \"text\":\"%s\"}", colorCodes.toString(), text.toString());
    }
}
