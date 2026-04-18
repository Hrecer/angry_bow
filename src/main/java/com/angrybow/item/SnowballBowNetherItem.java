package com.angrybow.item;

import com.angrybow.entity.SnowballProjectileEntity;
import com.angrybow.init.AngryBowEntities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class SnowballBowNetherItem extends Item {
	public SnowballBowNetherItem() { super(new Item.Properties().durability(2031)); }
	@Override public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) { entity.startUsingItem(hand); return new InteractionResultHolder<>(InteractionResult.SUCCESS, entity.getItemInHand(hand)); }
	@Override public UseAnim getUseAnimation(ItemStack itemstack) { return UseAnim.BOW; }
	@Override public int getUseDuration(ItemStack itemstack) { return 72000; }
	@Override public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entityLiving, int timeLeft) { fire(world, entityLiving, itemstack, 15.0D, 7, 1.5F); }

	private void fire(Level world, LivingEntity entityLiving, ItemStack itemstack, double damage, int knockback, float velocity) {
		if (!world.isClientSide && entityLiving instanceof ServerPlayer entity) {
			ItemStack stack = ProjectileWeaponItem.getHeldProjectile(entity, e -> e.is(Items.SNOWBALL));
			if (stack == ItemStack.EMPTY) { for (int i = 0; i < entity.getInventory().items.size(); i++) { ItemStack t = entity.getInventory().items.get(i); if (t != null && t.is(Items.SNOWBALL)) { stack = t; break; } } }
			if (entity.getAbilities().instabuild || stack != ItemStack.EMPTY) {
				SnowballProjectileEntity projectile = SnowballProjectileEntity.shoot(AngryBowEntities.SNOWBALL_BOW_NETHER.get(), world, entity, world.random, false, damage, knockback, velocity);
				itemstack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(entity.getUsedItemHand()));
				if (!entity.getAbilities().instabuild) { stack.shrink(1); if (stack.isEmpty()) entity.getInventory().removeItem(stack); }
			}
		}
	}
}
