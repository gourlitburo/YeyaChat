package yy.gourlitburo.yeyachat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import yy.gourlitburo.stringformatter.StringFormatter;

class AsyncPlayerChatEventHandler implements Listener {

  private Main plugin;
  private static final StringFormatter formatter = new StringFormatter();
  
  private static final Pattern pingPattern = Pattern.compile("@[A-z0-9_]{1,16}");

  public AsyncPlayerChatEventHandler(Main instance) {
    plugin = instance;
  }

  private static void sendPing(Player player) {
    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
  }

  @EventHandler
  public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
    if (plugin.getConfig().getBoolean("enable")) {
      // color and set format string
      event.setMessage(formatter.processMarkup(formatter.colorize(event.getMessage())));
      event.setFormat(formatter.colorize(plugin.getConfig().getString("template")));

      if (plugin.getConfig().getBoolean("ping.enable") && event.getPlayer().hasPermission(plugin.PERM_PING)) {
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
