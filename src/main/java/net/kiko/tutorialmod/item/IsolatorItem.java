package net.kiko.tutorialmod.item;

import net.minecraft.world.effect.MobEffects;

public class IsolatorItem extends JointItem {
    public IsolatorItem(Properties pProperties) {
        super(pProperties);
        this.secondsEffects = 10;
    }

    @Override
    public void initEffects() {
        this.effects.add(new EffectWithAmplifier(MobEffects.JUMP, 1));
        this.effects.add(new EffectWithAmplifier(MobEffects.MOVEMENT_SPEED, 1));
    }
}
