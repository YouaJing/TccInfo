package tcc.youajing.tccinfo;

import crypticlib.BukkitPlugin;
import org.bukkit.event.Listener;

/**
 * TccInfo插件的主类，继承自BukkitPlugin，用于处理插件的启用和禁用逻辑
 */
public class TccInfo extends BukkitPlugin {

    /**
     * 在插件启用时调用的方法
     */
    @Override
    public void enable() {
        // 设置"TccInfo"命令的执行器为InfoCommand
        getCommand("tccinfo").setExecutor(new InfoCommand(this));
        // 初始化PrefixManager
        // 前缀管理器实例，用于管理游戏内消息的前缀
        PrefixManager prefixManager = new PrefixManager(getDataFolder());
        // 创建并注册LoginListener监听器
        Listener loginListener = new LoginListener(this, prefixManager);
        // 将监听器注册到服务器的插件管理系统
        getServer().getPluginManager().registerEvents(loginListener, this);
        ObjectPool.plugin = this;
    }

    /**
     * 在插件禁用时调用的方法
     */
    @Override
    public void disable() {
        // 此处可以添加插件禁用时需要执行的清理或保存操作
    }

}
