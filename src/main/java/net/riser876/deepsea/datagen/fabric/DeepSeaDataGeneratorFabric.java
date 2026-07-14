package net.riser876.deepsea.datagen.fabric;

//? if fabric {
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.riser876.deepsea.DeepSeaCommon;
import net.riser876.deepsea.datagen.fabric.provider.DeepSeaBiomeTagProviderFabric;
import net.riser876.deepsea.datagen.fabric.provider.DeepSeaEnglishLanguageProviderFabric;
import net.riser876.deepsea.datagen.fabric.provider.DeepSeaEntityTypeTagProviderFabric;

public class DeepSeaDataGeneratorFabric implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(DeepSeaEnglishLanguageProviderFabric::new);
        pack.addProvider(DeepSeaEntityTypeTagProviderFabric::new);
        pack.addProvider(DeepSeaBiomeTagProviderFabric::new);
    }

    @Override
    public String getEffectiveModId() {
        return DeepSeaCommon.MOD_ID;
    }
}
//?}
