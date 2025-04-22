package net.riser876.deepsea.mixin;

import net.minecraft.entity.vehicle.BoatEntity;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.biome.Biome;
import net.riser876.deepsea.config.Config;
import net.riser876.deepsea.registry.TagRegistry;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(BoatEntity.class)
public class BoatEntityMixin {

    @Unique
    private static final Map<RegistryKey<Biome>, Boolean> DEEP_SEA_OCEAN_BIOME_CACHE = new HashMap<>();

    @Unique
    private int deepSeaTickCounter = 0;

    @Inject(
        method = "tick",
        at = @At("HEAD")
    )
    private void onDeepSeaTick(CallbackInfo ci) {
        if (deepSeaTickCounter++ != Config.DEEP_SEA_TICK_INTERVAL) {
            return;
        }

        BoatEntity boat = (BoatEntity)(Object)this;

        if (boat.getWorld().isClient() || boat.getPassengerList().isEmpty() || !boat.isTouchingWater()) {
            deepSeaTickCounter = 0;
            return;
        }

        ServerWorld world = (ServerWorld) boat.getWorld();

        world.getServer().execute(() -> {
            if (boat.isRemoved() || !boat.getType().isIn(TagRegistry.DEEP_SEA_BOAT)) {
                deepSeaTickCounter = 0;
                return;
            }

            RegistryEntry<Biome> biomeEntry = world.getBiome(boat.getBlockPos());
            RegistryKey<Biome> biomeKey = biomeEntry.getKey().orElse(null);

            if (biomeKey != null) {
                boolean isOcean = DEEP_SEA_OCEAN_BIOME_CACHE.computeIfAbsent(biomeKey, key ->
                    biomeEntry.isIn(BiomeTags.IS_OCEAN)
                );

                if (isOcean) {
                    boat.damage(boat.getDamageSources().generic(), 100.0F);
                }
            }

            deepSeaTickCounter = 0;
        });
    }
}
