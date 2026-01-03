package com.lota.lotaatributes.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import java.util.HashSet;
import java.util.Set;

public class WeaponCategories {
    
    // Weapon sets
    private static final Set<String> HEAVY_WEAPONS = new HashSet<>();
    private static final Set<String> BLADES = new HashSet<>();
    private static final Set<String> KATANAS = new HashSet<>();
    private static final Set<String> MAGICAL_ITEMS = new HashSet<>();
    private static final Set<String> THROWING = new HashSet<>();
    
    static {
        initializeHeavyWeapons();
        initializeBlades();
        // Initialize others if needed, currently empty by default
        loadConfig();
    }
    
    // Config class to match JSON structure
    private static class WeaponConfig {
        List<String> katanas = new ArrayList<>();
        List<String> heavy_weapons = new ArrayList<>();
        List<String> blades = new ArrayList<>();
        List<String> magical_items = new ArrayList<>();
        List<String> throwing = new ArrayList<>();
    }

    private static void loadConfig() {
        try {
            Path configDir = FMLPaths.CONFIGDIR.get().resolve("lotaattributes");
            Path configPath = configDir.resolve("weapon_types.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Create directory if it doesn't exist
            if (!Files.exists(configDir)) {
                Files.createDirectories(configDir);
            }

            if (Files.exists(configPath)) {
                try (Reader reader = Files.newBufferedReader(configPath)) {
                    WeaponConfig config = gson.fromJson(reader, WeaponConfig.class);
                    
                    // Only override if config is valid (lists are not null)
                    if (config != null) {
                        // User config overrides defaults if present
                        if (config.heavy_weapons != null && !config.heavy_weapons.isEmpty()) {
                            HEAVY_WEAPONS.clear();
                            HEAVY_WEAPONS.addAll(config.heavy_weapons);
                        }
                        if (config.blades != null && !config.blades.isEmpty()) {
                            BLADES.clear();
                            BLADES.addAll(config.blades);
                        }
                        if (config.katanas != null && !config.katanas.isEmpty()) {
                            KATANAS.clear();
                            KATANAS.addAll(config.katanas);
                        }
                        if (config.magical_items != null && !config.magical_items.isEmpty()) {
                            MAGICAL_ITEMS.clear();
                            MAGICAL_ITEMS.addAll(config.magical_items);
                        }
                        if (config.throwing != null && !config.throwing.isEmpty()) {
                            THROWING.clear();
                            THROWING.addAll(config.throwing);
                        }
                    }
                }
            } else {
                // Create default config from initialized values
                WeaponConfig defaultConfig = new WeaponConfig();
                defaultConfig.heavy_weapons.addAll(HEAVY_WEAPONS);
                defaultConfig.blades.addAll(BLADES);
                // Others are empty by default
                
                try (Writer writer = Files.newBufferedWriter(configPath)) {
                    gson.toJson(defaultConfig, writer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void initializeHeavyWeapons() {
        // Stalwart Dungeons hammers
        HEAVY_WEAPONS.add("stalwart_dungeons:wooden_hammer");
        HEAVY_WEAPONS.add("stalwart_dungeons:stone_hammer");
        HEAVY_WEAPONS.add("stalwart_dungeons:iron_hammer");
        HEAVY_WEAPONS.add("stalwart_dungeons:golden_hammer");
        HEAVY_WEAPONS.add("stalwart_dungeons:diamond_hammer");
        HEAVY_WEAPONS.add("stalwart_dungeons:netherite_hammer");
        HEAVY_WEAPONS.add("stalwart_dungeons:tungsten_hammer");
        HEAVY_WEAPONS.add("stalwart_dungeons:nether_hammer");
        
        // Cataclysm weapons
        HEAVY_WEAPONS.add("cataclysm:the_incinerator");
        HEAVY_WEAPONS.add("cataclysm:the_annihilator");
        HEAVY_WEAPONS.add("cataclysm:the_immolator");
        HEAVY_WEAPONS.add("cataclysm:void_forge");
        HEAVY_WEAPONS.add("cataclysm:astrape");
        HEAVY_WEAPONS.add("cataclysm:ceraunus");
        HEAVY_WEAPONS.add("cataclysm:infernal_forge");
        
        // Call from the Depth weapons
        HEAVY_WEAPONS.add("callfromthedepth_:soulmarblehhammer");
        HEAVY_WEAPONS.add("callfromthedepth_:dark_abyssscythe");
        HEAVY_WEAPONS.add("callfromthedepth_:soulclaymore");
        
        // Dungeons and Combat weapons
        HEAVY_WEAPONS.add("dungeons_and_combat:dragon_greatsword_bone");
        HEAVY_WEAPONS.add("dungeons_and_combat:dragon_greatsword_fire");
        HEAVY_WEAPONS.add("dungeons_and_combat:dragongreatsword_ice");
        HEAVY_WEAPONS.add("dungeons_and_combat:dragongreatsword_lightning");
        HEAVY_WEAPONS.add("dungeons_and_combat:dragonsteel_fire_long_sword");
        HEAVY_WEAPONS.add("dungeons_and_combat:dragonsteel_fire_greatsword");
        HEAVY_WEAPONS.add("dungeons_and_combat:dragonsteel_ice_long_sword");
        HEAVY_WEAPONS.add("dungeons_and_combat:dragonsteel_ice_greatsword");
        HEAVY_WEAPONS.add("dungeons_and_combat:dragonsteel_lightning_long_sword");
        HEAVY_WEAPONS.add("dungeons_and_combat:dragonsteel_lightning_greatsword");
        HEAVY_WEAPONS.add("dungeons_and_combat:wooden_greathammer");
        HEAVY_WEAPONS.add("dungeons_and_combat:stone_mace");
        HEAVY_WEAPONS.add("dungeons_and_combat:copper_axe_war");
        HEAVY_WEAPONS.add("dungeons_and_combat:copper_greathammer");
        HEAVY_WEAPONS.add("dungeons_and_combat:golden_glaive");
        HEAVY_WEAPONS.add("dungeons_and_combat:iron_mace");
        HEAVY_WEAPONS.add("dungeons_and_combat:iron_greataxe");
        HEAVY_WEAPONS.add("dungeons_and_combat:bronze_war_hammer");
        HEAVY_WEAPONS.add("dungeons_and_combat:steel_greataxe");
        HEAVY_WEAPONS.add("dungeons_and_combat:steel_wolf_greatsword");
        HEAVY_WEAPONS.add("dungeons_and_combat:titanium_greatsword");
        HEAVY_WEAPONS.add("dungeons_and_combat:sandstone_axe_war");
        HEAVY_WEAPONS.add("dungeons_and_combat:molten_dark_steel_sword");
        HEAVY_WEAPONS.add("dungeons_and_combat:molten_dark_steel_greataxe");
        HEAVY_WEAPONS.add("dungeons_and_combat:netherite_claymore");
        HEAVY_WEAPONS.add("dungeons_and_combat:molten_blazing_greathammer");
        HEAVY_WEAPONS.add("dungeons_and_combat:molten_blazing_sword");
        HEAVY_WEAPONS.add("dungeons_and_combat:cursed_molten_blazing_sword");
        HEAVY_WEAPONS.add("dungeons_and_combat:cursed_molten_blazing_greathammer");
        HEAVY_WEAPONS.add("dungeons_and_combat:acid_molten_blazing_sword");
        HEAVY_WEAPONS.add("dungeons_and_combat:acid_molten_blazing_greathammer");
        HEAVY_WEAPONS.add("dungeons_and_combat:ebony_long_sword");
        HEAVY_WEAPONS.add("dungeons_and_combat:crimson_long_sword");
        HEAVY_WEAPONS.add("dungeons_and_combat:crimson_scythe");
        HEAVY_WEAPONS.add("dungeons_and_combat:ebony_scythe");
        HEAVY_WEAPONS.add("dungeons_and_combat:rusty_claymor");
        HEAVY_WEAPONS.add("dungeons_and_combat:executioner_axe");
        HEAVY_WEAPONS.add("dungeons_and_combat:heavy_hammer");
        HEAVY_WEAPONS.add("dungeons_and_combat:old_axe");
        HEAVY_WEAPONS.add("dungeons_and_combat:saw_cleaver_glaive");
        HEAVY_WEAPONS.add("dungeons_and_combat:true_claymor");
        HEAVY_WEAPONS.add("dungeons_and_combat:morning_star_mace");
        HEAVY_WEAPONS.add("dungeons_and_combat:oath_sword");
        HEAVY_WEAPONS.add("dungeons_and_combat:the_gravedigger_greataxe");
        HEAVY_WEAPONS.add("dungeons_and_combat:sandstorm_greatsword");
        HEAVY_WEAPONS.add("dungeons_and_combat:molten_bone_greatsword");
        
        // EEEAB's Mobs
        HEAVY_WEAPONS.add("eeeabsmobs:guardian_axe");
        
        // Epic Fight greatswords and longswords
        HEAVY_WEAPONS.add("epicfight:stone_greatsword");
        HEAVY_WEAPONS.add("epicfight:iron_greatsword");
        HEAVY_WEAPONS.add("epicfight:golden_greatsword");
        HEAVY_WEAPONS.add("epicfight:diamond_greatsword");
        HEAVY_WEAPONS.add("epicfight:netherite_greatsword");
        HEAVY_WEAPONS.add("epicfight:iron_longsword");
        HEAVY_WEAPONS.add("epicfight:golden_longsword");
        HEAVY_WEAPONS.add("epicfight:diamond_longsword");
        HEAVY_WEAPONS.add("epicfight:netherite_longsword");
        
        // Ice and Fire
        HEAVY_WEAPONS.add("iceandfire:dragonsteel_fire_sword");
        HEAVY_WEAPONS.add("iceandfire:dragonsteel_ice_sword");
        HEAVY_WEAPONS.add("iceandfire:dragonsteel_lightning_sword");
        HEAVY_WEAPONS.add("iceandfire:troll_weapon_axe");
        HEAVY_WEAPONS.add("iceandfire:troll_weapon_column");
        HEAVY_WEAPONS.add("iceandfire:troll_weapon_column_forest");
        HEAVY_WEAPONS.add("iceandfire:troll_weapon_column_frost");
        HEAVY_WEAPONS.add("iceandfire:troll_weapon_hammer");
        HEAVY_WEAPONS.add("iceandfire:troll_weapon_trunk");
        HEAVY_WEAPONS.add("iceandfire:troll_weapon_trunk_frost");
        
        // Legendary Monsters
        HEAVY_WEAPONS.add("legendary_monsters:chorus_blade");
        HEAVY_WEAPONS.add("legendary_monsters:dinosaur_bone_club");
        HEAVY_WEAPONS.add("legendary_monsters:withered_scythe");
        HEAVY_WEAPONS.add("legendary_monsters:the_great_frost");
        HEAVY_WEAPONS.add("legendary_monsters:axe_of_lightning");
        HEAVY_WEAPONS.add("legendary_monsters:mossy_hammer");
        HEAVY_WEAPONS.add("legendary_monsters:monstrous_anchor");
        HEAVY_WEAPONS.add("legendary_monsters:golden_halbert");
        HEAVY_WEAPONS.add("legendary_monsters:soul_great_sword");
        
        // Mowzie's Mobs
        HEAVY_WEAPONS.add("mowziesmobs:wrought_axe");
        
        // Samurai Dynasty
        HEAVY_WEAPONS.add("samurai_dynasty:tetsubo");
        HEAVY_WEAPONS.add("samurai_dynasty:tetsubo_netherite");
        
        // Simply Swords
        HEAVY_WEAPONS.add("simplyswords:iron_claymore");
        HEAVY_WEAPONS.add("simplyswords:iron_greathammer");
        HEAVY_WEAPONS.add("simplyswords:iron_greataxe");
        HEAVY_WEAPONS.add("simplyswords:iron_halberd");
        HEAVY_WEAPONS.add("simplyswords:gold_claymore");
        HEAVY_WEAPONS.add("simplyswords:gold_greathammer");
        HEAVY_WEAPONS.add("simplyswords:gold_greataxe");
        HEAVY_WEAPONS.add("simplyswords:gold_halberd");
        HEAVY_WEAPONS.add("simplyswords:diamond_claymore");
        HEAVY_WEAPONS.add("simplyswords:diamond_greathammer");
        HEAVY_WEAPONS.add("simplyswords:diamond_greataxe");
        HEAVY_WEAPONS.add("simplyswords:diamond_halberd");
        HEAVY_WEAPONS.add("simplyswords:netherite_claymore");
        HEAVY_WEAPONS.add("simplyswords:netherite_greathammer");
        HEAVY_WEAPONS.add("simplyswords:netherite_greataxe");
        HEAVY_WEAPONS.add("simplyswords:netherite_halberd");
        HEAVY_WEAPONS.add("simplyswords:runic_claymore");
        HEAVY_WEAPONS.add("simplyswords:runic_greataxe");
        HEAVY_WEAPONS.add("simplyswords:runic_greathammer");
        HEAVY_WEAPONS.add("simplyswords:runic_halberd");
        HEAVY_WEAPONS.add("simplyswords:hearthflame");
        HEAVY_WEAPONS.add("simplyswords:soulkeeper");
        HEAVY_WEAPONS.add("simplyswords:twisted_blade");
        HEAVY_WEAPONS.add("simplyswords:frostfall");
        HEAVY_WEAPONS.add("simplyswords:livyatan");
        HEAVY_WEAPONS.add("simplyswords:icewhisper");
        HEAVY_WEAPONS.add("simplyswords:arcanethyst");
        HEAVY_WEAPONS.add("simplyswords:thunderbrand");
        HEAVY_WEAPONS.add("simplyswords:mjolnir");
        HEAVY_WEAPONS.add("simplyswords:hiveheart");
        HEAVY_WEAPONS.add("simplyswords:ribboncleaver");
        HEAVY_WEAPONS.add("simplyswords:enigma");
        HEAVY_WEAPONS.add("simplyswords:slumbering_lichblade");
        HEAVY_WEAPONS.add("simplyswords:walking_lichblade");
        HEAVY_WEAPONS.add("simplyswords:awakened_lichblade");
        HEAVY_WEAPONS.add("simplyswords:soulpyre");
        HEAVY_WEAPONS.add("simplyswords:molten_edge");
        HEAVY_WEAPONS.add("simplyswords:stormbringer");
        
        // Valarian Conquest
        HEAVY_WEAPONS.add("valarian_conquest:light_axe");
        HEAVY_WEAPONS.add("valarian_conquest:heavy_axe");
        HEAVY_WEAPONS.add("valarian_conquest:draugr_axe");
        HEAVY_WEAPONS.add("valarian_conquest:dwarven_axe");
        HEAVY_WEAPONS.add("valarian_conquest:soldiers_spear");
        HEAVY_WEAPONS.add("valarian_conquest:soldiers_halberd");
        HEAVY_WEAPONS.add("valarian_conquest:knights_spear");
        HEAVY_WEAPONS.add("valarian_conquest:refined_spear");
        HEAVY_WEAPONS.add("valarian_conquest:gilded_legion_spear");
        HEAVY_WEAPONS.add("valarian_conquest:draugr_spear");
        
        // Born in Chaos
        HEAVY_WEAPONS.add("born_in_chaos_v1:darkwarblade");
        HEAVY_WEAPONS.add("born_in_chaos_v1:skullbreaker_hammer");
        HEAVY_WEAPONS.add("born_in_chaos_v1:great_reaper_axe");
        HEAVY_WEAPONS.add("born_in_chaos_v1:sweet_axe");
        HEAVY_WEAPONS.add("born_in_chaos_v1:wood_solitter_axe");
        HEAVY_WEAPONS.add("born_in_chaos_v1:nut_hammer");
    }
    
    private static void initializeBlades() {
        // Ice and Fire
        BLADES.add("iceandfire:stymphalian_bird_dagger");
        
        // Dungeons and Combat
        BLADES.add("dungeons_and_combat:copper_dagger");
        BLADES.add("dungeons_and_combat:dragon_dagger_lightning");
        BLADES.add("dungeons_and_combat:titanium_sickle");
        BLADES.add("dungeons_and_combat:dragon_dagger_bone");
        BLADES.add("dungeons_and_combat:silver_dagger");
        BLADES.add("dungeons_and_combat:elden_sensu_dagger");
        BLADES.add("dungeons_and_combat:iron_dagger");
        BLADES.add("dungeons_and_combat:dragon_dagger_ice");
        BLADES.add("dungeons_and_combat:bronze_dagger");
        BLADES.add("dungeons_and_combat:slimy_knife");
        BLADES.add("dungeons_and_combat:dragon_dagger_fire");
        BLADES.add("dungeons_and_combat:stone_knife");
        BLADES.add("dungeons_and_combat:wooden_knife");
        BLADES.add("dungeons_and_combat:dagger");
        BLADES.add("dungeons_and_combat:ebony_dagger");
        BLADES.add("dungeons_and_combat:steel_dagger");
        BLADES.add("dungeons_and_combat:molten_dark_steel_dagger");
        BLADES.add("dungeons_and_combat:netherite_dagger");
        BLADES.add("dungeons_and_combat:cursed_steel_dagger");
        BLADES.add("dungeons_and_combat:ceremonial_dagger");
        
        // Cataclysm
        BLADES.add("cataclysm:athame");
        
        // Simply Swords
        BLADES.add("simplyswords:netherite_sai");
        BLADES.add("simplyswords:gold_sai");
        BLADES.add("simplyswords:diamond_sai");
        BLADES.add("simplyswords:iron_sai");
        BLADES.add("simplyswords:runic_sai");
        
        // Epic Fight
        BLADES.add("epicfight:netherite_dagger");
        BLADES.add("epicfight:golden_dagger");
        BLADES.add("epicfight:diamond_dagger");
        BLADES.add("epicfight:iron_dagger");
        
        // Call of Yucutan
        BLADES.add("call_of_yucutan:silex_tecpatl");
        BLADES.add("call_of_yucutan:obsidian_tecpatl");
        BLADES.add("call_of_yucutan:wooden_tecpatl");
        
        // Mowzie's Mobs
        BLADES.add("mowziesmobs:naga_fang_dagger");
        
        // Aquamirae
        BLADES.add("aquamirae_delight:seperator");
        BLADES.add("aquamirae_delight:remnants_knife");
        BLADES.add("aquamirae:dagger_of_greed");
        
        // Stalwart Dungeons
        BLADES.add("stalwart_dungeons:awful_dagger");
        
        // MineCells
        BLADES.add("minecells:assassins_dagger");
        
        // Born in Chaos
        BLADES.add("born_in_chaos_v1:intoxicating_dagger");
        BLADES.add("born_in_chaos_v1:dark_ritual_dagger");
        
        // CD Moveset
        BLADES.add("cdmoveset:s_netheritedagger");
        BLADES.add("cdmoveset:s_irondagger");
        BLADES.add("cdmoveset:s_diamonddagger");
        
        // Graveyard
        BLADES.add("graveyard:bone_dagger");
        
        // Samurai Dynasty
        BLADES.add("samurai_dynasty:sai");
        BLADES.add("samurai_dynasty:sai_netherite");
        
        // Legendary Monsters
        BLADES.add("legendary_monsters:sharp_sai");
    }
    
    /**
     * Determines the weapon type of an item
     */
    public static WeaponType getWeaponType(ItemStack stack) {
        if (stack.isEmpty()) {
            return WeaponType.NONE;
        }
        
        String itemId = getItemId(stack);
        
        if (HEAVY_WEAPONS.contains(itemId)) {
            return WeaponType.HEAVY_WEAPONS;
        } else if (BLADES.contains(itemId)) {
            return WeaponType.BLADES;
        } else if (KATANAS.contains(itemId)) {
            return WeaponType.KATANAS;
        } else if (MAGICAL_ITEMS.contains(itemId)) {
            return WeaponType.MAGICAL_ITEMS;
        } else if (THROWING.contains(itemId)) {
            return WeaponType.THROWING;
        }
        
        return WeaponType.NONE;
    }
    
    /**
     * Gets the registry ID of an item as a string (namespace:path)
     */
    private static String getItemId(ItemStack stack) {
        ResourceLocation location = ForgeRegistries.ITEMS.getKey(stack.getItem());
        return location != null ? location.toString() : "";
    }
}
