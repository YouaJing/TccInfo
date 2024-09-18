package tcc.youajing.tccinfo;

import crypticlib.BukkitPlugin;
import org.bukkit.event.Listener;


public class TccInfo extends BukkitPlugin {
    private PrefixManager prefixManager;
    @Override
    public void enable() {
        //TODO
        getCommand("tccinfo").setExecutor(new InfoCommand(this));
        // 初始化PrefixManager
        prefixManager = new PrefixManager(getDataFolder());
        //注册监听器
        Listener LoginListener = new LoginListener(this, prefixManager);
        getServer().getPluginManager().registerEvents(LoginListener, this);
    }

    @Override
    public void disable() {
        //TODO
    }

}
