package io.github.jamalam360.quickerconnectbutton.mixin;

import io.github.jamalam360.quickerconnectbutton.QuickerConnectButton;
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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Objects;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
	protected TitleScreenMixin(Component component) {
		super(component);
	}

	@Inject(
			method = "createNormalMenuOptions",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/TitleScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;", ordinal = 1, shift = At.Shift.BEFORE),
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	private void quickerconnectbutton$injectCustomButton(int a, int b, CallbackInfo info, Component multiplayerDisabledReason, boolean multiplayerDisabled, Tooltip multiplayerDisabledTooltip) {
		if (QuickerConnectButton.CONFIG.get().enabled() && !QuickerConnectButton.CONFIG.get().replaceMultiplayerButton) {
			Component text = QuickerConnectButton.CONFIG.get().getButtonText();
			int width = Mth.clamp(Objects.requireNonNull(this.minecraft).font.width(text), 50, this.width - (this.width / 2 + 108));

			this.addRenderableWidget(Button.builder(text, button -> {
				ServerData data = QuickerConnectButton.createServerData();
				ConnectScreen.startConnecting(this, this.minecraft, ServerAddress.parseString(data.ip), data, false);
			}).bounds(this.width / 2 + 104, a + b, width, 20).tooltip(multiplayerDisabledTooltip).build()).active = multiplayerDisabled;
		}
	}

	//FIXME: Apparently ModifyArgs straight up doesn't work on Forge (modlauncher). Using two (ModifyArg)s instead.
//	@ModifyArgs(
//			method = "createNormalMenuOptions",
//			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button;builder(Lnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/components/Button$OnPress;)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 1)
//	)
//	private void quickerconnectbutton$modifyMultiplayerButton(Args args) {
//		if (QuickerConnectButton.CONFIG.get().replaceMultiplayerButton) {
//			args.set(0, QuickerConnectButton.CONFIG.get().getButtonText());
//			args.<Button.OnPress>set(1, (button) -> {
//				ServerData data = QuickerConnectButton.createServerData();
//				ConnectScreen.startConnecting(this, Objects.requireNonNull(this.minecraft), ServerAddress.parseString(data.ip), data, false);
//			});
//		}
//	}

	@ModifyArg(
			method = "createNormalMenuOptions",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button;builder(Lnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/components/Button$OnPress;)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 1),
			index = 0
	)
	private Component quickerconnectbutton$modifyMultiplayerButtonText(Component text) {
		if (QuickerConnectButton.CONFIG.get().replaceMultiplayerButton) {
			return QuickerConnectButton.CONFIG.get().getButtonText();
		} else {
			return text;
		}
	}

	@ModifyArg(
			method = "createNormalMenuOptions",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button;builder(Lnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/components/Button$OnPress;)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 1),
			index = 1
	)
	private Button.OnPress quickerconnectbutton$modifyMultiplayerButtonOnPress(Button.OnPress onPress) {
		if (QuickerConnectButton.CONFIG.get().replaceMultiplayerButton) {
			return (button) -> {
				ServerData data = QuickerConnectButton.createServerData();
				ConnectScreen.startConnecting(this, Objects.requireNonNull(this.minecraft), ServerAddress.parseString(data.ip), data, false);
			};
		} else {
			return onPress;
		}
	}
}
