package com.mod.elventools.blocks;

import com.mod.elventools.ElvenTools;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ElvenCherryTomatoesCropBlock extends CropBlock{

    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 5); // Crop age range

    public ElvenCherryTomatoesCropBlock(Properties properties) {
        super(properties);
    }
    
    // Seeds used to plant the crop
    @Override
    protected ItemLike getBaseSeedId() { 
        return ElvenTools.ELVEN_CHERRY_TOMATOES_SEEDS.get();
    }

    // Age
    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    // Max age
    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    // Creates block states
    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> p_52286_) {
        p_52286_.add(AGE);
    }
}
