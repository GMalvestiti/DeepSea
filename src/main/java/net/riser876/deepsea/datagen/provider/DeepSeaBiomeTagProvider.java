package net.riser876.deepsea.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.biome.Biome;
import net.riser876.deepsea.registry.DeepSeaTags;

import java.util.concurrent.CompletableFuture;

public class DeepSeaBiomeTagProvider extends FabricTagProvider<Biome> {

    public DeepSeaBiomeTagProvider(FabricDataOutput output,
                                   CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, RegistryKeys.BIOME, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(DeepSeaTags.DEEP_SEA_BIOME)
                .addOptionalTag(ConventionalBiomeTags.IS_OCEAN)
                .setReplace(false);
    }
}
