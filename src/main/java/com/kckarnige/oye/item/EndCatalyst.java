package com.kckarnige.oye.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
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

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 2, false, false, true), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 2, false, false, true), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 2, false, false, true), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 2, false, false, true), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 1, false, false, true), attacker);
        return super.postHit(stack, target, attacker);
    }

    public void inventoryTick(ItemStack nullStack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player) {
                if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(ModItems.VOID_MATTER)) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 3, false, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 3, false, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 3, false, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 2, false, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1, false, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20, 3, true, false, false));
                } 
                else if (player.getEquippedStack(EquipmentSlot.OFFHAND).isOf(ModItems.VOID_MATTER) || player.getInventory().main.stream().anyMatch(stack -> stack.isOf(ModItems.VOID_MATTER))) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 1, false, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 1, false, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 1, false, false, false));
                }
            }
        }
    }

}