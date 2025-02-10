package com.mod.elventools.items;

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

    // Replaces the previous light block with an air block 
    private void replacePreviousLight(Level level) { 
        BlockState previousBlockState = level.getBlockState(previousPos);
        if (previousBlockState.is(Blocks.LIGHT)) {
            level.setBlockAndUpdate(previousPos, Blocks.AIR.defaultBlockState()); // Set block on previous position back to air
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide()) { // Check if the entity is a player
            if((isSelected || player.getOffhandItem() == stack)){ // Checks if the player is holding the sword
                BlockPos currentPos = player.blockPosition().above(); // Gets current block state, returning the block that corresponds to the upper half of the palyer
                BlockState currentState = level.getBlockState(currentPos); // Gets the state of the block
                
                if (!(player.isInWall())) { // Checks if player is in a block, i.e. grass, water etc...
                    // Replace previous block
                    if (previousPos != null && !previousPos.equals(currentPos)) {
                        replacePreviousLight(level);
                    }

                    // Replace current block with light
                    if (currentState.isAir()) {
                        level.setBlockAndUpdate(currentPos, Blocks.LIGHT.defaultBlockState()); // Replaces current block with a light block
                    }
                
                    // Update the previous position
                    previousPos = currentPos;
                }
            } else if (previousPos != null) { 
                replacePreviousLight(level);
                previousPos = null;
            }
            // Gives the player luck
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
