package net.riser876.deepsea.datagen.neoforge.provider;

//? if neoforge {
/*import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
//? if <1.21.4 {
/^import net.neoforged.neoforge.common.data.ExistingFileHelper;
^///?}
import net.riser876.deepsea.DeepSeaCommon;
import net.riser876.deepsea.registry.DeepSeaTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class DeepSeaBiomeTagProviderNeoForge extends BiomeTagsProvider {

    //? if <1.21.4 {
    /^public DeepSeaBiomeTagProviderNeoForge(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, DeepSeaCommon.MOD_ID, existingFileHelper);
    }
    ^///?} else {
    public DeepSeaBiomeTagProviderNeoForge(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, DeepSeaCommon.MOD_ID);
    }
    //?}

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        tag(DeepSeaTags.DEEP_SEA_BIOME).addTag(BiomeTags.IS_OCEAN);
    }
}
*///?}
