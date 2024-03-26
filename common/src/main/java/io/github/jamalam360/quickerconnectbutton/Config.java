package io.github.jamalam360.quickerconnectbutton;

import blue.endless.jankson.Comment;
import io.github.jamalam360.jamlib.config.ConfigExtensions;
import io.github.jamalam360.jamlib.config.ConfigManager;
import io.github.jamalam360.jamlib.config.MatchesRegex;
import io.github.jamalam360.jamlib.config.WithinRange;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.chat.Component;

import java.util.List;

public class Config implements ConfigExtensions<Config> {
	// Regex was a mistake
	@MatchesRegex("^((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$|^[a-zA-Z0-9.-]+)?$")
	@Comment("Leave empty to disable the quick connect button")
	public String ip = "";
	@WithinRange(min = 0, max = 65535)
	public int port = 25565;
	@Comment("Whether to replace the default multiplayer button on the main menu, or add a new one to its right.")
	public boolean replaceMultiplayerButton = false;
	@Comment("The text to display on the button. Leave empty to use \"Connect\".")
	public String text = "";
	@Comment("What to do if the server has a resource pack.")
	public ServerData.ServerPackStatus resourcePackBehaviour = ServerData.ServerPackStatus.PROMPT;


	public boolean enabled() {
		return this.ip != null && !this.ip.equals("");
	}

	public Component getButtonText() {
		if (this.text != null && !this.text.equals("")) {
			return Component.literal(this.text);
		} else {
			return Component.translatable("menu.quickerconnectbutton.connect");
		}
	}

	@Override
	public List<Link> getLinks() {
		return List.of(
				new Link(Link.DISCORD, "https://jamalam.tech/Discord", Component.translatable("config.quickerconnectbutton.discord")),
				new Link(Link.GITHUB, "https://github.com/JamCoreModding/quicker-connect-button", Component.translatable("config.quickerconnectbutton.github")),
				new Link(Link.GENERIC_LINK, "https://modrinth.com/mod/quicker-connect-button", Component.translatable("config.quickerconnectbutton.modrinth"))
		);
	}
}
