package com.example.datagen.provider;

//? if fabric {
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static com.example.TemplateModCommon.id;

public class FabricEnglishLanguageProvider extends FabricLanguageProvider {

    public FabricEnglishLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registries, TranslationBuilder translationBuilder) {
        translationBuilder.add(id("template", "test"), "Deep Sea Boat");
    }
}
//?}
