package dk.dennist.durnotifier;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <dentych@github> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Dennis T
 * ----------------------------------------------------------------------------
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // EventListener
        EvtListen evtListen = new EvtListen();
        evtListen.durWarning = 10;
        getServer().getPluginManager().registerEvents(evtListen, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("dn")) {
            if ((sender instanceof Player)) {
                Player p = (Player) sender;
                if (args.length == 2 && args[0].equalsIgnoreCase("use")) {
                    if (p.isOp()) {
                        short dur = p.getItemInHand().getDurability();
                        short maxDur = p.getItemInHand().getType().getMaxDurability();
                        if (maxDur > 0) {
                            try {
                                short durUse = Short.parseShort(args[1]);
                                short newDur = (short) (dur + durUse);
                                if (newDur < maxDur) {
                                    p.getItemInHand().setDurability(newDur);
                                    return true;
                                }
                            } catch (NumberFormatException e) {
                                return false;
                            }
                        }
                    }
                }
                else if (args.length == 1 && args[0].equalsIgnoreCase("dur")) {
                    String itemname = p.getItemInHand().getType().toString();
                    short dur = p.getItemInHand().getDurability();
                    short maxDur = p.getItemInHand().getType().getMaxDurability();
                    short durLeft = (short)(maxDur - dur);
                    p.sendMessage("Your " + ChatColor.GREEN + itemname + ChatColor.RESET + " has " + ChatColor.GREEN + durLeft + ChatColor.RESET + " uses left!");
                }
            }
            else // if sender != player || sender != opped.
                sender.sendMessage(ChatColor.RED + "This command can only be used by in-game players.");
        }
        return false;
    }
}
