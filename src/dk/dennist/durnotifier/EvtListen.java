package dk.dennist.durnotifier;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <dentych@github> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Dennis T
 * ----------------------------------------------------------------------------
 */
public class EvtListen implements Listener {

    public short durWarning = 0;
    @EventHandler
    void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        int dur = p.getItemInHand().getDurability();
        int maxDur = p.getItemInHand().getType().getMaxDurability();
        int durLeft = maxDur - dur;

        if (maxDur > 0) {
            String itemname = p.getItemInHand().getType().toString();

            // Debugging messages. Disabled for production.
            // p.sendMessage("Durability of " + itemname + ": " + durLeft);
            // p.sendMessage("Max dur of " + itemname + ": " + maxDur);
            if (p.getItemInHand().getType() != Material.AIR && durLeft < durWarning) {
                p.sendMessage(ChatColor.RED + "WARNING: " + ChatColor.RESET + "Your " + ChatColor.GREEN + itemname + ChatColor.RESET + " has " + ChatColor.GREEN + durLeft + ChatColor.RESET + " uses left!");
            }
        } // else, item is not a tool.
    }

    @EventHandler
    public void onEntityDmg(EntityDamageByEntityEvent evt) {
        if (evt.getDamager() instanceof Player) {
            Player p = (Player) evt.getDamager();
            int dur = p.getItemInHand().getDurability();
            int maxDur = p.getItemInHand().getType().getMaxDurability();
            int durLeft = maxDur-dur;

            if (maxDur > 0) {
                String itemname = p.getItemInHand().getType().toString();

                if (durLeft < durWarning) {
                    p.sendMessage(ChatColor.RED + "WARNING: " + ChatColor.RESET + "Your " + ChatColor.GREEN + itemname + ChatColor.RESET + " has " + ChatColor.GREEN + durLeft + ChatColor.RESET + " uses left!");
                }
            }
        }
    }
}
