package com.bigenergy.obelevator.config;

public class Config {
    public int range;
    public boolean centerPitchOnTp, allowCarpetsOnElevator, elevatorNotFoundMsg;

    public Config() {
        range = 20;
        centerPitchOnTp = true;
        allowCarpetsOnElevator = true;
        elevatorNotFoundMsg = false;
    }

    public String serialize() {
        return ConfigManager.GSON.toJson(this, Config.class);
    }
}
