package com.aly.randomtp.Events;

import com.aly.randomtp.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import java.util.ArrayList;
import java.util.Random;

public class InventoryClick implements Listener {


    public ArrayList<String> netherBiomes = new ArrayList<>();
    public ArrayList<String> overworldBiomes = new ArrayList<>();

    private final Main main;
    public InventoryClick(Main main) {

        this.main = main;

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        netherBiomes.clear();
        for (int i = 0; i<main.getConfig().getStringList("nether.allowed-biomes").size(); i++) {
            netherBiomes.add(main.getConfig().getStringList("nether.allowed-biomes").get(i).toString());
        }
        overworldBiomes.clear();
        for (int i = 0; i<main.getConfig().getStringList("overworld.allowed-biomes").size(); i++) {
            overworldBiomes.add(main.getConfig().getStringList("overworld.allowed-biomes").get(i).toString());
        }

        Player player = (Player) e.getWhoClicked();

        Random radius = new Random();
        int x = radius.nextInt(main.getConfig().getInt("radius.x"));
        int z = radius.nextInt(main.getConfig().getInt("radius.z"));

        if (player.getOpenInventory().getTitle().equals(ChatColor.BLUE.toString() + ChatColor.BOLD + "Random Teleport") && e.getCurrentItem() != null) {

            switch(e.getRawSlot()) {
                case 11:
                    if(main.getConfig().getString("overworld.name") != null) {

                        int y = Bukkit.getWorld(main.getConfig().getString("overworld.world-name")).getHighestBlockYAt(x, z) + 1;
                        Location overworldTP = new Location(Bukkit.getWorld(main.getConfig().getString("overworld.world-name")), x, y, z);
                        if (overworldBiomes.contains(String.valueOf(overworldTP.getBlock().getBiome()))) {
                            player.teleport(overworldTP);
                        } else {
                            player.sendMessage("Sorry biome found didnt match the one in config");
                        }
                    }
                    break;

                case 13:
                    if(main.getConfig().getString("nether.world-name") != null) {

                        int y = Bukkit.getWorld(main.getConfig().getString("nether.world-name")).getHighestBlockYAt(x, z) + 1;

                        Location netherTP = new Location(Bukkit.getWorld(main.getConfig().getString("nether.world-name")), x, y, z);
                        if (netherBiomes.contains(String.valueOf(netherTP.getBlock().getBiome()))) {
                            player.teleport(netherTP);
                        } else {
                            System.out.println(netherTP.getBlock().getBiome());
                            player.sendMessage("Sorry biome found didnt match the one in config");
                        }
                    }
                    break;

                case 15:
                    if(main.getConfig().getString("end-world-name") != null) {

                        int y = Bukkit.getWorld(main.getConfig().getString("end-world-name")).getHighestBlockYAt(x,z) + 1;

                        Location endTP = new Location(Bukkit.getWorld(main.getConfig().getString("end-world-name")), x, y, z);
                        if (endTP.getBlock().getType() != Material.AIR) {
                            player.teleport(endTP);
                        }else {
                            player.sendMessage("no safe location found, please try again");
                        }

                    }
                    break;

            }

        }

    }
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (player.getOpenInventory().getTitle().equals(ChatColor.BLUE.toString() + ChatColor.BOLD + "Random Teleport")) {
            System.out.println("test");
            e.setCancelled(true);
        }
    }

}
