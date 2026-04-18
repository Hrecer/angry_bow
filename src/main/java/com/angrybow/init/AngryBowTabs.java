package com.angrybow.init;

import com.angrybow.AngryBowMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AngryBowTabs {
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AngryBowMod.MODID);

	public static final RegistryObject<CreativeModeTab> ANGRY_BOW_TAB = TABS.register("angry_bow_tab",
			() -> CreativeModeTab.builder()
					.icon(() -> new ItemStack(AngryBowItems.ICON_ITEM.get()))
					.title(Component.translatable("itemGroup.angry_bow.tab"))
					.build());

	@SubscribeEvent
	public static void buildTabContents(BuildCreativeModeTabContentsEvent event) {
		if (event.getTab() == ANGRY_BOW_TAB.get()) {
			// 雪球弓
			event.accept(new ItemStack(AngryBowItems.SNOWBALL_BOW.get()));
			event.accept(new ItemStack(AngryBowItems.SNOWBALL_BOW_IRON.get()));
			event.accept(new ItemStack(AngryBowItems.SNOWBALL_BOW_COPPER.get()));
			event.accept(new ItemStack(AngryBowItems.SNOWBALL_BOW_DIAMOND.get()));
			event.accept(new ItemStack(AngryBowItems.SNOWBALL_BOW_NETHER.get()));
			// 火球弓
			event.accept(new ItemStack(AngryBowItems.FIREBALL_BOW.get()));
			event.accept(new ItemStack(AngryBowItems.FIREBALL_BOW_IRON.get()));
			event.accept(new ItemStack(AngryBowItems.FIREBALL_BOW_COPPER.get()));
			event.accept(new ItemStack(AngryBowItems.FIREBALL_BOW_DIAMOND.get()));
			event.accept(new ItemStack(AngryBowItems.FIREBALL_BOW_NETHER.get()));
		}
	}

	public static void register(IEventBus bus) {
		TABS.register(bus);
	}
}
