package com.example.elventools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ElvenSword extends SwordItem {
    
    private BlockPos previousPos = null;

    
    public ElvenSword(Tier tier, Item.Properties prop) {
        super(tier, prop);
    }

    private void repacePreviousLight(Level level) {
        BlockState previousBlockState = level.getBlockState(previousPos);
        if (previousBlockState.is(Blocks.LIGHT)) {
            level.setBlockAndUpdate(previousPos, Blocks.AIR.defaultBlockState()); // Set block on previous position back to air
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide()) { // Check if the entity is a player
            if((isSelected || player.getOffhandItem() == stack)){
                BlockPos currentPos = player.blockPosition().above();
                BlockState currentState = level.getBlockState(currentPos);
                
                if (!(player.isInWall())) {
                    // Replace previous block
                    if (previousPos != null && !previousPos.equals(currentPos)) {
                        repacePreviousLight(level);
                    }
                

                    // Replace current block with light
                    if (currentState.isAir()) {
                        level.setBlockAndUpdate(currentPos, Blocks.LIGHT.defaultBlockState());
                    }
                
                    // Update the previous position
                    previousPos = currentPos;
                }
            } else if (previousPos != null) {
                repacePreviousLight(level);
                previousPos = null;
            }
        
            if (level.isNight()) {
                
            }
            player.addEffect(new MobEffectInstance(
                    MobEffects.LUCK,
                    60,                     // Duration in ticks, 20 ticks = 1 second
                    1,                      // Amplifier, 0 = level I, 1 = level II...
                    true,                   // Ambient effect
                    false,                  // Show particles
                    false                   // Show effect icon
            ));
        }
    }

}
