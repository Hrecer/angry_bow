package com.angrybow.init;

import com.angrybow.entity.FireballProjectileEntity;
import com.angrybow.entity.SnowballProjectileEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "angry_bow", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AngryBowClientEvents {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // 雪球弓实体渲染器
        event.registerEntityRenderer(AngryBowEntities.SNOWBALL_BOW.get(), 
            (EntityRendererProvider.Context context) -> new ThrownItemRenderer<>(context));
        event.registerEntityRenderer(AngryBowEntities.SNOWBALL_BOW_IRON.get(), 
            (EntityRendererProvider.Context context) -> new ThrownItemRenderer<>(context));
        event.registerEntityRenderer(AngryBowEntities.SNOWBALL_BOW_COPPER.get(), 
            (EntityRendererProvider.Context context) -> new ThrownItemRenderer<>(context));
        event.registerEntityRenderer(AngryBowEntities.SNOWBALL_BOW_DIAMOND.get(), 
            (EntityRendererProvider.Context context) -> new ThrownItemRenderer<>(context));
        event.registerEntityRenderer(AngryBowEntities.SNOWBALL_BOW_NETHER.get(), 
            (EntityRendererProvider.Context context) -> new ThrownItemRenderer<>(context));
        
        // 火球弓实体渲染器 - 使用与雪球相同的渲染器
        event.registerEntityRenderer(AngryBowEntities.FIREBALL_BOW.get(), 
            (EntityRendererProvider.Context context) -> new ThrownItemRenderer<>(context));
        event.registerEntityRenderer(AngryBowEntities.FIREBALL_BOW_IRON.get(), 
            (EntityRendererProvider.Context context) -> new ThrownItemRenderer<>(context));
        event.registerEntityRenderer(AngryBowEntities.FIREBALL_BOW_COPPER.get(), 
            (EntityRendererProvider.Context context) -> new ThrownItemRenderer<>(context));
        event.registerEntityRenderer(AngryBowEntities.FIREBALL_BOW_DIAMOND.get(), 
            (EntityRendererProvider.Context context) -> new ThrownItemRenderer<>(context));
        event.registerEntityRenderer(AngryBowEntities.FIREBALL_BOW_NETHER.get(), 
            (EntityRendererProvider.Context context) -> new ThrownItemRenderer<>(context));
    }
}
