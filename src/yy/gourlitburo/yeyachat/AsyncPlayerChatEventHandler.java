package yy.gourlitburo.yeyachat;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

class AsyncPlayerChatEventHandler implements Listener {

  private Main plugin;
  
  private static final Pattern pattern = Pattern.compile("@[A-z0-9_]{3,16}");

  public AsyncPlayerChatEventHandler(Main instance) {
    plugin = instance;
  }

  private void sendPing(Player player) {
    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
  }

  @EventHandler
  public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
    if (plugin.getConfig().getBoolean("enable")) {
      // intercept chat event and send out our message
      event.setCancelled(true);
      String template = plugin.getConfig().getString("template");
      String formatted = plugin.formatter.format(template, Map.of(
        "PLAYER", event.getPlayer().getName(),
        "MESSAGE", event.getMessage()
      ));
      for (Player player : plugin.server.getOnlinePlayers()) {
        player.sendMessage(formatted);
      }
      plugin.getServer().getLogger().info(formatted);

      if (plugin.getConfig().getBoolean("ping.enable")) {
        // look for pings in text and ping accordingly
        Matcher matcher = pattern.matcher(event.getMessage());
        while (matcher.find()) {
          String matched = matcher.group(0);
          String name = matched.substring(1, matched.length());
          Player pingee = plugin.server.getPlayer(name);
          if (pingee != null && !pingee.hasPermission(plugin.PERM_PING_IMMUNE)) {
            sendPing(pingee);
          }
        }
      }
    }
  }

}
