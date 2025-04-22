package net.riser876.deepsea.registry;

import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TagRegistry {
    public static final TagKey<EntityType<?>> DEEP_SEA_BOAT = TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of("deepsea", "boat"));
}
