package com.angrybow.item;

import com.angrybow.entity.FireballProjectileEntity;
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
import net.minecraft.world.phys.Vec3;

public class FireballBowCopperItem extends Item {
    public FireballBowCopperItem() { super(new Item.Properties().durability(100)); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        entity.startUsingItem(hand);
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, entity.getItemInHand(hand));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) { return UseAnim.BOW; }

    @Override
    public int getUseDuration(ItemStack itemstack) { return 72000; }

    @Override
    public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entityLiving, int timeLeft) {
        if (!world.isClientSide && entityLiving instanceof ServerPlayer entity) {
            Vec3 look = entity.getLookAngle();
            FireballProjectileEntity fireball = new FireballProjectileEntity(
                AngryBowEntities.FIREBALL_BOW_COPPER.get(),
                entity,
                look.x, look.y, look.z,
                world,
                5.0D,
                10
            );
            fireball.setPos(entity.getX(), entity.getEyeY() - 0.1, entity.getZ());
            fireball.setDeltaMovement(look.scale(3.0F));
            world.addFreshEntity(fireball);
            
            itemstack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(entity.getUsedItemHand()));
        }
    }
}
