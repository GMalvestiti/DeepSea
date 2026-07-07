package net.riser876.deepsea;

import java.nio.file.Path;

public sealed interface ModLoader {

    ModLoader INSTANCE =
        /*? if fabric{*/new FabricModLoader();
        /*?} elif neoforge *///new NeoForgeModLoader();

    Path getConfigDir();

    //? if fabric {
    final class FabricModLoader implements ModLoader {
        private final net.fabricmc.loader.api.FabricLoader loader = net.fabricmc.loader.api.FabricLoader.getInstance();

        @Override
        public Path getConfigDir() {
            return loader.getConfigDir();
        }
    }
    //?} elif neoforge {
    /*final class NeoForgeModLoader implements ModLoader {

        @Override
        public Path getConfigDir() {
            return null;
        }
    }
    *///?}
}
