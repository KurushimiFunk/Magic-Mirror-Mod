package net.kurushimifunk.magic_mirror_mod;

import net.fabricmc.api.ModInitializer;
import net.kurushimifunk.magic_mirror_mod.item.ModItems;
import net.kurushimifunk.magic_mirror_mod.sounds.ModSounds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagicMirrorMod implements ModInitializer {

	public static final String MOD_ID = "magic_mirror_mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Loading "+MagicMirrorMod.MOD_ID);
		
		ModItems.RegisterModItems();
		ModSounds.RegisterSounds();
	}
}
