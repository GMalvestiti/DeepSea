package net.riser876.deepsea.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.riser876.deepsea.registry.DeepSeaTags;

import java.util.concurrent.CompletableFuture;

public class DeepSeaBiomeTagProvider extends FabricTagProvider<Biome> {

    public DeepSeaBiomeTagProvider(FabricDataOutput output,
                                   CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, Registries.BIOME, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        tag(DeepSeaTags.DEEP_SEA_BIOME)
                .addOptionalTag(ConventionalBiomeTags.IS_OCEAN.location());
    }
}
