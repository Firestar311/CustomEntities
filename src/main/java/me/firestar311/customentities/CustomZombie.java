package me.firestar311.customentities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CustomZombie extends Zombie {
    public CustomZombie(Level world) {
        super(EntityType.ZOMBIE, world);
    }
    
    public void clearGoals() {
        this.goalSelector.removeAllGoals(goal -> true);
        this.targetSelector.removeAllGoals(goal -> true);
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }
    
    public void resetGoals() {
        this.registerGoals();
    }
}
