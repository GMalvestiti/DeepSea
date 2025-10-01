package net.riser876.deepsea.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.riser876.deepsea.registry.DeepSeaTag;

import java.util.concurrent.CompletableFuture;

public class DeepSeaEnglishLanguageProvider extends FabricLanguageProvider {

    public DeepSeaEnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(DeepSeaTag.DEEP_SEA_BOAT, "Deep Sea Boat");
    }
}