package net.riser876.deepsea.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.riser876.deepsea.datagen.provider.DeepSeaBiomeTagProvider;
import net.riser876.deepsea.datagen.provider.DeepSeaEnglishLanguageProvider;
import net.riser876.deepsea.datagen.provider.DeepSeaEntityTypeTagProvider;
import net.riser876.deepsea.util.DeepSeaGlobals;

public class DeepSeaDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(DeepSeaEnglishLanguageProvider::new);
        pack.addProvider(DeepSeaEntityTypeTagProvider::new);
        pack.addProvider(DeepSeaBiomeTagProvider::new);
    }

    @Override
    public String getEffectiveModId() {
        return DeepSeaGlobals.MOD_ID;
    }
}
