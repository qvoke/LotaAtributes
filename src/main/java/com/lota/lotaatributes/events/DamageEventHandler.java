package com.lota.lotaatributes.events;

import com.lota.lotaatributes.attributes.ModAttributes;
import com.lota.lotaatributes.data.WeaponCategories;
import com.lota.lotaatributes.data.WeaponType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DamageEventHandler {
    
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        // Check if the damage source is from a player
        if (!(event.getSource().getEntity() instanceof Player player)) {
            return;
        }
        
        // Get the weapon in the player's main hand
        var weapon = player.getMainHandItem();
        if (weapon.isEmpty()) {
            return;
        }
        
        // Determine weapon type
        WeaponType weaponType = WeaponCategories.getWeaponType(weapon);
        if (weaponType == WeaponType.NONE) {
            return;
        }
        
        // Get the appropriate attribute based on weapon type
        AttributeInstance attributeInstance = null;
        switch (weaponType) {
            case HEAVY_WEAPONS:
                attributeInstance = player.getAttribute(ModAttributes.HEAVY_WEAPONS_DAMAGE.get());
                break;
            case BLADES:
                attributeInstance = player.getAttribute(ModAttributes.BLADES_DAMAGE.get());
                break;
            case KATANAS:
                attributeInstance = player.getAttribute(ModAttributes.KATANAS_DAMAGE.get());
                break;
            case MAGICAL_ITEMS:
                attributeInstance = player.getAttribute(ModAttributes.MAGICAL_ITEMS_DAMAGE.get());
                break;
            case THROWING:
                attributeInstance = player.getAttribute(ModAttributes.THROWING_DAMAGE.get());
                break;
            default:
                return;
        }
        
        if (attributeInstance == null) {
            return;
        }
        
        // Get attribute value and multiply damage (Damage * (1 + Attribute))
        double attributeValue = attributeInstance.getValue();
        
        // Formula: Base * (1 + Attribute) * 0.8
        // -1 attribute -> Base * (1 - 1) * 0.8 = 0 damage
        // 6 attribute -> Base * (1 + 6) * 0.8 = 5.6x damage (e.g. 4 * 5.6 = 22.4 lethal)
        // 0.8 factor added to shift lethal threshold from 5 to 6 for standard weapons
        float newDamage = event.getAmount() * (1.0f + (float) attributeValue) * 0.8f;
        
        // Ensure damage doesn't go below 0
        if (newDamage < 0) {
            newDamage = 0;
        }
        
        event.setAmount(newDamage);
    }
}
