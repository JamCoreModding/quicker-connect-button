package io.github.jamalam360.quickerconnectbutton.fabric;

import io.github.jamalam360.quickerconnectbutton.fabriclike.QuickerConnectButtonFabricLike;
import net.fabricmc.api.ModInitializer;

public class QuickerConnectButtonFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        QuickerConnectButtonFabricLike.init();
    }
}
