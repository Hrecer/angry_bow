package com.angrybow.entity;

import com.angrybow.init.AngryBowEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;


public class SnowballProjectileEntity extends ThrowableProjectile implements ItemSupplier {
	private final boolean noGravity;
	private final double damage;
	private final int knockback;
	private final float velocity;
	private int ticksAlive = 0;
	private int maxLifetime = -1; // -1 表示无限制

	public SnowballProjectileEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(AngryBowEntities.SNOWBALL_BOW.get(), world);
		this.noGravity = false;
		this.damage = 0;
		this.knockback = 0;
		this.velocity = 1.5F;
	}

	public SnowballProjectileEntity(EntityType<? extends SnowballProjectileEntity> type, Level world) {
		super(type, world);
		this.noGravity = false;
		this.damage = 0;
		this.knockback = 0;
		this.velocity = 1.5F;
	}

	public SnowballProjectileEntity(EntityType<? extends SnowballProjectileEntity> type, LivingEntity entity, Level world, boolean noGravity, double damage, int knockback, float velocity) {
		super(type, entity, world);
		this.noGravity = noGravity;
		this.damage = damage;
		this.knockback = knockback;
		this.velocity = velocity;
	}

	public SnowballProjectileEntity(EntityType<? extends SnowballProjectileEntity> type, double x, double y, double z, Level world) {
		super(type, x, y, z, world);
		this.noGravity = false;
		this.damage = 0;
		this.knockback = 0;
		this.velocity = 1.5F;
	}

	@Override
	protected void defineSynchedData() {
	}

	@Override
	public boolean isNoGravity() {
		return this.noGravity;
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(Items.SNOWBALL);
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		Entity entity = result.getEntity();
		Entity owner = this.getOwner();
		if (entity instanceof LivingEntity livingEntity) {
			if (damage > 0) {
				entity.hurt(this.damageSources().thrown(this, owner), (float) damage);
			}
			if (knockback > 0) {
				Vec3 vec3 = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale(knockback * 0.6);
				if (vec3.lengthSqr() > 0.0) {
					livingEntity.push(vec3.x, 0.1, vec3.z);
				}
			}
		}
		for (int i = 0; i < 8; ++i) {
			this.level().addParticle(ParticleTypes.ITEM_SNOWBALL, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		for (int i = 0; i < 8; ++i) {
			this.level().addParticle(ParticleTypes.ITEM_SNOWBALL, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
		}
	}

	public void setMaxLifetime(int ticks) {
		this.maxLifetime = ticks;
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.level().isClientSide && this.maxLifetime > 0) {
			this.ticksAlive++;
			if (this.ticksAlive >= this.maxLifetime) {
				this.discard();
			}
		}
	}

	public static SnowballProjectileEntity shoot(EntityType<? extends SnowballProjectileEntity> type, Level world, LivingEntity entity, RandomSource random, boolean noGravity, double damage, int knockback, float velocity) {
		SnowballProjectileEntity projectile = new SnowballProjectileEntity(type, entity, world, noGravity, damage, knockback, velocity);
		
		float xRot = entity.getXRot();
		float yRot = entity.getYRot();
		
		float f = -Mth.sin(yRot * ((float) Math.PI / 180F)) * Mth.cos(xRot * ((float) Math.PI / 180F));
		float f1 = -Mth.sin(xRot * ((float) Math.PI / 180F));
		float f2 = Mth.cos(yRot * ((float) Math.PI / 180F)) * Mth.cos(xRot * ((float) Math.PI / 180F));
		
		projectile.shoot(f, f1, f2, velocity, 1.0F);
		
		world.addFreshEntity(projectile);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 0.8F));
		
		return projectile;
	}
}
