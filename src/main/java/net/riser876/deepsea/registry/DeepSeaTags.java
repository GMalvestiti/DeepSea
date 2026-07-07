package net.riser876.deepsea.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.riser876.deepsea.DeepSeaCommon;

public class DeepSeaTags {
    public static final TagKey<EntityType<?>> DEEP_SEA_BOAT = TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(DeepSeaCommon.MOD_ID, "boat"));
    public static final TagKey<Biome> DEEP_SEA_BIOME = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(DeepSeaCommon.MOD_ID, "ocean"));
}
