package net.riser876.deepsea.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.riser876.deepsea.config.ConfigManager;
import net.riser876.deepsea.util.DeepSeaGlobals;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static net.riser876.deepsea.config.ConfigManager.CONFIG;

public class DeepSeaMixinPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {
        try {
            ConfigManager.loadConfig();
            ConfigManager.validateConfig();
            DeepSeaGlobals.LOGGER.info("[DeepSea] Configuration loaded.");
        } catch (Exception e) {
            DeepSeaGlobals.LOGGER.error("[DeepSea] Failed to load configuration. Check your deepsea.json config file.", e);
        }
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.contains("net.riser876.deepsea")) {
            if (Objects.isNull(CONFIG)) {
                return false;
            }
            return CONFIG.ENABLED;
        }
        return true;
    }

    @Override
    public String getRefMapperConfig() {return null;}
    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}
    @Override
    public List<String> getMixins() {return null;}
    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
