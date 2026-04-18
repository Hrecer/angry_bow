package com.angrybow.init;

import com.angrybow.item.AngryIconItem;
import com.angrybow.item.FireballBowIronItem;
import com.angrybow.item.FireballBowItem;
import com.angrybow.item.FireballBowCopperItem;
import com.angrybow.item.FireballBowDiamondItem;
import com.angrybow.item.FireballBowNetherItem;
import com.angrybow.item.SnowballBowIronItem;
import com.angrybow.item.SnowballBowItem;
import com.angrybow.item.SnowballBowCopperItem;
import com.angrybow.item.SnowballBowDiamondItem;
import com.angrybow.item.SnowballBowNetherItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AngryBowItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, "angry_bow");

	// Icon item for creative tab
	public static final RegistryObject<Item> ICON_ITEM = REGISTRY.register("angry_icon", AngryIconItem::new);

	// 雪球弓
	public static final RegistryObject<Item> SNOWBALL_BOW = REGISTRY.register("snowball_bow", SnowballBowItem::new);
	public static final RegistryObject<Item> SNOWBALL_BOW_IRON = REGISTRY.register("snowball_bow_iron", SnowballBowIronItem::new);
	public static final RegistryObject<Item> SNOWBALL_BOW_COPPER = REGISTRY.register("snowball_bow_copper", SnowballBowCopperItem::new);
	public static final RegistryObject<Item> SNOWBALL_BOW_DIAMOND = REGISTRY.register("snowball_bow_diamond", SnowballBowDiamondItem::new);
	public static final RegistryObject<Item> SNOWBALL_BOW_NETHER = REGISTRY.register("snowball_bow_nether", SnowballBowNetherItem::new);

	// 火球弓
	public static final RegistryObject<Item> FIREBALL_BOW = REGISTRY.register("fireball_bow", FireballBowItem::new);
	public static final RegistryObject<Item> FIREBALL_BOW_IRON = REGISTRY.register("fireball_bow_iron", FireballBowIronItem::new);
	public static final RegistryObject<Item> FIREBALL_BOW_COPPER = REGISTRY.register("fireball_bow_copper", FireballBowCopperItem::new);
	public static final RegistryObject<Item> FIREBALL_BOW_DIAMOND = REGISTRY.register("fireball_bow_diamond", FireballBowDiamondItem::new);
	public static final RegistryObject<Item> FIREBALL_BOW_NETHER = REGISTRY.register("fireball_bow_nether", FireballBowNetherItem::new);
}
