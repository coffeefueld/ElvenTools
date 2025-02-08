package com.example.elventools.blocks;

import com.example.elventools.ElvenTools;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ElvenCherryTomatoesCropBlock extends CropBlock{

    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 5);

    public ElvenCherryTomatoesCropBlock(Properties properties) {
        super(properties);
        
    }
    
    @Override
    protected ItemLike getBaseSeedId() { 
        return ElvenTools.ELVEN_CHERRY_TOMATOES.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> p_52286_) {
        p_52286_.add(AGE);
    }
}
