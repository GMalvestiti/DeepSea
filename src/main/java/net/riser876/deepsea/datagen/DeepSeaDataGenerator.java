package net.riser876.deepsea.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.riser876.deepsea.datagen.provider.DeepSeaEnglishLanguageProvider;
import net.riser876.deepsea.datagen.provider.DeepSeaTagProvider;
import net.riser876.deepsea.util.DeepSeaGlobal;

public class DeepSeaDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(DeepSeaEnglishLanguageProvider::new);
        pack.addProvider(DeepSeaTagProvider::new);
    }

    @Override
    public String getEffectiveModId() {
        return DeepSeaGlobal.MOD_ID;
    }
}
