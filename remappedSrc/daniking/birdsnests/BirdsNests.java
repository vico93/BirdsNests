package daniking.birdsnests;

import com.google.common.base.Suppliers;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BirdsNests implements ModInitializer {
    private static final Supplier<Map<RegistryKey<LootTable>, Block>> LOOT_TABLE_LOOKUP = Suppliers.memoize(
            () -> Util.make(
                    new HashMap<>(),
                    map -> Registries.BLOCK.forEach(
                            block -> block.getLootTableKey().ifPresent(
                                    key -> map.put(key, block)
                            )
                    )
            )
    );
    public static final String MODID = "birdsnests";
    public static final Logger LOGGER = LoggerFactory.getLogger(BirdsNests.class);
    public static Config config;
    public static final RegistryKey<Item> NEST_ITEM_KEY = RegistryKey.of(
            RegistryKeys.ITEM,
            Identifier.of(
                    MODID,
                    "nest"
            )
    );
    public static final Item NEST_ITEM;

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, NEST_ITEM_KEY, NEST_ITEM);
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
        return LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder((float) config.nestDropChance).build())
                .with(ItemEntry.builder(NEST_ITEM).build())
                .build();
    }

    static {
        AutoConfig.register(Config.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(Config.class).getConfig();
        NEST_ITEM = new NestItem(
                new Item.Settings()
                        .maxCount(
                                config != null
                                ? config.maxCount
                                : 64
                        )
                        .registryKey(NEST_ITEM_KEY)
        );
    }
}
