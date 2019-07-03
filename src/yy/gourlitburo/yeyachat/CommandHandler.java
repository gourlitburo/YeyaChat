package yy.gourlitburo.yeyachat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

class CommandHandler implements CommandExecutor {

  private Main plugin;

  public CommandHandler(Main instance) {
    plugin = instance;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 0) return false;

    if (args[0].equalsIgnoreCase("reload")) {
      plugin.reloadConfig();
      sender.sendMessage("Reloaded.");
      return true;
    }

    return false;
  }

}
