package io.github.jamalam360.quickerconnectbutton.quilt;

import io.github.jamalam360.quickerconnectbutton.QuickerConnectButton;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class QuickerConnectButtonQuilt implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		QuickerConnectButton.init();
	}
}
