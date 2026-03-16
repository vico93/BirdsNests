package daniking.birdsnests;

import com.google.common.base.Suppliers;
// import me.shedaniel.autoconfig.AutoConfig;
// import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@NullMarked
public class BirdsNests implements ModInitializer {
    private static final Supplier<Map<ResourceKey<LootTable>, Block>> LOOT_TABLE_LOOKUP = Suppliers.memoize(
            () -> Util.make(
                    new HashMap<>(),
                    map -> BuiltInRegistries.BLOCK.forEach(
                            block -> block.getLootTable().ifPresent(
                                    key -> map.put(key, block)
                            )
                    )
            )
    );
    public static final String MODID = "birdsnests";
    public static final Logger LOGGER = LoggerFactory.getLogger(BirdsNests.class);
    public static Config config;
    public static final ResourceKey<Item> NEST_ITEM_KEY = ResourceKey.create(
            Registries.ITEM,
            Identifier.fromNamespaceAndPath(
                    MODID,
                    "nest"
            )
    );
    public static final Item NEST_ITEM;

    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.ITEM, NEST_ITEM_KEY, NEST_ITEM);
        registerLootTables();
        LOGGER.info("BirdsNests Initialized");
    }

    static void registerLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (source.isBuiltin()) {
                if (LOOT_TABLE_LOOKUP.get().get(key) instanceof LeavesBlock) {
                    tableBuilder.pool(buildLoot());
                }
            }
        });
    }

    static LootPool buildLoot() {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .when(LootItemRandomChanceCondition.randomChance(0.050f /* (float) config.nestDropChance */).build())
                .add(LootItem.lootTableItem(NEST_ITEM).build())
                .build();
    }

    static {
        // AutoConfig.register(Config.class, GsonConfigSerializer::new);
        // config = AutoConfig.getConfigHolder(Config.class).getConfig();
        NEST_ITEM = new NestItem(
                new Item.Properties()
                        .stacksTo(
                                64 // config.maxCount
                        )
                        .setId(NEST_ITEM_KEY)
        );
    }
}
