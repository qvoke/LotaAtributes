package com.lota.lotaatributes.events;

import com.lota.lotaatributes.attributes.ModAttributes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Event handlers for custom attributes.
 */
public class AttributeEventHandler {

    /**
     * Applies the Stealth bonus when a mob tries to target a player.
     * Reduces the effective follow range based on stealth value.
     */
    @SubscribeEvent
    public void applyStealthBonus(LivingChangeTargetEvent event) {
        if (!(event.getNewTarget() instanceof Player player)) return;
        
        double stealth = player.getAttributeValue(ModAttributes.STEALTH.get()) / 100d;
        if (stealth == 0) return;
        
        LivingEntity attacker = event.getEntity();
        double followRange = attacker.getAttributeValue(Attributes.FOLLOW_RANGE);
        
        // Cancel targeting if player is outside reduced aggro range
        if (attacker.distanceTo(player) > followRange * (1 - stealth)) {
            event.setCanceled(true);
        }
    }

    /**
     * Applies the Evasion bonus when a player is attacked.
     * Uses diminishing returns formula to calculate dodge chance.
     */
    @SubscribeEvent
    public void applyEvasionBonus(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!(event.getSource().getEntity() instanceof LivingEntity attacker)) return;
        
        double evasion = player.getAttributeValue(ModAttributes.EVASION.get());
        if (evasion == 0) return;
        
        // Diminishing returns formula
        double evasionChance = (evasion * 0.05) / (1 + evasion * 0.05) * 0.8;
        
        if (!(player.getRandom().nextFloat() < evasionChance)) return;
        
        // Play dodge sound effect
        player.level()
              .playSound(null, player, SoundEvents.ENDER_DRAGON_FLAP, SoundSource.PLAYERS, 0.5F, 1.5F);
        
        // Cancel the attack
        event.setCanceled(true);
    }
}
