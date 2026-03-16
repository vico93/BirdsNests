package daniking.birdsnests;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

public class BirdsNestsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(context -> context.insertAfter(
                Items.WARPED_FUNGUS_ON_A_STICK,
                BirdsNests.NEST_ITEM
        ));
    }
}
