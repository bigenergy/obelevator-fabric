package com.bigenergy.obelevator.init;

import com.bigenergy.obelevator.OpenBlocksElevator;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ElevatorItemGroups {
    public static final ItemGroup ELEVATORS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ElevatorItems.elevatorBlockItems.get("white_elevator")))
            .displayName(Text.translatableWithFallback("itemGroup.obelevator.elevators", "Elevators"))
            .entries((context, entries) -> {
                for (BlockItem blockItem : ElevatorItems.elevatorBlockItems.values()) {
                    entries.add(blockItem);
                }
            })
            .build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, Identifier.of(OpenBlocksElevator.MOD_ID, "elevators"), ELEVATORS);
    }
}
