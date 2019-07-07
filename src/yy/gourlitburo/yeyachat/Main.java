package yy.gourlitburo.yeyachat;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  Logger logger = getLogger();
  Server server = getServer();
  PluginManager manager = Bukkit.getPluginManager();
  StringFormatter formatter = new StringFormatter();

  final String PERM_PING_IMMUNE = "yeyachat.ping.immune";

  @Override
  public void onEnable() {
    manager.registerEvents(new AsyncPlayerChatEventHandler(this), this);

    PluginCommand command = getCommand("yc");
    command.setExecutor(new CommandHandler(this));

    saveDefaultConfig();

    logger.info("YeyaChat ready.");
  }

}
