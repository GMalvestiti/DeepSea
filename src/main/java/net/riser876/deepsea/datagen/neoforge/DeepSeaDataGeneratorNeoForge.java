package net.riser876.deepsea.datagen.neoforge;

//? if neoforge {
/*import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
//? if <1.21.11 {
/^import net.neoforged.neoforge.common.data.ExistingFileHelper;
^///?}
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.riser876.deepsea.DeepSeaCommon;
import net.riser876.deepsea.datagen.neoforge.provider.DeepSeaBiomeTagProviderNeoForge;
import net.riser876.deepsea.datagen.neoforge.provider.DeepSeaEnglishLanguageProviderNeoForge;
import net.riser876.deepsea.datagen.neoforge.provider.DeepSeaEntityTypeTagProviderNeoForge;

import java.util.concurrent.CompletableFuture;

//? if <1.21.11 {
/^@EventBusSubscriber(modid = DeepSeaCommon.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
^///?} else
@EventBusSubscriber(modid = DeepSeaCommon.MOD_ID)
public class DeepSeaDataGeneratorNeoForge {

    //? if <1.21.11 {
    /^@SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(
            event.includeClient(),
            new DeepSeaEnglishLanguageProviderNeoForge(output)
        );

        generator.addProvider(
            event.includeServer(),
            new DeepSeaBiomeTagProviderNeoForge(output, lookupProvider, existingFileHelper)
        );

        generator.addProvider(
            event.includeServer(),
            new DeepSeaEntityTypeTagProviderNeoForge(output, lookupProvider, existingFileHelper)
        );
    }
    ^///?} else {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();

        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.addProvider(new DeepSeaEnglishLanguageProviderNeoForge(output));
        event.addProvider(new DeepSeaEntityTypeTagProviderNeoForge(output, lookupProvider));
        event.addProvider(new DeepSeaBiomeTagProviderNeoForge(output, lookupProvider));
    }
    //?}
}
*///?}
