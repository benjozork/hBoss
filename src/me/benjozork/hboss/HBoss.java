package me.benjozork.hboss;

import me.benjozork.hboss.handler.internal.CommandHandler;
import me.benjozork.hboss.handler.internal.MessageHandler;
import me.benjozork.hboss.handler.SpawnHandler;

import me.benjozork.hboss.internal.ConfigAccessor;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * Created by Benjozork on 2016-04-26.
 */
public class HBoss extends JavaPlugin {

    private ConfigAccessor messagesConfig  = new ConfigAccessor(this, "messages.yml");
    private Random rand = new Random();

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        MessageHandler.setConfig(getConfig());
        getCommand("hboss").setExecutor(new CommandHandler());

        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {

            @Override
            public void run() {
                SpawnHandler.getSpawns().get(rand.nextInt(SpawnHandler.getSpawns().size())).spawn();
            }

        }, 0L, getConfig().getLong("delay"));

    }
}