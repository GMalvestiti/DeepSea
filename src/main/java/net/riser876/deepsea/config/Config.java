package net.riser876.deepsea.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Config {

    @Expose
    @SerializedName("enabled")
    public boolean ENABLED = true;

    @Expose
    @SerializedName("tick_interval")
    public int DEEP_SEA_TICK_INTERVAL = 100;

    @Expose
    @SerializedName("boat_damage")
    public float DEEP_SEA_BOAT_DAMAGE = 100.0F;
}
