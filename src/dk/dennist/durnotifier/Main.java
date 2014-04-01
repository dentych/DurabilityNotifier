package dk.dennist.durnotifier;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <dentych@github> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Dennis T
 * ----------------------------------------------------------------------------
 */
public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // EventListener
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        short durWarning = 10;
        int dur = p.getItemInHand().getDurability();
        int maxDur = p.getItemInHand().getType().getMaxDurability();
        int durLeft = maxDur - dur;

        if (maxDur > 0) {
            String itemname = p.getItemInHand().getType().toString();

            // Debugging messages. Disabled for production.
            // p.sendMessage("Durability of " + itemname + ": " + durLeft);
            // p.sendMessage("Max dur of " + itemname + ": " + maxDur);
            if (p.getItemInHand().getType() != Material.AIR && durLeft < durWarning) {
                p.sendMessage(ChatColor.RED + "WARNING: " + ChatColor.GREEN + "Your " + itemname + " needs repairing soon!");
            }
        } // else, item is not a tool.
    }
}
