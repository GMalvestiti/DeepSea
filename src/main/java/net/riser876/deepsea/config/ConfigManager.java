package net.riser876.deepsea.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ConfigManager {

    private static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();
    private static final String CONFIG_FILE_NAME = "deepsea.json";
    public static Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE_NAME);

    public static Config CONFIG;

    public static void loadConfig() {
        if (Files.notExists(ConfigManager.CONFIG_PATH)) {
            loadDefaultConfig();
            return;
        }

        try {
            String json = Files.readString(ConfigManager.CONFIG_PATH);
            ConfigManager.CONFIG = GSON.fromJson(json, Config.class);
            ConfigManager.saveConfig();
        } catch (IOException | JsonSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadDefaultConfig() {
        ConfigManager.CONFIG = new Config();
        ConfigManager.saveConfig();
    }

    private static void saveConfig() {
        try {
            String json = GSON.toJson(ConfigManager.CONFIG);
            Files.write(ConfigManager.CONFIG_PATH, json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void validateConfig() {
        if (Objects.isNull(CONFIG)) {
            return;
        }

        if (CONFIG.TICK_INTERVAL < 1) {
            CONFIG.TICK_INTERVAL = 100;
        }

        if (CONFIG.BOAT_DAMAGE < 0.0F) {
            CONFIG.BOAT_DAMAGE = 100.0F;
        }

        if (CONFIG.CACHE.CACHE_SIZE < 1) {
            CONFIG.CACHE.CACHE_SIZE = 500;
        }

        if (CONFIG.CACHE.CACHE_TIME < 1) {
            CONFIG.CACHE.CACHE_TIME = 240;
        }

        if (CONFIG.SOUND.VOLUME < 0.0F) {
            CONFIG.SOUND.VOLUME = 1.0F;
        }

        if (CONFIG.SOUND.PITCH < 0.0F) {
            CONFIG.SOUND.PITCH = 1.0F;
        }
    }
}