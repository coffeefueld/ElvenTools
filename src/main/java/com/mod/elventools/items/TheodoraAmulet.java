package com.mod.elventools.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TheodoraAmulet extends Item implements Equipable{
        
    public TheodoraAmulet(Item.Properties properties) {
        super(properties);
    }

    public boolean isEquippedBy(Player player) {
        // Get the item in the appropriate equipment slot
        ItemStack equippedItem = player.getItemBySlot(this.getEquipmentSlot());

        // Check if the equipped item is this custom item
        return equippedItem.getItem() == this;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide()) { // Check if the entity is a player
            if (this.isEquippedBy(player) || (isSelected || player.getOffhandItem() == stack)) { // Checks if the item is worn or held by the player
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
                    3,                      // Amplifier, 0 = level I, 1 = level II...
                    true,                   // Ambient effect
                    false,                  // Show particles
                    false                   // Show effect icon
            ));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return Equipable.super.swapWithEquipmentSlot(this, level, player, hand); // Swaps held amulet with whatever is worn on the head slot
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        // Specifies the slot where this item should be equipped
        return EquipmentSlot.HEAD;
    }

}
