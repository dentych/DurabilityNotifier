package dk.dennist.durnotifier;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Dennis on 01/04/14.
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

        short dur = p.getItemInHand().getDurability();

        if (dur < 10) {
            p.sendMessage(ChatColor.RED + "WARNING: " + ChatColor.GREEN + "Your tool needs repairing soon!");
        }
    }
}
