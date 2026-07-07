package net.riser876.deepsea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static net.riser876.deepsea.config.ConfigManager.CONFIG;

public class DeepSeaCommon {

    public static final String MOD_ID = /*$ mod_id*/ "";
    public static final Logger LOGGER = LoggerFactory.getLogger(/*$ mod_id*/ "");

    public static void init() {
        if (Objects.nonNull(CONFIG) && CONFIG.ENABLED) {
            DeepSeaCommon.info("Mod loaded.");
        } else {
            DeepSeaCommon.info("Mod disabled.");
        }
    }

    public static void info(String message) {
        DeepSeaCommon.LOGGER.info("[{}] {}", DeepSeaCommon.MOD_ID, message);
    }

    public static void error(String message, Exception e) {
        DeepSeaCommon.LOGGER.error("[{}] {}", DeepSeaCommon.MOD_ID, message, e);
    }
}
