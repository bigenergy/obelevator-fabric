package com.bigenergy.obelevator.init;

import com.bigenergy.obelevator.OpenBlocksElevator;
import com.bigenergy.obelevator.block.ElevatorBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class ElevatorBlocks {
    public static Map<String, Block> elevatorBlocks = new LinkedHashMap<>();

    public static void register() {
        for (DyeColor dyeColor : DyeColor.values()) {
            String id = dyeColor.getName() + "_elevator";
            Block block = new ElevatorBlock(AbstractBlock.Settings.create()
                    .mapColor(dyeColor)
                    .requiresTool()
                    .strength(3f, 5f)
                    .sounds(BlockSoundGroup.WOOL));
            elevatorBlocks.put(id, block);
            Registry.register(Registries.BLOCK, Identifier.of(OpenBlocksElevator.MOD_ID, id), block);
        }
    }
}
