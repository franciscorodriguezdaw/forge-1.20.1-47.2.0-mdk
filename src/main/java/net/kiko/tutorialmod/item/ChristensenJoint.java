package net.kiko.tutorialmod.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;

import java.util.List;

public class ChristensenJoint extends JointItem {
    public ChristensenJoint(Properties pProperties) {
        super(pProperties);
    }


    public void initEffects() {
        this.effects.add(new EffectWithAmplifier(MobEffects.MOVEMENT_SPEED, 2));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand){
        this.useJoint(pLevel, pPlayer, pUsedHand);
        this.extraPower(pPlayer, pLevel);
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public void extraPower(Player player, Level level) {

        Vec3 center = player.position().add(0, 0.1, 0);
        double size = 0.01;
        double thickness = 3.5;
        int particlesPerSegment = 30;

        if(!level.isClientSide()) {
            repulseMobs(level, player);
            spawnSwastika((ServerLevel) level, center, size, thickness, particlesPerSegment);
        }
    }

    public void repulseMobs(Level level, Player player) {
        int BBmultiplier = 5;
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(BBmultiplier));
        for (LivingEntity mob : entities) {
            if (!(mob instanceof Player)) {
                double bbSize = player.getBoundingBox().inflate(BBmultiplier).getSize();
                Vec3 lookDirection = new Vec3(mob.getX() - player.getX(), 0.5, mob.getZ() - player.getZ());
                Vec3 adjustedForceVector = new Vec3(bbSize/lookDirection.x, lookDirection.y, bbSize/lookDirection.z);
                mob.setDeltaMovement(adjustedForceVector.scale(0.2));
                mob.hurtMarked = true;
                // mob.setSecondsOnFire(5);
            }
        }
    }

    public void spawnSwastika(ServerLevel world, Vec3 center, double size, double thickness, int particlesPerSegment) {
        Vec3[] segments = {
                new Vec3(-thickness, 0, size), new Vec3(thickness, 0, size),
                new Vec3(-thickness, 0, size), new Vec3(-thickness, 0, size - thickness),

                new Vec3(size, 0, thickness), new Vec3(size, 0, -thickness),
                new Vec3(size, 0, thickness), new Vec3(size - thickness, 0, thickness),

                new Vec3(-thickness, 0, -size), new Vec3(thickness, 0, -size),
                new Vec3(thickness, 0, -size), new Vec3(thickness, 0, -size + thickness),

                new Vec3(-size, 0, thickness), new Vec3(-size, 0, -thickness),
                new Vec3(-size, 0, -thickness), new Vec3(-size + thickness, 0, -thickness)
        };

        for (int i = 0; i < segments.length; i += 2) {
            spawnParticlesBetween(world, center.add(segments[i]), center.add(segments[i + 1]), particlesPerSegment);
        }
    }

    private void spawnParticlesBetween(ServerLevel world, Vec3 start, Vec3 end, int count) {
        for (int i = 0; i <= count; i++) {
            double t = (double) i / count;
            Vec3 point = start.add(end.subtract(start).scale(t));
            world.sendParticles(ParticleTypes.SOUL, point.x, point.y, point.z, 1, 0, 0.5, 0, 0);
            world.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, point.x, point.y, point.z, 1, 0, 0, 0, 0);
            world.sendParticles(ParticleTypes.ENCHANT, point.x, point.y, point.z, 1, 0, 2, 0, 0);
        }
    }
}
