package net.riser876.deepsea.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;
import net.minecraft.registry.RegistryWrapper;
import net.riser876.deepsea.registry.DeepSeaTags;

import java.util.concurrent.CompletableFuture;

public class DeepSeaTagProvider extends FabricTagProvider.EntityTypeTagProvider {

    public DeepSeaTagProvider (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(DeepSeaTags.DEEP_SEA_BOAT)
                .addOptionalTag(ConventionalEntityTypeTags.BOATS)
                .setReplace(false);
    }
}
