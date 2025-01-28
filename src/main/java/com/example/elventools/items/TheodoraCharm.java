package com.example.elventools.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TheodoraCharm extends Item{
    public TheodoraCharm(Item.Properties properties) {
        super(properties);
    }

                           // ItemStack stack  Level world  Entity player  int slot  boolean isSelected
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide()) { // Check if the entity is a player
            if (isSelected || player.getOffhandItem() == stack) { // Checks if the item is heald by the player
                player.addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SPEED,
                    60,                     // Duration in ticks, 20 ticks = 1 second
                    2,                      // Amplifier, 0 = level I, 1 = level II...
                    true,                   // Ambient effect
                    false,                  // Show particles
                    false                   // Show effect icon
                ));
                player.addEffect(new MobEffectInstance(
                    MobEffects.INVISIBILITY, 
                    60,                     // Duration in ticks, 20 ticks = 1 second
                    0,                      // Amplifier, 0 = level I, 1 = level II...
                    true,                   // Ambient effect
                    false,                  // Show particles
                    false                   // Show effect icon
                ));
                player.addEffect(new MobEffectInstance(
                    MobEffects.DAMAGE_RESISTANCE,
                    60,                     // Duration in ticks, 20 ticks = 1 second
                    2,                      // Amplifier, 0 = level I, 1 = level II...
                    true,                   // Ambient effect
                    false,                  // Show particles
                    false                   // Show effect icon
                ));
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
    
        super.inventoryTick(stack, level, entity, slot, isSelected);
    }
}
