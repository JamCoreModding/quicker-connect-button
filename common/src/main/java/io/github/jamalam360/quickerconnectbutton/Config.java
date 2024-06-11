package io.github.jamalam360.quickerconnectbutton;

import blue.endless.jankson.Comment;
import io.github.jamalam360.jamlib.config.ConfigExtensions;
import io.github.jamalam360.jamlib.config.HiddenInGui;
import io.github.jamalam360.jamlib.config.WithinRange;
import java.util.List;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.chat.Component;

public class Config implements ConfigExtensions<Config> {

    @Comment("Leave empty to disable the quick connect button")
    public String ip = "";
    @WithinRange(min = 0, max = 65535)
    public int port = 25565;
    @Comment("(Deprecated, use buttonLocation) Whether to replace the default multiplayer button on the main menu, or add a new one to its right.")
    @HiddenInGui
    @Deprecated(forRemoval = true)
    public boolean replaceMultiplayerButton = false;
    @Comment("Where to place the connect button")
    public ButtonLocation buttonLocation = ButtonLocation.RIGHT;
    @Comment("The text to display on the button. Leave empty to use \"Connect\".")
    public String text = "";
    @Comment("What to do if the server has a resource pack.")
    public ServerData.ServerPackStatus resourcePackBehaviour = ServerData.ServerPackStatus.PROMPT;

    public boolean enabled() {
        return this.ip != null && !this.ip.isEmpty();
    }

    public ButtonLocation getButtonLocation() {
        if (this.replaceMultiplayerButton) {
            return ButtonLocation.REPLACE_MULTIPLAYER_BUTTON;
        } else {
            return this.buttonLocation;
        }
    }

    public Component getButtonText() {
        if (this.text != null && !this.text.isEmpty()) {
            return Component.literal(this.text);
        } else {
            return Component.translatable("menu.quickerconnectbutton.connect");
        }
    }

    @Override
    public List<Link> getLinks() {
        return List.of(
              new Link(Link.DISCORD, "https://discord.jamalam.tech", Component.translatable("config.quickerconnectbutton.discord")),
              new Link(Link.GITHUB, "https://github.com/JamCoreModding/quicker-connect-button", Component.translatable("config.quickerconnectbutton.github")),
              new Link(Link.GENERIC_LINK, "https://modrinth.com/mod/quicker-connect-button", Component.translatable("config.quickerconnectbutton.modrinth"))
        );
    }

    public enum ButtonLocation {
        RIGHT,
        REPLACE_MULTIPLAYER_BUTTON,
        REPLACE_SINGLEPLAYER_BUTTON,
        REPLACE_REALMS_BUTTON
    }
}
