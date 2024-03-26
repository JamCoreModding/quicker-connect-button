package io.github.jamalam360.quickerconnectbutton;

import io.github.jamalam360.jamlib.JamLib;
import io.github.jamalam360.jamlib.JamLibPlatform;
import io.github.jamalam360.jamlib.config.ConfigManager;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.language.I18n;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickerConnectButton {
	public static final String MOD_ID = "quickerconnectbutton";
	public static final String MOD_NAME = "Quicker Connect Button";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
	public static final ConfigManager<Config> CONFIG = new ConfigManager<>(MOD_ID, Config.class);

	public static void init() {
		LOGGER.info("Loading Quicker Connect Button on " + JamLibPlatform.getPlatform().name());
		JamLib.checkForJarRenaming(QuickerConnectButton.class);
	}

	public static ServerData createServerData() {
		ServerData data = new ServerData(I18n.get("selectServer.defaultName"), CONFIG.get().ip + ":" + CONFIG.get().port, ServerData.Type.OTHER);
		data.setResourcePackStatus(CONFIG.get().resourcePackBehaviour);
		return data;
	}
}
