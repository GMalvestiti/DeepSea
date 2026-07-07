package net.riser876.deepsea;

//? if fabric {
import net.fabricmc.api.ModInitializer;

public class DeepSeaFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        DeepSeaCommon.init();
    }
}
//?}