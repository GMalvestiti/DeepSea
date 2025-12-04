package net.riser876.deepsea.mixin;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.riser876.deepsea.registry.DeepSeaTags;

import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.TimeUnit;

import static net.riser876.deepsea.config.ConfigManager.CONFIG;

@Mixin(BoatEntity.class)
public class BoatEntityMixin {

    @Unique
    private static final Cache<@NotNull Pair<String, ChunkPos>, Boolean> DEEP_SEA_CACHE =
            Caffeine.newBuilder()
                    .expireAfterWrite(CONFIG.CACHE.CACHE_TIME, TimeUnit.MINUTES)
                    .maximumSize(CONFIG.CACHE.CACHE_SIZE)
                    .build();

    @Unique private int deepSeaTickCounter = 0;

    @Inject(
        method = "tick",
        at = @At("HEAD")
    )
    private void onDeepSeaTick(CallbackInfo ci) {
        if (++deepSeaTickCounter < CONFIG.TICK_INTERVAL) return;

        deepSeaTickCounter = 0;

        BoatEntity boat = (BoatEntity) (Object) this;

        if (boat.getWorld().isClient() || !boat.hasPassengers()
                || !boat.isTouchingWater() || !boat.getType().isIn(DeepSeaTags.DEEP_SEA_BOAT)) {
            return;
        }

        ServerWorld world = (ServerWorld) boat.getWorld();

        Pair<String, ChunkPos> cacheKey = Pair.of(world.getRegistryKey().getValue().toString(), boat.getChunkPos());

        Boolean cached = DEEP_SEA_CACHE.getIfPresent(cacheKey);

        boolean isOcean;

        if (cached != null) {
            isOcean = cached;
        } else {
            RegistryEntry<Biome> biomeEntry = world.getBiome(boat.getBlockPos());
            isOcean = biomeEntry.isIn(DeepSeaTags.DEEP_SEA_BIOME);
            DEEP_SEA_CACHE.put(cacheKey, isOcean);
        }

        if (isOcean) {
            if (CONFIG.SOUND.DEEP_SEA_PLAY_SOUND) {
                boat.playSound(
                        SoundEvents.ENTITY_PLAYER_ATTACK_STRONG,
                        CONFIG.SOUND.VOLUME,
                        CONFIG.SOUND.PITCH
                );
            }

            if (CONFIG.DISCARD_BOAT) {
                boat.kill();
            } else {
                boat.damage(boat.getDamageSources().magic(), CONFIG.BOAT_DAMAGE);
            }
        }
    }
}
