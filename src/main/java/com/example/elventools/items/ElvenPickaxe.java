package com.example.elventools.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class ElvenPickaxe extends PickaxeItem{

    public ElvenPickaxe(Tier tier, Item.Properties prop) {
        super(tier, prop);
    }
    
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide()) { // Check if the entity is a player and 
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
