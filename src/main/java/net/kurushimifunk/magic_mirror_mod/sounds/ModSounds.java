package net.kurushimifunk.magic_mirror_mod.sounds;

import net.kurushimifunk.magic_mirror_mod.MagicMirrorMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    
    public static final SoundEvent TELEPORT_SOUND = RegisterSoundEvent("teleport");

    private static SoundEvent RegisterSoundEvent(String name){
        Identifier id = new Identifier(MagicMirrorMod.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void RegisterSounds(){
        System.out.println("Registering Mod sounds for "+ MagicMirrorMod.MOD_ID);
    }

}
