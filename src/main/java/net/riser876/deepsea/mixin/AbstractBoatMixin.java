package net.riser876.deepsea.mixin;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.riser876.deepsea.record.ChunkBiomeKey;
import net.riser876.deepsea.registry.DeepSeaTags;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.TimeUnit;

import static net.riser876.deepsea.config.ConfigManager.CONFIG;

@Mixin(AbstractBoat.class)
public class AbstractBoatMixin {

    @Unique
    private static final Cache<@NotNull ChunkBiomeKey, Boolean> DEEP_SEA_CACHE =
            Caffeine.newBuilder()
                    .maximumSize(CONFIG.CACHE.CACHE_SIZE)
                    .expireAfterAccess(CONFIG.CACHE.CACHE_TIME, TimeUnit.MINUTES)
                    .build();

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/vehicle/boat/AbstractBoat;tickBubbleColumn()V"
            )
    )
    private void onDeepSeaTick(CallbackInfo ci) {
        AbstractBoat boat = (AbstractBoat) (Object) this;

        if ((boat.tickCount % CONFIG.TICK_INTERVAL) != 0) return;

        if (boat.level().isClientSide()
                || !boat.isVehicle()
                || !boat.isInWater()
                || !boat.getType().is(DeepSeaTags.DEEP_SEA_BOAT)) {
            return;
        }

        ServerLevel level = (ServerLevel) boat.level();
        ChunkPos chunkPos = boat.chunkPosition();

        final ChunkBiomeKey cacheKey = new ChunkBiomeKey(
                chunkPos.toLong(),
                level.dimension().identifier()
        );

        Boolean cached = DEEP_SEA_CACHE.getIfPresent(cacheKey);

        final boolean isOcean;

        if (cached != null) {
            isOcean = cached;
        } else {
            Holder<Biome> biomeEntry = level.getBiome(boat.blockPosition());
            isOcean = biomeEntry.is(DeepSeaTags.DEEP_SEA_BIOME);
            DEEP_SEA_CACHE.put(cacheKey, isOcean);
        }

        if (isOcean) {
            if (CONFIG.SOUND.DEEP_SEA_PLAY_SOUND) {
                boat.playSound(
                        SoundEvents.AMBIENT_UNDERWATER_ENTER,
                        CONFIG.SOUND.VOLUME,
                        CONFIG.SOUND.PITCH
                );
            }

            if (CONFIG.DISCARD_BOAT) {
                boat.discard();
            } else {
                boat.hurtServer(level, level.damageSources().drown(), CONFIG.BOAT_DAMAGE);
            }
        }
    }
}