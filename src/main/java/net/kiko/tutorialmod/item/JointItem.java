package net.kiko.tutorialmod.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JointItem extends Item {

    private final Random random = new Random();
    protected int secondsEffects = 10;
    private final int timeEffects = this.secondsEffects *  20;
    protected List<EffectWithAmplifier> effects = new ArrayList<>();

    public JointItem(Properties pProperties) {
        super(pProperties);
        this.initEffects();
    }
    public void initEffects() {
        this.effects.add(new EffectWithAmplifier(MobEffects.SLOW_FALLING, 1));
        this.effects.add(new EffectWithAmplifier(MobEffects.MOVEMENT_SPEED, 1));
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        this.useJoint(pLevel, pPlayer, pUsedHand);
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public void useJoint(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        BlockPos pPos = new BlockPos((int) pPlayer.getX(), (int) pPlayer.getY(), (int) pPlayer.getZ());
        if(!pLevel.isClientSide()) {
            this.spawnParticles(pLevel, pPlayer, pPos);
            this.getStonedPowers(pPlayer);
            UseOnContext pContext = new UseOnContext(pPlayer, pUsedHand, null);
            pContext.getItemInHand().hurtAndBreak(1, pPlayer,
                    plyr -> plyr.broadcastBreakEvent(plyr.getUsedItemHand()));
        } else {
            pLevel.playSound(pPlayer, pPos, SoundEvents.BLAZE_BURN, SoundSource.AMBIENT, 1.0F, 1.0F);
        }
    }

    public void getStonedPowers(Player player) {
        if (player != null) {
            boolean hasPlayerEffect = player.hasEffect(MobEffects.MOVEMENT_SPEED);

            if(this.random.nextDouble() < 0.001) {
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, this.timeEffects, 1, true, false));
            }

            if(!hasPlayerEffect) {
                for (EffectWithAmplifier effect : this.effects) {
                    player.addEffect(new MobEffectInstance(effect.getEffect(), this.timeEffects, effect.getAmplifier(), true, false));
                }
            }
        }
    }

    public void spawnParticles(Level level, Player player, BlockPos pPos) {
        ServerLevel fake = (ServerLevel) level;

        Vec3 lookDirection = player.getViewVector(1.0F);

        double px = player.getX();
        double py = player.getY() + player.getEyeHeight(); // Ajuste para que esté al nivel de los ojos
        double pz = player.getZ();

        double x = px + lookDirection.x * 0.5;
        double y = py + lookDirection.y * 0.5;
        double z = pz + lookDirection.z * 0.5;

        fake.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 2, 0, 0, 0, 0.02F);
        fake.sendParticles(ParticleTypes.SMALL_FLAME, x, y, z, 2, 0, 0, 0, 0.02F);
        fake.sendParticles(ParticleTypes.SMOKE, x, y, z, 2, 0, 0, 0, 0.02F);

    }
}
