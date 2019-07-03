package yy.gourlitburo.yeyachat;

import java.util.Collection;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

class AsyncPlayerChatEventHandler implements Listener {

  private Main plugin;

  public AsyncPlayerChatEventHandler(Main instance) {
    plugin = instance;
  }

  @EventHandler
  public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
    if (plugin.getConfig().getBoolean("enable")) {
      event.setCancelled(true);
      String template = plugin.getConfig().getString("template");
      String formatted = plugin.formatter.format(template, Map.of(
        "PLAYER", event.getPlayer().getName(),
        "MESSAGE", event.getMessage()
      ));
      Collection<? extends Player> players = plugin.server.getOnlinePlayers();
      for (Player player : players) {
        player.sendMessage(formatted);
      }
      plugin.getServer().getLogger().info(formatted);
    }
  }

}
