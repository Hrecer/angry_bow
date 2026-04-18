package com.angrybow.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class FireballProjectileEntity extends SmallFireball {
    private double damage;
    private int knockback;

    public FireballProjectileEntity(EntityType<? extends FireballProjectileEntity> type, Level world) {
        super(type, world);
        this.damage = 5.0D;
        this.knockback = 0;
    }

    public FireballProjectileEntity(EntityType<? extends FireballProjectileEntity> type, LivingEntity shooter, double offsetX, double offsetY, double offsetZ, Level world, double damage, int knockback) {
        super(world, shooter, offsetX, offsetY, offsetZ);
        this.damage = damage;
        this.knockback = knockback;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide) {
            if (result.getEntity() instanceof LivingEntity livingEntity) {
                if (damage > 0) {
                    livingEntity.hurt(this.damageSources().fireball(this, this.getOwner()), (float) damage);
                }
                if (knockback > 0) {
                    livingEntity.knockback(knockback * 0.5F, this.getX() - livingEntity.getX(), this.getZ() - livingEntity.getZ());
                }
                // 点燃实体
                livingEntity.setSecondsOnFire(5);
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level().isClientSide) {
            // 在落点放置火
            BlockPos blockPos = result.getBlockPos().relative(result.getDirection());
            if (this.level().isEmptyBlock(blockPos)) {
                this.level().setBlockAndUpdate(blockPos, BaseFireBlock.getState(this.level(), blockPos));
            }
        }
    }
}
