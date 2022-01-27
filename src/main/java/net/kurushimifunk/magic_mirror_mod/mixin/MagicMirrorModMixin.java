package net.kurushimifunk.magic_mirror_mod.mixin;

import net.kurushimifunk.magic_mirror_mod.MagicMirrorMod;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MagicMirrorModMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		MagicMirrorMod.LOGGER.info("Loading mixin - "+MagicMirrorMod.MOD_ID);
	}
}