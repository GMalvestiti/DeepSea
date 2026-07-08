package net.riser876.deepsea.datagen.neoforge.provider;

//? if neoforge {
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.neoforged.neoforge.common.Tags;
import net.riser876.deepsea.DeepSeaCommon;
import net.riser876.deepsea.registry.DeepSeaTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class DeepSeaEntityTypeTagProviderNeoForge extends EntityTypeTagsProvider {

    public DeepSeaEntityTypeTagProviderNeoForge(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, DeepSeaCommon.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        tag(DeepSeaTags.DEEP_SEA_BOAT).addTag(Tags.EntityTypes.BOATS);
    }
}
//?}
