package io.github.jamalam360.quickerconnectbutton.fabric;

import io.github.jamalam360.quickerconnectbutton.QuickerConnectButton;
import net.fabricmc.api.ModInitializer;

public class QuickerConnectButtonFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        QuickerConnectButton.init();
    }
}
