package net.riser876.deepsea.registry;

import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class DeepSeaTags {
    public static final TagKey<EntityType<?>> DEEP_SEA_BOAT = TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of("deepsea", "boat"));
    public static final TagKey<Biome> DEEP_SEA_BIOME = TagKey.of(RegistryKeys.BIOME, Identifier.of("deepsea", "ocean"));
}
