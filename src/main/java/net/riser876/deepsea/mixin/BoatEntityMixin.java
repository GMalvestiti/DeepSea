package net.riser876.deepsea.mixin;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
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

@Mixin(Boat.class)
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

        Boat boat = (Boat) (Object) this;

        if (boat.level().isClientSide() || !boat.isVehicle()
                || !boat.isInWater() || !boat.getType().is(DeepSeaTags.DEEP_SEA_BOAT)) {
            return;
        }

        ServerLevel world = (ServerLevel) boat.level();

        Pair<String, ChunkPos> cacheKey = Pair.of(world.dimension().location().toString(), boat.chunkPosition());

        Boolean cached = DEEP_SEA_CACHE.getIfPresent(cacheKey);

        boolean isOcean;

        if (cached != null) {
            isOcean = cached;
        } else {
            Holder<Biome> biomeEntry = world.getBiome(boat.blockPosition());
            isOcean = biomeEntry.is(DeepSeaTags.DEEP_SEA_BIOME);
            DEEP_SEA_CACHE.put(cacheKey, isOcean);
        }

        if (isOcean) {
            if (CONFIG.SOUND.DEEP_SEA_PLAY_SOUND) {
                boat.playSound(
                        SoundEvents.PLAYER_ATTACK_STRONG,
                        CONFIG.SOUND.VOLUME,
                        CONFIG.SOUND.PITCH
                );
            }

            if (CONFIG.DISCARD_BOAT) {
                boat.kill();
            } else {
                boat.hurt(boat.damageSources().magic(), CONFIG.BOAT_DAMAGE);
            }
        }
    }
}
