package net.riser876.deepsea;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeepSea implements ModInitializer {

    public static final String MOD_ID = "deepsea";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        DeepSea.LOGGER.info("[DeepSea] Mod loaded.");
    }
}
