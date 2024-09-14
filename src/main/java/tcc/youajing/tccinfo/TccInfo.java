package tcc.youajing.tccinfo;

import crypticlib.BukkitPlugin;

public class TccInfo extends BukkitPlugin {

    @Override
    public void enable() {
        //TODO
        getCommand("tccinfo").setExecutor(new InfoCommand(this));
    }

    @Override
    public void disable() {
        //TODO
    }

}