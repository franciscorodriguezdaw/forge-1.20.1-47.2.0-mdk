package net.kiko.tutorialmod.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class SapphireItem extends Item {
    public SapphireItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            // player.sendSystemMessage(Component.literal("Position: " + positionClicked.getX() + " "
            //        + positionClicked.getY() + " " + positionClicked.getZ()));

            double x = player.getX() + player.getLookAngle().x;
            double y = player.getY() + player.getLookAngle().y;
            double z = player.getZ() + player.getLookAngle().z;

            /*for (float i = 5.0F; i >= 1.0F; i -= 1.0F) {
                pContext.getLevel().explode((Entity) player, player.getX() + player.getLookAngle().x * (4*i), y, player.getZ() + player.getLookAngle().z * (4*i), i, Level.ExplosionInteraction.BLOCK);
            }*/

            //pContext.getLevel().explode((Entity) player, x, y, z, 5.0F, Level.ExplosionInteraction.BLOCK);

            //player.teleportTo(positionClicked.getX(), positionClicked.getY() + 1F, positionClicked.getZ());

            ServerLevel fake = (ServerLevel) pContext.getLevel();
            fake.sendParticles(ParticleTypes.ENCHANT, x, y, z, 37, 0, 0, 0, 1.5F);
            fake.sendParticles(ParticleTypes.SMALL_FLAME, x, y + 1.5, z + 0.5, 5, 1, 0, 0, 2F);
            /*for (int i = 0; i < 100; i++) {
                fake.sendParticles(ParticleTypes.BUBBLE, x, y + 1.5, z + 0.5, 1, 1, i, 0, 2.5F);
            }*/

            EntityType.LIGHTNING_BOLT.spawn(fake, (ItemStack) null, null, positionClicked, MobSpawnType.TRIGGERED, true, true);


            //pContext.getPlayer().getBlockReach()
            //pContext.getPlayer().getEntityReach()
        }
        return InteractionResult.SUCCESS;
    }
}
