package net.riser876.deepsea;

import net.fabricmc.api.ModInitializer;
import net.riser876.deepsea.util.DeepSeaGlobal;

import java.util.Objects;

import static net.riser876.deepsea.config.ConfigManager.CONFIG;

public class DeepSea implements ModInitializer {

    @Override
    public void onInitialize() {
        if (Objects.nonNull(CONFIG) && CONFIG.ENABLED) {
            DeepSeaGlobal.LOGGER.info("[DeepSea] Mod loaded.");
        } else {
            DeepSeaGlobal.LOGGER.info("[DeepSea] Mod disabled.");
        }
    }
}
