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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FireballBowIronItem extends Item {
    public FireballBowIronItem() { super(new Item.Properties().durability(579)); }

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
        fire(world, entityLiving, itemstack, 10.5D, 5, 1.25F);
    }

    private void fire(Level world, LivingEntity entityLiving, ItemStack itemstack, double damage, int knockback, float velocity) {
        if (!world.isClientSide && entityLiving instanceof ServerPlayer entity) {
            ItemStack fireChargeStack = findFireCharge(entity);
            if (entity.getAbilities().instabuild || fireChargeStack != null) {
                Vec3 look = entity.getLookAngle();
                FireballProjectileEntity fireball = new FireballProjectileEntity(
                    AngryBowEntities.FIREBALL_BOW_IRON.get(),
                    entity,
                    look.x, look.y, look.z,
                    world,
                    damage,
                    knockback
                );
                fireball.setPos(entity.getX(), entity.getEyeY() - 0.1, entity.getZ());
                fireball.setDeltaMovement(look.scale(velocity));
                world.addFreshEntity(fireball);
                
                itemstack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(entity.getUsedItemHand()));
                if (!entity.getAbilities().instabuild && fireChargeStack != null) {
                    fireChargeStack.shrink(1);
                }
            }
        }
    }

    private ItemStack findFireCharge(ServerPlayer player) {
        for (int i = 0; i < player.getInventory().items.size(); i++) {
            ItemStack stack = player.getInventory().items.get(i);
            if (stack.is(Items.FIRE_CHARGE)) {
                return stack;
            }
        }
        return null;
    }
}
