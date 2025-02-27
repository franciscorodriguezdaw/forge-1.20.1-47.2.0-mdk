package net.kiko.tutorialmod.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ResinJoint extends JointItem {

    public ResinJoint(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand){
        this.useJoint(pLevel, pPlayer, pUsedHand);
        Vec3 lookDirection = pPlayer.getLookAngle();
        pPlayer.setDeltaMovement(lookDirection.scale(2.0));
        pPlayer.hurtMarked = true;

        pLevel.playSound(null, pPlayer.blockPosition(), SoundEvents.VILLAGER_HURT,
                SoundSource.PLAYERS, 1.0F, 1.0F);
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void initEffects() {
        this.effects.add(new EffectWithAmplifier(MobEffects.SLOW_FALLING, 1));
    }
}
