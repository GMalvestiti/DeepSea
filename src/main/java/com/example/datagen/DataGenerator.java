package com.example.datagen;

//? if fabric {
import com.example.TemplateModCommon;
import com.example.datagen.provider.FabricEnglishLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(FabricEnglishLanguageProvider::new);
    }

    @Override
    public String getEffectiveModId() {
        return TemplateModCommon.MOD_ID;
    }
}
//?}
