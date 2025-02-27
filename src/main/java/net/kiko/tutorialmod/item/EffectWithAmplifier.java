package net.kiko.tutorialmod.item;

import net.minecraft.world.effect.MobEffect;

public class EffectWithAmplifier {
    private MobEffect effect;
    private int amplifier;
    public EffectWithAmplifier(MobEffect effect, int amplifier) {
        this.effect = effect;
        this.amplifier = amplifier;
    }

    public MobEffect getEffect() {
        return effect;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public void setEffect(MobEffect effect) {
        this.effect = effect;
    }

    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }
}
