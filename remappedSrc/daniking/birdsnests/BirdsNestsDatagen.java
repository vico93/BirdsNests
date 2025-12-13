package daniking.birdsnests;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class BirdsNestsDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModelProvider::new);
        pack.addProvider(LootTableProvider::new);
    }

    static class ModelProvider extends FabricModelProvider {
        public ModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            itemModelGenerator.register(BirdsNests.NEST_ITEM, Models.GENERATED);
        }
    }

    static class LootTableProvider extends SimpleFabricLootTableProvider {
        public LootTableProvider(
                FabricDataOutput output,
                CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup
        ) {
            super(output, registryLookup, LootContextTypes.EMPTY);
        }

        @Override
        public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> registrar) {
            registrar.accept(
                    NestItem.NEST_LOOT_TABLE_KEY,
                    LootTable.builder().pool(
                            LootPool.builder()
                                    .with(
                                            ItemEntry.builder(Items.STICK).apply(
                                                    SetCountLootFunction.builder(
                                                            UniformLootNumberProvider.create(1, 4)
                                                    )
                                            )
                                    ).with(
                                            ItemEntry.builder(Items.FEATHER)
                                                    .apply(
                                                            SetCountLootFunction.builder(
                                                                    UniformLootNumberProvider.create(0, 3)
                                                            )
                                                    )
                                    ).with(
                                            ItemEntry.builder(Items.EGG).conditionally(
                                                    RandomChanceLootCondition.builder(0.33F)
                                            )
                                    ).with(
                                            ItemEntry.builder(Items.STRING).apply(
                                                    SetCountLootFunction.builder(
                                                            UniformLootNumberProvider.create(1, 2)
                                                    )
                                            ).conditionally(
                                                    RandomChanceLootCondition.builder(0.50F)
                                            )
                                    ).with(
                                            ItemEntry.builder(Items.BONE).apply(
                                                    SetCountLootFunction.builder(
                                                            UniformLootNumberProvider.create(1, 2)
                                                    )
                                            ).conditionally(
                                                    RandomChanceLootCondition.builder(0.37F)
                                            )
                                    ).with(
                                            ItemEntry.builder(Items.FLINT).conditionally(
                                                    RandomChanceLootCondition.builder(0.25F)
                                            )
                                    ).with(
                                            ItemEntry.builder(Items.WHEAT_SEEDS).apply(
                                                    SetCountLootFunction.builder(
                                                            UniformLootNumberProvider.create(1, 2)
                                                    )
                                            ).conditionally(
                                                    RandomChanceLootCondition.builder(0.125F)
                                            )
                                    ).with(
                                            ItemEntry.builder(Items.MELON_SEEDS)
                                                    .conditionally(
                                                            RandomChanceLootCondition.builder(0.04F)
                                                    )
                                    ).with(
                                            ItemEntry.builder(Items.PUMPKIN_SEEDS)
                                                    .conditionally(
                                                            RandomChanceLootCondition.builder(0.04F)
                                                    )
                                    ).with(
                                            ItemEntry.builder(Items.DIAMOND)
                                                    .conditionally(
                                                            RandomChanceLootCondition.builder(0.04F)
                                                    )
                                    ).with(
                                            ItemEntry.builder(Items.EMERALD)
                                                    .conditionally(
                                                            RandomChanceLootCondition.builder(0.03333F)
                                                    )
                                    ).with(
                                            ItemEntry.builder(Items.SALMON).conditionally(
                                                    RandomChanceLootCondition.builder(0.03333F)
                                            )
                                    ).with(
                                            ItemEntry.builder(Items.TROPICAL_FISH).conditionally(
                                                    RandomChanceLootCondition.builder(0.03333F)
                                            )
                                    ).with(
                                            ItemEntry.builder(Items.COD).conditionally(
                                                    RandomChanceLootCondition.builder(0.03333F)
                                            )
                                    ).with(
                                            ItemEntry.builder(Items.PRISMARINE_SHARD).conditionally(
                                                    RandomChanceLootCondition.builder(0.016667F)
                                            )
                                    )
                                    .with(
                                            ItemEntry.builder(Items.BEETROOT_SEEDS).conditionally(
                                                    RandomChanceLootCondition.builder(0.04F)
                                            )
                                    )
                                    .with(
                                            ItemEntry.builder(Items.REDSTONE).apply(
                                                    SetCountLootFunction.builder(
                                                            UniformLootNumberProvider.create(1, 2)
                                                    )
                                            ).conditionally(
                                                    RandomChanceLootCondition.builder(0.0625F)
                                            )
                                    ).with(
                                            ItemEntry.builder(Items.GLOWSTONE_DUST).apply(
                                                    SetCountLootFunction.builder(
                                                            UniformLootNumberProvider.create(1, 1)
                                                    )
                                            ).conditionally(
                                                    RandomChanceLootCondition.builder(0.111F)
                                            )
                                    ).with(
                                            ItemEntry.builder(Items.BLAZE_POWDER).apply(
                                                    SetCountLootFunction.builder(
                                                            UniformLootNumberProvider.create(1, 1)
                                                    )
                                            ).conditionally(
                                                    RandomChanceLootCondition.builder(0.025F)
                                            )
                                    ).with(
                                            ItemEntry.builder(Items.GOLD_NUGGET).apply(
                                                    SetCountLootFunction.builder(
                                                            UniformLootNumberProvider.create(1, 3)
                                                    )
                                            ).conditionally(
                                                    RandomChanceLootCondition.builder(0.125F)
                                            )
                                    ).with(
                                            ItemEntry.builder(Items.IRON_NUGGET).apply(
                                                    SetCountLootFunction.builder(
                                                            UniformLootNumberProvider.create(1, 4)
                                                    )
                                            ).conditionally(
                                                    RandomChanceLootCondition.builder(0.25F)
                                            )
                                    )
                    ));
        }
    }

}
