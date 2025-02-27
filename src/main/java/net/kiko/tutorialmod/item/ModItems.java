package net.kiko.tutorialmod.item;

import net.kiko.tutorialmod.TutorialMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> JOINT = ITEMS.register("joint",
            () -> new JointItem(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> ISOLATOR = ITEMS.register("isolator",
            () -> new IsolatorItem(new Item.Properties().durability(200)));

    public static final RegistryObject<Item> JOINT_ATRAIN = ITEMS.register("joint_atrain",
            () -> new JointAtrainItem(new Item.Properties().durability(200)));

    public static final RegistryObject<Item> CHRISTENSEN_JOINT = ITEMS.register("christensen_joint",
            () -> new ChristensenJoint(new Item.Properties().durability(200)));

    public static final RegistryObject<Item> RESIN_JOINT = ITEMS.register("resin_joint",
            () -> new ResinJoint(new Item.Properties().durability(200)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
