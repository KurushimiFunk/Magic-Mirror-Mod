package net.kurushimifunk.magic_mirror_mod.item.custom;

import java.util.Optional;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kurushimifunk.magic_mirror_mod.sounds.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MagicMirror extends Item{
        
    
    public MagicMirror(FabricItemSettings settings){
        super(settings);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return Ingredient.ofItems(Items.DIAMOND).test(ingredient) || super.canRepair(stack, ingredient);
    }

    @Override
    public int getEnchantability() {
        return 5;
    }

    private void teleportToSpawn(MinecraftServer server, ServerPlayerEntity serverUser, Hand hand){
        ServerWorld overWorld = server.getOverworld();
        BlockPos defaultSpawnPoint = overWorld.getSpawnPos();
        serverUser.teleport(overWorld ,defaultSpawnPoint.getX(), defaultSpawnPoint.getY(), defaultSpawnPoint.getZ(), 0, 0);
        overWorld.playSound( null, serverUser.getPos().x,serverUser.getPos().y,serverUser.getPos().z, ModSounds.TELEPORT_SOUND, SoundCategory.NEUTRAL, 1, 1);
        overWorld.sendEntityStatus(serverUser, (byte)46);
        serverUser.getStackInHand(hand).damage(1, serverUser, p -> p.sendToolBreakStatus(hand));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            MinecraftServer server = world.getServer();
            ServerPlayerEntity serverUser = server.getPlayerManager().getPlayer(user.getUuid());

            ServerWorld dimension = server.getWorld(serverUser.getSpawnPointDimension());
            BlockPos pos = serverUser.getSpawnPointPosition();

            world.playSound( null, user.getPos().x,user.getPos().y,user.getPos().z, ModSounds.TELEPORT_SOUND, SoundCategory.NEUTRAL, 1f, 1f);
            world.sendEntityStatus(user, (byte)46);
            user.getItemCooldownManager().set(this, 30);

            if(dimension !=null && pos !=null){
                Optional<Vec3d> userSpawn = PlayerEntity.findRespawnPosition(dimension, pos, 0f, false, user.isAlive());
                if(userSpawn.isPresent()){
                    serverUser.teleport(dimension, userSpawn.get().x, userSpawn.get().y, userSpawn.get().z, 0f, 0f);
                    dimension.playSound( null, serverUser.getPos().x,serverUser.getPos().y,serverUser.getPos().z, ModSounds.TELEPORT_SOUND, SoundCategory.NEUTRAL, 1f, 1f);
                    dimension.sendEntityStatus(serverUser, (byte)46);
                    serverUser.getStackInHand(hand).damage(1, serverUser, p -> p.sendToolBreakStatus(hand));
                }
                else{
                    teleportToSpawn(server, serverUser, hand);
                }
            }
            else{
                teleportToSpawn(server,serverUser, hand);
            }
        }
        
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
