package io.github.jamalam360.quickerconnectbutton.quilt;

import io.github.jamalam360.quickerconnectbutton.QuickerConnectButton;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class QuickerConnectButtonQuilt implements ModInitializer {
	@Override
	public void onInitialize(ModContainer mod) {
		QuickerConnectButton.init();
	}
}
