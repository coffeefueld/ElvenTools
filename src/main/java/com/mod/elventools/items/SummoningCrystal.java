package com.mod.elventools.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class SummoningCrystal extends Item {

    public SummoningCrystal(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide) { // Checks if it's server side
            WanderingTrader trader = EntityType.WANDERING_TRADER.create(level); // Entity to spawn
            if (trader != null) {
                BlockPos pos = context.getClickedPos().relative(context.getClickedFace()); 
                trader.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, level.random.nextFloat() * 360F, 0); // Position to spawn the trader
                context.getItemInHand().shrink(1); // Consumes item
                level.addFreshEntity(trader); // Spawns trader   
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
    
}
