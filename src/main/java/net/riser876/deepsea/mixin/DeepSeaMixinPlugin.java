package net.riser876.deepsea.mixin;

import net.riser876.deepsea.config.ConfigManager;
import net.riser876.deepsea.util.DeepSeaGlobal;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

import static net.riser876.deepsea.config.ConfigManager.CONFIG;

public class DeepSeaMixinPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {
        try {
            ConfigManager.loadConfig();
            DeepSeaGlobal.LOGGER.info("[DeepSea] Configuration loaded.");
        } catch (Exception e) {
            DeepSeaGlobal.LOGGER.error("[DeepSea] Failed to load configuration. Check your deepsea.json config file.", e);
        }
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return CONFIG.ENABLED && mixinClassName.startsWith("net.riser876.deepsea");
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
