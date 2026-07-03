package com.example.datagen.provider;

//? if fabric {

//? if <=1.21.11 {
/*import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
 *///?} else
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static com.example.TemplateModCommon.id;

public class FabricEnglishLanguageProvider extends FabricLanguageProvider {

    //? if <=1.21.11 {
    /*public FabricEnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
     *///?} else
    public FabricEnglishLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registries, TranslationBuilder translationBuilder) {
        translationBuilder.add(id("template", "test"), "Deep Sea Boat");
    }
}
//?}
