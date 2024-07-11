package com.bigenergy.obelevator.block;

import com.bigenergy.obelevator.config.ConfigManager;
import com.bigenergy.obelevator.util.BlockStateUtils;
import com.bigenergy.obelevator.util.PlayerUtils;
import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class ElevatorBlockFunctions {
    private static final Map<Direction, Integer> directionsYaw = new HashMap<>(Map.of(
            Direction.NORTH, 180,
            Direction.SOUTH, 0,
            Direction.WEST, 90,
            Direction.EAST, 270
    ));

    public static void goUp(ServerPlayerEntity player) {
        BlockPos playerBlockPos = player.getBlockPos();
        for (int y = playerBlockPos.getY(); y <= playerBlockPos.getY() + ConfigManager.config.range - 1; y++)
            if (checkPosForElevator(y, player.getWorld(), playerBlockPos, player)) return;

        if (!ConfigManager.config.elevatorNotFoundMsg) return;
        player.sendMessage(Text.translatableWithFallback("msg.obelevator.block.elevator.elevator_not_found_up",
                String.format("There isn't any elevator above you in range of %d blocks", ConfigManager.config.range),
                ConfigManager.config.range).formatted(Formatting.RED), true);
    }

    public static void goDown(ServerPlayerEntity player) {
        BlockPos playerBlockPos = player.getBlockPos().down(2);
        for (int y = playerBlockPos.getY(); y >= playerBlockPos.getY() - ConfigManager.config.range + 1; y--)
            if (checkPosForElevator(y, player.getWorld(), playerBlockPos, player)) return;

        if (!ConfigManager.config.elevatorNotFoundMsg) return;
        player.sendMessage(Text.translatableWithFallback("msg.obelevator.block.elevator.elevator_not_found_down",
                String.format("There isn't any elevator under you in range of %d blocks", ConfigManager.config.range),
                ConfigManager.config.range).formatted(Formatting.RED), true);
    }

    private static boolean checkPosForElevator(int y, World world, BlockPos playerBlockPos, ServerPlayerEntity player) {
        BlockPos nextElevPos = playerBlockPos.withY(y);
        BlockState nextElevState = world.getBlockState(nextElevPos);

        if (!BlockStateUtils.isBlockElevator(nextElevState)) return false;

        if (!PlayerUtils.canTpToElevator(world, nextElevPos)) {
            player.sendMessage(Text.translatableWithFallback("msg.obelevator.block.elevator.no_space_to_tp",
                    "There is no space above next elevator to teleport you").formatted(Formatting.RED), true);
        } else goToElevator(player, nextElevPos, nextElevState);
        return true;
    }

    private static void goToElevator(ServerPlayerEntity player, BlockPos elevPos, BlockState elevState) {
        double y = elevPos.getY() + 1;
        if (BlockStateUtils.isBlockCarpet(player.getWorld().getBlockState(elevPos.up())))
            y += 0.06250d; // Carpet height

        player.inTeleportationState = true;
        player.teleport(player.getServerWorld(), elevPos.getX() + 0.5d, y, elevPos.getZ() + 0.5d,
                directionsYaw.get(elevState.get(ElevatorBlock.DIRECTION_PROPERTY)),
                ConfigManager.config.centerPitchOnTp ? 0f : player.getPitch());

    }
}
