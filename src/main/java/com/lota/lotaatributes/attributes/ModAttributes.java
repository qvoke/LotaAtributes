package com.lota.lotaatributes.attributes;

import com.lota.lotaatributes.LotaAttributesMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = LotaAttributesMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = 
            DeferredRegister.create(ForgeRegistries.ATTRIBUTES, LotaAttributesMod.MOD_ID);

    public static final RegistryObject<Attribute> HEAVY_WEAPONS_DAMAGE = ATTRIBUTES.register(
            "heavy_weapons_damage",
            () -> new RangedAttribute(
                    "attribute.name.lotaatributes.heavy_weapons_damage",
                    1.0,  // default value
                    -100.0,  // min value
                    1024.0 // max value
            ).setSyncable(true)
    );

    public static final RegistryObject<Attribute> BLADES_DAMAGE = ATTRIBUTES.register(
            "blades_damage",
            () -> new RangedAttribute(
                    "attribute.name.lotaatributes.blades_damage",
                    1.0,  // default value
                    -100.0,  // min value
                    1024.0 // max value
            ).setSyncable(true)
    );

    public static final RegistryObject<Attribute> KATANAS_DAMAGE = ATTRIBUTES.register(
            "katanas_damage",
            () -> new RangedAttribute(
                    "attribute.name.lotaatributes.katanas_damage",
                    1.0,
                    -100.0,
                    1024.0
            ).setSyncable(true)
    );

    public static final RegistryObject<Attribute> MAGICAL_ITEMS_DAMAGE = ATTRIBUTES.register(
            "magical_items_damage",
            () -> new RangedAttribute(
                    "attribute.name.lotaatributes.magical_items_damage",
                    1.0,
                    -100.0,
                    1024.0
            ).setSyncable(true)
    );

    public static final RegistryObject<Attribute> THROWING_DAMAGE = ATTRIBUTES.register(
            "throwing_damage",
            () -> new RangedAttribute(
                    "attribute.name.lotaatributes.throwing_damage",
                    1.0,
                    -100.0,
                    1024.0
            ).setSyncable(true)
    );

    /**
     * Stealth attribute - reduces monster aggression range.
     * Value represents percentage reduction (0-90%).
     */
    public static final RegistryObject<Attribute> STEALTH = create("stealth", 90d);

    /**
     * Evasion attribute - chance to dodge attacks.
     * Uses diminishing returns formula: evasionChance = (evasion * 0.05) / (1 + evasion * 0.05) * 0.8
     */
    public static final RegistryObject<Attribute> EVASION = create("evasion", 100d);

    private static RegistryObject<Attribute> create(String name, double maxValue) {
        String descriptionId = "attribute.name.lotaatributes.%s".formatted(name);
        return ATTRIBUTES.register(
                name, () -> new RangedAttribute(descriptionId, 0d, 0d, maxValue).setSyncable(true));
    }

    @SubscribeEvent
    public static void attachAttributes(EntityAttributeModificationEvent event) {
        ATTRIBUTES.getEntries().stream()
                .map(RegistryObject::get)
                .forEach(attribute -> event.add(EntityType.PLAYER, attribute));
    }
}
