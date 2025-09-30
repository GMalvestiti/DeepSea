package net.riser876.deepsea.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Config {

    @Expose
    @SerializedName("enabled")
    public boolean ENABLED = ConfigDefaultValues.ENABLED;

    @Expose
    @SerializedName("tick_interval")
    public int DEEP_SEA_TICK_INTERVAL = ConfigDefaultValues.DEEP_SEA_TICK_INTERVAL;
}
