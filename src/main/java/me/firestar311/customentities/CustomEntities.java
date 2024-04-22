package me.firestar311.customentities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class CustomEntities extends JavaPlugin {
    @Override
    public void onEnable() {
        try {
            Field factoryField = null;
            for (Field declaredField : EntityType.class.getDeclaredFields()) {
                if (declaredField.getType().equals(EntityType.EntityFactory.class)) {
                    factoryField = declaredField;
                    break;
                }
            }
            factoryField.setAccessible(true);
            factoryField.set(EntityType.ZOMBIE, (EntityType.EntityFactory<Entity>) (entityType, level) -> new CustomZombie(level));
            System.out.println("Replaced zombie with custom entity type");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender; 
        World world = player.getWorld();
        CraftWorld craftWorld = (CraftWorld) world;
        ServerLevel serverLevel = craftWorld.getHandle();
        Location location = player.getLocation();
        CustomZombie entity = (CustomZombie) EntityType.ZOMBIE.spawn(serverLevel, new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ()), MobSpawnType.NATURAL);
        entity.clearGoals();
        return true;
    }
}