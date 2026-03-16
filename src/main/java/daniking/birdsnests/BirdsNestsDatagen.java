package daniking.birdsnests;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.HolderLookup;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jspecify.annotations.NullMarked;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@NullMarked
public class BirdsNestsDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModelProvider::new);
        pack.addProvider(LootTableProvider::new);
    }

    static class ModelProvider extends FabricModelProvider {
        public ModelProvider(FabricPackOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

        }

        @Override
        public void generateItemModels(ItemModelGenerators itemModelGenerator) {
            itemModelGenerator.createFlatItemModel(BirdsNests.NEST_ITEM, ModelTemplates.FLAT_ITEM);
        }
    }

    static class LootTableProvider extends SimpleFabricLootTableSubProvider {
        public LootTableProvider(
                FabricPackOutput output,
                CompletableFuture<HolderLookup.Provider> registryLookup
        ) {
            super(output, registryLookup, LootContextParamSets.EMPTY);
        }

        @Override
        public void generate(BiConsumer<ResourceKey<net.minecraft.world.level.storage.loot.LootTable>, net.minecraft.world.level.storage.loot.LootTable.Builder> registrar) {
            registrar.accept(
                    NestItem.NEST_LOOT_TABLE_KEY,
                    LootTable.lootTable().pool(
                            LootPool.lootPool()
                                    .add(
                                            LootItem.lootTableItem(Items.STICK).apply(
                                                    SetItemCountFunction.setCount(
                                                            UniformGenerator.between(1, 4)
                                                    )
                                            )
                                    ).add(
                                            LootItem.lootTableItem(Items.FEATHER)
                                                    .apply(
                                                            SetItemCountFunction.setCount(
                                                                    UniformGenerator.between(0, 3)
                                                            )
                                                    )
                                    ).add(
                                            LootItem.lootTableItem(Items.EGG).when(
                                                    LootItemRandomChanceCondition.randomChance(0.33F)
                                            )
                                    ).add(
                                            LootItem.lootTableItem(Items.STRING).apply(
                                                    SetItemCountFunction.setCount(
                                                            UniformGenerator.between(1, 2)
                                                    )
                                            ).when(
                                                    LootItemRandomChanceCondition.randomChance(0.50F)
                                            )
                                    ).add(
                                            LootItem.lootTableItem(Items.BONE).apply(
                                                    SetItemCountFunction.setCount(
                                                            UniformGenerator.between(1, 2)
                                                    )
                                            ).when(
                                                    LootItemRandomChanceCondition.randomChance(0.37F)
                                            )
                                    ).add(
                                            LootItem.lootTableItem(Items.FLINT).when(
                                                    LootItemRandomChanceCondition.randomChance(0.25F)
                                            )
                                    ).add(
                                            LootItem.lootTableItem(Items.WHEAT_SEEDS).apply(
                                                    SetItemCountFunction.setCount(
                                                            UniformGenerator.between(1, 2)
                                                    )
                                            ).when(
                                                    LootItemRandomChanceCondition.randomChance(0.125F)
                                            )
                                    ).add(
                                            LootItem.lootTableItem(Items.MELON_SEEDS)
                                                    .when(
                                                            LootItemRandomChanceCondition.randomChance(0.04F)
                                                    )
                                    ).add(
                                            LootItem.lootTableItem(Items.PUMPKIN_SEEDS)
                                                    .when(
                                                            LootItemRandomChanceCondition.randomChance(0.04F)
                                                    )
                                    ).add(
                                            LootItem.lootTableItem(Items.DIAMOND)
                                                    .when(
                                                            LootItemRandomChanceCondition.randomChance(0.04F)
                                                    )
                                    ).add(
                                            LootItem.lootTableItem(Items.EMERALD)
                                                    .when(
                                                            LootItemRandomChanceCondition.randomChance(0.03333F)
                                                    )
                                    ).add(
                                            LootItem.lootTableItem(Items.SALMON).when(
                                                    LootItemRandomChanceCondition.randomChance(0.03333F)
                                            )
                                    ).add(
                                            LootItem.lootTableItem(Items.TROPICAL_FISH).when(
                                                    LootItemRandomChanceCondition.randomChance(0.03333F)
                                            )
                                    ).add(
                                            LootItem.lootTableItem(Items.COD).when(
                                                    LootItemRandomChanceCondition.randomChance(0.03333F)
                                            )
                                    ).add(
                                            LootItem.lootTableItem(Items.PRISMARINE_SHARD).when(
                                                    LootItemRandomChanceCondition.randomChance(0.016667F)
                                            )
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.BEETROOT_SEEDS).when(
                                                    LootItemRandomChanceCondition.randomChance(0.04F)
                                            )
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.REDSTONE).apply(
                                                    SetItemCountFunction.setCount(
                                                            UniformGenerator.between(1, 2)
                                                    )
                                            ).when(
                                                    LootItemRandomChanceCondition.randomChance(0.0625F)
                                            )
                                    ).add(
                                            LootItem.lootTableItem(Items.GLOWSTONE_DUST).apply(
                                                    SetItemCountFunction.setCount(
                                                            UniformGenerator.between(1, 1)
                                                    )
                                            ).when(
                                                    LootItemRandomChanceCondition.randomChance(0.111F)
                                            )
                                    ).add(
                                            LootItem.lootTableItem(Items.BLAZE_POWDER).apply(
                                                    SetItemCountFunction.setCount(
                                                            UniformGenerator.between(1, 1)
                                                    )
                                            ).when(
                                                    LootItemRandomChanceCondition.randomChance(0.025F)
                                            )
                                    ).add(
                                            LootItem.lootTableItem(Items.GOLD_NUGGET).apply(
                                                    SetItemCountFunction.setCount(
                                                            UniformGenerator.between(1, 3)
                                                    )
                                            ).when(
                                                    LootItemRandomChanceCondition.randomChance(0.125F)
                                            )
                                    ).add(
                                            LootItem.lootTableItem(Items.IRON_NUGGET).apply(
                                                    SetItemCountFunction.setCount(
                                                            UniformGenerator.between(1, 4)
                                                    )
                                            ).when(
                                                    LootItemRandomChanceCondition.randomChance(0.25F)
                                            )
                                    )
                                    .build()
                    ));
        }
    }

}
