package net.riser876.deepsea.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;
import net.minecraft.core.HolderLookup;
import net.riser876.deepsea.registry.DeepSeaTags;

import java.util.concurrent.CompletableFuture;

public class DeepSeaEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {

    public DeepSeaEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        tag(DeepSeaTags.DEEP_SEA_BOAT)
                .addOptionalTag(ConventionalEntityTypeTags.BOATS.location());
    }
}
