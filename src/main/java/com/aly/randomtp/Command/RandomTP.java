package com.aly.randomtp.Command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RandomTP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // CHECK IF THE SENDER IS A PLAYER
        if (sender instanceof Player) {

            Player player =  (Player) sender;
            if (player.hasPermission("rtp.use")) {

                Inventory inv = Bukkit.createInventory(player,27, ChatColor.BLUE.toString() + ChatColor.BOLD + "Random Teleport");

                // OVERWORLD
                ItemStack overworld = new ItemStack(Material.GRASS_BLOCK, 1);
                ItemMeta overworldMeta = overworld.getItemMeta();
                overworldMeta.setDisplayName(ChatColor.GREEN + "OverWorld");
                overworld.setItemMeta(overworldMeta);
                inv.setItem(11,overworld);

                // NETHER
                ItemStack nether = new ItemStack(Material.NETHERRACK, 1);
                ItemMeta netherMeta = nether.getItemMeta();
                netherMeta.setDisplayName(ChatColor.RED + "Nether");
                nether.setItemMeta(netherMeta);
                inv.setItem(13,nether);

                // END
                ItemStack end = new ItemStack(Material.END_STONE, 1);
                ItemMeta endMeta = end.getItemMeta();
                endMeta.setDisplayName(ChatColor.DARK_GRAY + "The End");
                end.setItemMeta(endMeta);
                inv.setItem(15,end);

                // GLASS
                for (int i = 0; i<=9; i++) {
                    ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
                    inv.setItem(i,glass);
                }
                for (int i = 17; i<=26; i++) {
                    ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
                    inv.setItem(i,glass);
                }

                // OPEN THE GUI
                player.openInventory(inv);

            } else {
                player.sendMessage(ChatColor.RED + "You don't have permissions to use this command");
            }



        } else {
            System.out.println("Only players can run this command");
        }

        return false;
    }
}
