package com.bigenergy.obelevator.util;

import com.bigenergy.obelevator.OpenBlocksElevator;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class BlockStateUtils {
    public static boolean isBlockElevator(BlockState blockState) {
        return blockHasTag(blockState, Identifier.of(OpenBlocksElevator.MOD_ID, "elevators"));
    }

    public static boolean isBlockCarpet(BlockState blockState) {
        return blockHasTag(blockState, Identifier.of(OpenBlocksElevator.MOD_ID, "carpets"));
    }

    public static boolean isAirOrWallBlock(BlockState blockState) {
        return blockState.isAir() || blockHasTag(blockState, Identifier.of(OpenBlocksElevator.MOD_ID, "wall_blocks"));
    }

    public static boolean blockHasTag(BlockState blockState, Identifier tagId) {
        return blockState.streamTags().toList().contains(TagKey.of(RegistryKeys.BLOCK, tagId));
    }
}
