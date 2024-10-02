package com.kckarnige.oye.item;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AntiCatalyst extends Item {

    public AntiCatalyst(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient()) {
            player.getItemCooldownManager().set(this, 15);
            world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 120, 2, false, false, true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 120, 3, false, false, true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 120, 3, false, false, true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 120, 2, false, false, true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 120, 2, false, false, true));
            if (FabricLoader.getInstance().isModLoaded("saturative")) {
                if (player.getHungerManager().getFoodLevel() < 100) {
                    player.getHungerManager().add(50, 5);
                }
            } else {
                if (player.getHungerManager().getFoodLevel() < 10) {
                    player.getHungerManager().add(14, 5);
                }
            }
            
        }
        return TypedActionResult.consume(player.getStackInHand(hand).copyWithCount(player.getStackInHand(hand).getCount() - 1));
    }

}