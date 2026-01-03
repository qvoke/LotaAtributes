package com.lota.lotaatributes;

import com.lota.lotaatributes.attributes.ModAttributes;
import com.lota.lotaatributes.events.AttributeEventHandler;
import com.lota.lotaatributes.events.DamageEventHandler;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(LotaAttributesMod.MOD_ID)
public class LotaAttributesMod {
    public static final String MOD_ID = "lotaatributes";
    private static final Logger LOGGER = LogUtils.getLogger();

    public LotaAttributesMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        // Register attributes
        ModAttributes.ATTRIBUTES.register(modEventBus);
        
        // Register event handlers
        MinecraftForge.EVENT_BUS.register(new DamageEventHandler());
        MinecraftForge.EVENT_BUS.register(new AttributeEventHandler());
        
        LOGGER.info("LOTA Attributes mod initialized");
    }
}
