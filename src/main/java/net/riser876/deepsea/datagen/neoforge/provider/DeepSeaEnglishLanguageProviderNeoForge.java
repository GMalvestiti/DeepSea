package net.riser876.deepsea.datagen.neoforge.provider;

//? if neoforge {
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.riser876.deepsea.DeepSeaCommon;
import net.riser876.deepsea.registry.DeepSeaTags;

public class DeepSeaEnglishLanguageProviderNeoForge extends LanguageProvider {

    public DeepSeaEnglishLanguageProviderNeoForge(PackOutput output) {
        super(output, DeepSeaCommon.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(DeepSeaTags.DEEP_SEA_BOAT, "Deep Sea Boat");
        add(DeepSeaTags.DEEP_SEA_BIOME, "Deep Sea Ocean Biome");
    }
}
//?}
