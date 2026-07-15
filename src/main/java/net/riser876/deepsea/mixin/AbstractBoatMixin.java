package net.riser876.deepsea.mixin;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
//? if <1.21.2 {
/*import net.minecraft.world.entity.vehicle.Boat;
*///?} elif <1.21.11 {
/*import net.minecraft.world.entity.vehicle.AbstractBoat;
*///?} else {
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
//?}
import net.minecraft.world.level.biome.Biome;
import net.riser876.deepsea.record.ChunkBiomeKey;
import net.riser876.deepsea.registry.DeepSeaTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.TimeUnit;

import static net.riser876.deepsea.config.ConfigManager.CONFIG;

@Mixin(/*? if <1.21.2 {*//*Boat*//*?} else {*/AbstractBoat/*?}*/.class)
public class AbstractBoatMixin {

    @Unique
    private static final Cache<ChunkBiomeKey, Boolean> DEEP_SEA_CACHE =
        Caffeine.newBuilder()
            .maximumSize(CONFIG.CACHE.CACHE_SIZE)
            .expireAfterAccess(CONFIG.CACHE.CACHE_TIME, TimeUnit.MINUTES)
            .build();

    @Unique
    private boolean deepsea$isOcean = false;

    @Inject(
        method = "tick",
        at = @At(
            value = "INVOKE",
            //? if <1.21.2 {
            /*target = "Lnet/minecraft/world/entity/vehicle/Boat;tickBubbleColumn()V"
            *///?} elif <1.21.11 {
            /*target = "Lnet/minecraft/world/entity/vehicle/AbstractBoat;tickBubbleColumn()V"
            *///?} else {
            target = "Lnet/minecraft/world/entity/vehicle/boat/AbstractBoat;tickBubbleColumn()V"
            //?}
        )
    )
    private void deepsea$tick(CallbackInfo ci) {
        //? if <1.21.2 {
        /*Boat boat = (Boat) (Object) this;
        *///?} else
        AbstractBoat boat = (AbstractBoat) (Object) this;

        if ((boat.tickCount % CONFIG.TICK_INTERVAL) != 0) return;

        if (boat.level().isClientSide()
            || !boat.isVehicle()
            || !boat.isInWater()
            //? if <26.1 {
            /*|| !boat.getType().is(DeepSeaTags.DEEP_SEA_BOAT)) {
            *///?} else
            || !boat.is(DeepSeaTags.DEEP_SEA_BOAT)) {
            return;
        }

        ServerLevel level = (ServerLevel) boat.level();

        final ChunkBiomeKey cacheKey = new ChunkBiomeKey(
            //? if <26.1 {
            /*boat.chunkPosition().toLong(),
            *///?} else
            boat.chunkPosition().pack(),
            //? if <1.21.11 {
            /*level.dimension().location()
             *///?} else
            level.dimension().identifier()
        );

        Boolean cached = DEEP_SEA_CACHE.getIfPresent(cacheKey);

        if (cached != null) {
            this.deepsea$isOcean = cached;
        } else {
            Holder<Biome> biomeEntry = level.getBiome(boat.blockPosition());
            this.deepsea$isOcean = biomeEntry.is(DeepSeaTags.DEEP_SEA_BIOME);
            DEEP_SEA_CACHE.put(cacheKey, this.deepsea$isOcean);
        }

        if (this.deepsea$isOcean) {
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
                //? if <1.21.2 {
                /*boat.hurt(level.damageSources().drown(), CONFIG.BOAT_DAMAGE);
                *///?} else
                boat.hurtServer(level, level.damageSources().drown(), CONFIG.BOAT_DAMAGE);
            }
        }
    }
}
