package yy.gourlitburo.yeyachat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

class AsyncPlayerChatEventHandler implements Listener {

  private Main plugin;
  
  private static final Pattern pingPattern = Pattern.compile("@[A-z0-9_]{3,16}");

  public AsyncPlayerChatEventHandler(Main instance) {
    plugin = instance;
  }

  private static String colorize(String text) {
    return ChatColor.translateAlternateColorCodes('&', text);
  }

  private static void sendPing(Player player) {
    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
  }

  @EventHandler
  public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
    if (plugin.getConfig().getBoolean("enable")) {
      // color and set format string
      String coloredMessage = colorize(event.getMessage());
      event.setMessage(coloredMessage);
      String coloredFormatString = colorize(plugin.getConfig().getString("template"));
      event.setFormat(coloredFormatString);

      if (plugin.getConfig().getBoolean("ping.enable")) {
        // look for pings in text and ping accordingly
        Matcher matcher = pingPattern.matcher(event.getMessage());
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
