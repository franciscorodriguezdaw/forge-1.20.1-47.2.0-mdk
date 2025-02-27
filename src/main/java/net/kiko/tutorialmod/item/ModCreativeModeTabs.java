package net.kiko.tutorialmod.item;

import net.kiko.tutorialmod.TutorialMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("tutorial_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.JOINT.get()))
                    .title(Component.translatable("creative.tabs.joints"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.JOINT.get());
                        output.accept(ModItems.JOINT_ATRAIN.get());
                        output.accept(ModItems.ISOLATOR.get());
                        output.accept(ModItems.CHRISTENSEN_JOINT.get());
                        output.accept(ModItems.RESIN_JOINT.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
