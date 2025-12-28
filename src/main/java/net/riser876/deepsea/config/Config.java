package net.riser876.deepsea.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Config {

    @Expose
    @SerializedName("enabled")
    public boolean ENABLED = true;

    @Expose
    @SerializedName("tick_interval")
    public int TICK_INTERVAL = 100;

    @Expose
    @SerializedName("boat_damage")
    public float BOAT_DAMAGE = 100.0F;

    @Expose
    @SerializedName("discard_boat")
    public boolean DISCARD_BOAT = false;

    @Expose
    @SerializedName("cache")
    public CacheConfig CACHE = new CacheConfig();

    @Expose
    @SerializedName("sound")
    public SoundConfig SOUND = new SoundConfig();

    public static class CacheConfig {

        @Expose
        @SerializedName("cache_size")
        public int CACHE_SIZE = 500;

        @Expose
        @SerializedName("cache_time")
        public int CACHE_TIME = 240;
    }

    public static class SoundConfig {

        @Expose
        @SerializedName("play_sound")
        public boolean DEEP_SEA_PLAY_SOUND = true;

        @Expose
        @SerializedName("volume")
        public float VOLUME = 1.0F;

        @Expose
        @SerializedName("pitch")
        public float PITCH = 1.0F;
    }
}