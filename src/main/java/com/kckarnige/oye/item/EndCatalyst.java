package com.kckarnige.oye.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
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

public class EndCatalyst extends Item {

    public EndCatalyst(Settings settings) {
        super(settings);
    }

    public void inventoryTick(ItemStack nullStack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player) {
                if (player.getEquippedStack(EquipmentSlot.OFFHAND).isOf(ModItems.VOID_MATTER)
                        || player.getEquippedStack(EquipmentSlot.HEAD).isOf(ModItems.VOID_MATTER)
                        || player.getInventory().main.stream().anyMatch(stack -> stack.isOf(ModItems.VOID_MATTER))) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 1, false, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 1, false, false, false));
                }
            }
        }
    }

}