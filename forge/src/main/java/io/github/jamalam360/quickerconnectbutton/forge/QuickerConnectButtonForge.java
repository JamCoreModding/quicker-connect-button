package io.github.jamalam360.quickerconnectbutton.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import io.github.jamalam360.quickerconnectbutton.QuickerConnectButton;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(QuickerConnectButton.MOD_ID)
public class QuickerConnectButtonForge {
	public QuickerConnectButtonForge() {
		EventBuses.registerModEventBus(QuickerConnectButton.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
		EnvExecutor.runInEnv(Env.CLIENT, () -> QuickerConnectButton::init);
	}
}
