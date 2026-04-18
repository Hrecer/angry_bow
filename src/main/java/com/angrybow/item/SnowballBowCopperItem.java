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
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class SnowballBowCopperItem extends Item {
	public SnowballBowCopperItem() { super(new Item.Properties().durability(100)); }
	@Override public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) { entity.startUsingItem(hand); return new InteractionResultHolder<>(InteractionResult.SUCCESS, entity.getItemInHand(hand)); }
	@Override public UseAnim getUseAnimation(ItemStack itemstack) { return UseAnim.BOW; }
	@Override public int getUseDuration(ItemStack itemstack) { return 72000; }
	@Override public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entityLiving, int timeLeft) {
		if (!world.isClientSide && entityLiving instanceof ServerPlayer entity) {
			SnowballProjectileEntity.shoot(AngryBowEntities.SNOWBALL_BOW_COPPER.get(), world, entity, world.random, false, 5.0D, 10, 3.0F);
			itemstack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(entity.getUsedItemHand()));
		}
	}
}
