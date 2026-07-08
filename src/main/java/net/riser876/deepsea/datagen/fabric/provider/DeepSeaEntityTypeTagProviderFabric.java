package net.riser876.deepsea.datagen.fabric.provider;

//? if fabric {
/*import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;
import net.minecraft.core.HolderLookup;
import net.riser876.deepsea.registry.DeepSeaTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class DeepSeaEntityTypeTagProviderFabric extends FabricTagsProvider.EntityTypeTagsProvider {

    public DeepSeaEntityTypeTagProviderFabric(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        builder(DeepSeaTags.DEEP_SEA_BOAT).addOptionalTag(ConventionalEntityTypeTags.BOATS);
    }
}
*///?}
