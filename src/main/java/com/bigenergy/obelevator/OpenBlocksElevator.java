package com.bigenergy.obelevator;

import com.bigenergy.obelevator.init.ElevatorBlocks;
import com.bigenergy.obelevator.config.ConfigManager;
import com.bigenergy.obelevator.init.ElevatorItemGroups;
import com.bigenergy.obelevator.init.ElevatorItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenBlocksElevator implements ModInitializer {
    public static String MOD_ID = "obelevator";
    public static String MOD_NAME = "OpenBlocksElevator-Fabric";
    public static Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        ConfigManager.init();

        ElevatorBlocks.register();
        ElevatorItems.register();
        ElevatorItemGroups.register();

        LOGGER.info("Hello from {}, initialized!", MOD_NAME);
    }
}
