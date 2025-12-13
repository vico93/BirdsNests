package daniking.birdsnests;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.context.LootWorldContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import static net.minecraft.util.Util.make;

public class NestItem extends Item {
    public static final RegistryKey<LootTable> NEST_LOOT_TABLE_KEY = RegistryKey.of(
            RegistryKeys.LOOT_TABLE,
            Identifier.of(
                    BirdsNests.MODID,
                    "nest/nest_loot"
            )
    );

    public NestItem(net.minecraft.item.Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        final ItemStack stack = user.getStackInHand(hand);
        stack.decrementUnlessCreative(1, user);
        world.playSound(
                user,
                user.getBlockPos(),
                SoundEvents.BLOCK_GRASS_BREAK,
                SoundCategory.NEUTRAL,
                1.0F,
                1.0F
        );
        if (world instanceof ServerWorld serverWorld) {
            spawnLoot(serverWorld, user);
            return ActionResult.SUCCESS.withNewHandStack(stack);
        } else {
            return super.use(world, user, hand);
        }
    }

    private static void spawnLoot(ServerWorld world, PlayerEntity player) {
        final LootTable table = world.getServer()
                .getReloadableRegistries()
                .getLootTable(NEST_LOOT_TABLE_KEY);
        final Random random = player.getRandom();
        table.generateLoot(
                new LootWorldContext.Builder(world)
                        .build(LootContextTypes.EMPTY),
                stack -> world.spawnEntity(make(
                        new ItemEntity(
                                world,
                                player.getX(),
                                player.getY() + 1.5D,
                                player.getZ(),
                                stack
                        ),
                        entity -> entity.setVelocity(
                                random.nextGaussian() * 0.05F,
                                0.2D,
                                random.nextGaussian() * 0.05F
                        )
                ))
        );
    }
}
