package com.angrybow.init;

import com.angrybow.entity.FireballProjectileEntity;
import com.angrybow.entity.SnowballProjectileEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AngryBowEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "angry_bow");

	// 雪球弓实体
	public static final RegistryObject<EntityType<SnowballProjectileEntity>> SNOWBALL_BOW = registerSnowball("projectile_snowball_bow");
	public static final RegistryObject<EntityType<SnowballProjectileEntity>> SNOWBALL_BOW_IRON = registerSnowball("projectile_snowball_bow_iron");
	public static final RegistryObject<EntityType<SnowballProjectileEntity>> SNOWBALL_BOW_COPPER = registerSnowball("projectile_snowball_bow_copper");
	public static final RegistryObject<EntityType<SnowballProjectileEntity>> SNOWBALL_BOW_DIAMOND = registerSnowball("projectile_snowball_bow_diamond");
	public static final RegistryObject<EntityType<SnowballProjectileEntity>> SNOWBALL_BOW_NETHER = registerSnowball("projectile_snowball_bow_nether");

	// 火球弓实体
	public static final RegistryObject<EntityType<FireballProjectileEntity>> FIREBALL_BOW = registerFireball("projectile_fireball_bow");
	public static final RegistryObject<EntityType<FireballProjectileEntity>> FIREBALL_BOW_IRON = registerFireball("projectile_fireball_bow_iron");
	public static final RegistryObject<EntityType<FireballProjectileEntity>> FIREBALL_BOW_COPPER = registerFireball("projectile_fireball_bow_copper");
	public static final RegistryObject<EntityType<FireballProjectileEntity>> FIREBALL_BOW_DIAMOND = registerFireball("projectile_fireball_bow_diamond");
	public static final RegistryObject<EntityType<FireballProjectileEntity>> FIREBALL_BOW_NETHER = registerFireball("projectile_fireball_bow_nether");

	private static RegistryObject<EntityType<SnowballProjectileEntity>> registerSnowball(String name) {
		return REGISTRY.register(name, () -> EntityType.Builder.<SnowballProjectileEntity>of(SnowballProjectileEntity::new, MobCategory.MISC)
				.setCustomClientFactory(SnowballProjectileEntity::new)
				.setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64)
				.setUpdateInterval(1)
				.sized(0.25F, 0.25F)
				.build(name));
	}

	private static RegistryObject<EntityType<FireballProjectileEntity>> registerFireball(String name) {
		return REGISTRY.register(name, () -> EntityType.Builder.<FireballProjectileEntity>of(FireballProjectileEntity::new, MobCategory.MISC)
				.setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64)
				.setUpdateInterval(1)
				.sized(0.3125F, 0.3125F)
				.build(name));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {});
	}
}
