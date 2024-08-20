package com.kckarnige.oye.item;

import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EndCatalyst extends Item {

    public EndCatalyst(Settings settings) {
        super(settings);
    }

    public void inventoryTick(ItemStack nullStack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player) {

                if (player.getInventory().main.stream().anyMatch(stack -> stack.isOf(ModItems.VOID_MATTER))) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 2, true, false, true));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 2, true, false, true));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 2, true, false, true));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 1, true, false, true));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 60, 1, true, false, true));
                }
            }
        }
    }

}