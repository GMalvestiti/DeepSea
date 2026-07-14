package net.riser876.deepsea.datagen.fabric.provider;

//? if fabric {
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;
import net.minecraft.core.HolderLookup;
import net.riser876.deepsea.registry.DeepSeaTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class DeepSeaEntityTypeTagProviderFabric extends FabricTagProvider./*? if <26.1 {*/EntityTypeTagProvider/*?} else {*//*EntityTypeTagsProvider*//*?}*/ {

    public DeepSeaEntityTypeTagProviderFabric(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        //? if <1.21.9 {
        tag(DeepSeaTags.DEEP_SEA_BOAT).addOptionalTag(ConventionalEntityTypeTags.BOATS.location());
        //?} else
        //builder(DeepSeaTags.DEEP_SEA_BOAT).addOptionalTag(ConventionalEntityTypeTags.BOATS);
    }
}
//?}
