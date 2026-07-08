package net.riser876.deepsea.datagen.neoforge;

//? if neoforge {
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.riser876.deepsea.DeepSeaCommon;
import net.riser876.deepsea.datagen.neoforge.provider.DeepSeaBiomeTagProviderNeoForge;
import net.riser876.deepsea.datagen.neoforge.provider.DeepSeaEnglishLanguageProviderNeoForge;
import net.riser876.deepsea.datagen.neoforge.provider.DeepSeaEntityTypeTagProviderNeoForge;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = DeepSeaCommon.MOD_ID)
public class DeepSeaDataGeneratorNeoForge {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();

        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.addProvider(new DeepSeaEnglishLanguageProviderNeoForge(output));
        event.addProvider(new DeepSeaEntityTypeTagProviderNeoForge(output, lookupProvider));
        event.addProvider(new DeepSeaBiomeTagProviderNeoForge(output, lookupProvider));
    }
}
//?}
