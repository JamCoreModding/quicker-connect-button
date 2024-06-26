package io.github.jamalam360.quickerconnectbutton.mixin;

import dev.architectury.platform.Platform;
import io.github.jamalam360.quickerconnectbutton.Config.ButtonLocation;
import io.github.jamalam360.quickerconnectbutton.QuickerConnectButton;
import java.util.Objects;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {

    protected TitleScreenMixin(Component component) {
        super(component);
    }

    @Inject(
          method = "init",
          at = @At("HEAD")
    )
    private void quickerconnectbutton$init(CallbackInfo ci) {
        if (Platform.isModLoaded("modmenu") && QuickerConnectButton.CONFIG.get().getButtonLocation() == ButtonLocation.REPLACE_REALMS_BUTTON) {
            QuickerConnectButton.LOGGER.warn("ModMenu is installed, but the button location is set to REPLACE_REALMS_BUTTON. This will not work. Defaulting to RIGHT.");
            QuickerConnectButton.CONFIG.get().buttonLocation = ButtonLocation.RIGHT;
            QuickerConnectButton.CONFIG.save();
        }
    }

    @Inject(
          method = "createNormalMenuOptions",
          at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/TitleScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;", ordinal = 1, shift = At.Shift.BEFORE),
          locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void quickerconnectbutton$buttonRight(int a, int b, CallbackInfo info, Component multiplayerDisabledReason, boolean multiplayerDisabled, Tooltip multiplayerDisabledTooltip) {
        if (QuickerConnectButton.CONFIG.get().enabled() && QuickerConnectButton.CONFIG.get().getButtonLocation() == ButtonLocation.RIGHT) {
            Component text = QuickerConnectButton.CONFIG.get().getButtonText();
            int width = Mth.clamp(Objects.requireNonNull(this.minecraft).font.width(text), 50, this.width - (this.width / 2 + 108));

            this.addRenderableWidget(Button.builder(text, button -> {
                ServerData data = QuickerConnectButton.createServerData();
                ConnectScreen.startConnecting(this, this.minecraft, ServerAddress.parseString(data.ip), data, false, null);
            }).bounds(this.width / 2 + 104, a + b, width, 20).tooltip(multiplayerDisabledTooltip).build()).active = multiplayerDisabled;
        }
    }

    @ModifyArgs(
          method = "createNormalMenuOptions",
          at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button;builder(Lnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/components/Button$OnPress;)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 0)
    )
    private void quickerconnectbutton$buttonSingleplayer(Args args) {
        if (QuickerConnectButton.CONFIG.get().getButtonLocation() == ButtonLocation.REPLACE_SINGLEPLAYER_BUTTON) {
            this.quickerconnectbutton$replaceButton(args);
        }
    }

    @ModifyArgs(
          method = "createNormalMenuOptions",
          at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button;builder(Lnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/components/Button$OnPress;)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 1)
    )
    private void quickerconnectbutton$buttonMultiplayer(Args args) {
        if (QuickerConnectButton.CONFIG.get().getButtonLocation() == ButtonLocation.REPLACE_MULTIPLAYER_BUTTON) {
            this.quickerconnectbutton$replaceButton(args);
        }
    }

    @ModifyArgs(
          method = "createNormalMenuOptions",
          at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button;builder(Lnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/components/Button$OnPress;)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 2)
    )
    private void quickerconnectbutton$buttonRealms(Args args) {
        if (QuickerConnectButton.CONFIG.get().getButtonLocation() == ButtonLocation.REPLACE_REALMS_BUTTON) {
            this.quickerconnectbutton$replaceButton(args);
        }
    }

    @Unique
    private void quickerconnectbutton$replaceButton(Args args) {
        args.set(0, QuickerConnectButton.CONFIG.get().getButtonText());
        args.<Button.OnPress>set(1, (button) -> {
            ServerData data = QuickerConnectButton.createServerData();
            ConnectScreen.startConnecting(this, Objects.requireNonNull(this.minecraft), ServerAddress.parseString(data.ip), data, false, null);
        });
    }
}
