package com.mod.elventools.items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class ParsnipAndPotatoStew  extends Item {
    public ParsnipAndPotatoStew(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(15) // How much hunger it restores
                .saturationModifier(3.0F) // How much saturation it gives
                .effect(new MobEffectInstance(MobEffects.REGENERATION, 9600, 1), 1.0F) // Gives player regeneration  for 8 minutes
                .build()));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT; // Makes the player use the eating animation
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        super.finishUsingItem(stack, world, entity); // Apply the food effect
        // Return an empty bowl after eating
        return new ItemStack(Items.BOWL);
    }
}
