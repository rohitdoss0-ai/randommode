package com.example.randommode;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Properties;
import java.util.Random;

public class RandomMode extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("RandomMode enabled, picking mode...");

        File file = new File("server.properties");
        Properties props = new Properties();

        try (FileInputStream in = new FileInputStream(file)) {
            props.load(in);
        } catch (IOException e) {
            getLogger().severe("Failed to load server.properties");
            return;
        }

        boolean hardcore = new Random().nextBoolean();
        props.setProperty("hardcore", hardcore ? "true" : "false");

        try (FileOutputStream out = new FileOutputStream(file)) {
            props.store(out, "Updated by RandomMode plugin");
            getLogger().info("Selected mode: " + (hardcore ? "Hardcore" : "Survival"));
            Bukkit.getConsoleSender().sendMessage("[RandomMode] Mode set to " + (hardcore ? "Hardcore" : "Survival"));
        } catch (IOException e) {
            getLogger().severe("Failed to save server.properties");
        }
    }
}
