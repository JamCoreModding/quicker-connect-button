package io.github.jamalam360.quickerconnectbutton.neoforge;

import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import io.github.jamalam360.quickerconnectbutton.QuickerConnectButton;
import net.neoforged.fml.common.Mod;

@Mod(QuickerConnectButton.MOD_ID)
public class QuickerConnectButtonNeoForge {
	public QuickerConnectButtonNeoForge() {
		EnvExecutor.runInEnv(Env.CLIENT, () -> QuickerConnectButton::init);
	}
}
