package io.github.jamalam360.quickerconnectbutton.fabric;

import io.github.jamalam360.quickerconnectbutton.QuickerConnectButton;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class QuickerConnectButtonFabric implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        QuickerConnectButton.init();
    }
}
