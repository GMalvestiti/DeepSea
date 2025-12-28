package net.riser876.deepsea.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;

public class DeepSeaTags {
    public static final TagKey<EntityType<?>> DEEP_SEA_BOAT = TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("deepsea", "boat"));
    public static final TagKey<Biome> DEEP_SEA_BIOME = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("deepsea", "ocean"));
}
