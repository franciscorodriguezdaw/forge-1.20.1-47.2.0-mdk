package net.kiko.tutorialmod.item;

import net.minecraft.world.effect.MobEffects;

public class JointAtrainItem extends JointItem {
    public JointAtrainItem(Properties pProperties) {
        super(pProperties);
        this.secondsEffects = 20;
    }

    @Override
    public void initEffects() {
        this.effects.add(new EffectWithAmplifier(MobEffects.MOVEMENT_SPEED, 12));
        this.effects.add(new EffectWithAmplifier(MobEffects.DIG_SPEED, 3));
        this.effects.add(new EffectWithAmplifier(MobEffects.DOLPHINS_GRACE, 10));
    }
}
