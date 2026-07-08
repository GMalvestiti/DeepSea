package net.riser876.deepsea;

//? if neoforge {
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(DeepSeaCommon.MOD_ID)
public class DeepSeaNeoForge {

    public DeepSeaNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        DeepSeaCommon.init();
    }
}
//?}
