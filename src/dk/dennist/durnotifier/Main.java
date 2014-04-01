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

        int dur = p.getItemInHand().getDurability();
        int maxDur = p.getItemInHand().getType().getMaxDurability();
        int durLeft = maxDur - dur;

        String itemname = p.getItemInHand().toString();

        p.sendMessage("Durability of " + itemname + ": " + dur);
        if (p.getItemInHand().getType() != Material.AIR && durLeft < 25) {
            p.sendMessage(ChatColor.RED + "WARNING: " + ChatColor.GREEN + "Your tool needs repairing soon!");
        }
    }
}
