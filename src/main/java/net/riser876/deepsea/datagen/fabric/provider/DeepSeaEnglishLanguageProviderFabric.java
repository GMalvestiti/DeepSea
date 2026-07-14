package net.riser876.deepsea.datagen.fabric.provider;

//? if fabric {
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.riser876.deepsea.registry.DeepSeaTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class DeepSeaEnglishLanguageProviderFabric extends FabricLanguageProvider {

    public DeepSeaEnglishLanguageProviderFabric(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider registries, TranslationBuilder translationBuilder) {
        translationBuilder.add(DeepSeaTags.DEEP_SEA_BOAT, "Deep Sea Boat");
        translationBuilder.add(DeepSeaTags.DEEP_SEA_BIOME, "Deep Sea Ocean Biome");
    }
}
//?}
