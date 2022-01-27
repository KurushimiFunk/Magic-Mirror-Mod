package net.kurushimifunk.magic_mirror_mod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kurushimifunk.magic_mirror_mod.MagicMirrorMod;
import net.kurushimifunk.magic_mirror_mod.item.custom.MagicMirror;

public class ModItems {
    
    public static final Item Magic_Mirror = RegisterItem("magic_mirror", new MagicMirror(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1).rarity(Rarity.RARE).maxDamage(32)));

    private static Item RegisterItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(MagicMirrorMod.MOD_ID, name) , item);

    }
    
    public static void RegisterModItems(){
        System.out.println("Registering Mod items for "+MagicMirrorMod.MOD_ID);
    }
}
