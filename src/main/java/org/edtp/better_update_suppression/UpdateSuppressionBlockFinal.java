package org.edtp.better_update_suppression;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.function.Function;

public class UpdateSuppressionBlockFinal {
    public static final Block UPDATE_SUPPRESSION_BLOCK = register("update_suppression_block", UpdateSuppressionBlock::new, Block.Settings.create().strength(0.1f));

    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier identifier = Identifier.of("better_update_suppression", path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        final Block block = Blocks.register(registryKey, factory, settings);
        Items.register(block);
        return block;
    }

    public static void init() {
        PolymerItem.registerOverlay(UPDATE_SUPPRESSION_BLOCK.asItem(), new PolymerItem() {
            @Override
            public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
                return Items.OBSERVER;
            }

            @Override
            @Nullable
            public Identifier getPolymerItemModel(ItemStack stack, PacketContext context) {
                return Identifier.of("better_update_suppression", "update_suppression_block");
            }
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {
            entries.add(UPDATE_SUPPRESSION_BLOCK);
        });
    }
}
