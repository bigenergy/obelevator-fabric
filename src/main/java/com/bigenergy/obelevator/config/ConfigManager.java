package com.bigenergy.obelevator.config;

import com.bigenergy.obelevator.OpenBlocksElevator;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.nio.file.Files;

public class ConfigManager {
    protected static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    public static Config config;
    private static File configFile;

    public static void init() {
        // Getting a config file path
        configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), OpenBlocksElevator.MOD_ID + ".json");
        try {
            if (configFile.exists()) {
                // Read the config file if it exists
                OpenBlocksElevator.LOGGER.info("Loading configuration...");
                config = GSON.fromJson(Files.readString(configFile.toPath()), Config.class);
            } else {
                // Create default config file if it doesn't exist
                OpenBlocksElevator.LOGGER.info("Creating default configuration...");
                config = new Config();
                saveConfig();
            }
        } catch (Exception exception) {
            OpenBlocksElevator.LOGGER.error("Failed to load configuration.", exception);
        }
    }

    public static void saveConfig() {
        try {
            // Write configuration to the file
            Files.writeString(configFile.toPath(), config.serialize());
        } catch (Exception exception) {
            OpenBlocksElevator.LOGGER.error("Failed to save configuration.", exception);
        }
    }
}
