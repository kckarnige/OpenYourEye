package com.kckarnige.oye.item;

import com.kckarnige.oye.oye;
import net.minecraft.block.WitherRoseBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Objects;

public class EndCatalyst extends Item {

    public EndCatalyst(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient()) {
            player.equipStack(EquipmentSlot.HEAD, getDefaultStack());
            world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS);
        }
        return TypedActionResult.success(ItemStack.EMPTY);
    }

    public void inventoryTick(ItemStack nullStack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player) {
                if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(ModItems.VOID_MATTER)) {
                    if (String.valueOf(player.getEquippedStack(EquipmentSlot.OFFHAND)).contains("minecraft:wither_rose")||
                            String.valueOf(player.getEquippedStack(EquipmentSlot.MAINHAND)).contains("minecraft:wither_rose")) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 30, 2, true, false, false));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 30, 3, true, false, false));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 30, 3, true, false, false));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 30, 2, true, false, false));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 30, 2, true, false, false));
                        player.removeStatusEffect(StatusEffects.DARKNESS);
                        player.removeStatusEffect(StatusEffects.BLINDNESS);
                        player.removeStatusEffect(StatusEffects.WEAKNESS);
                        player.removeStatusEffect(StatusEffects.SLOWNESS);
                        player.removeStatusEffect(StatusEffects.WITHER);
                        player.removeStatusEffect(StatusEffects.HUNGER);
                    } else {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100, 3, false, false, false));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 100, 3, false, false, false));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 30, 1, false, false, false));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30, 2, false, false, false));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 2, false, false, false));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 1, false, false, false));
                        world.addParticle(ParticleTypes.HEART, player.getX(), player.getY(), player.getZ(), 0.0, 0.0, 0.0);
                    }
                } 
                else if (player.getEquippedStack(EquipmentSlot.OFFHAND).isOf(ModItems.VOID_MATTER)
                        || player.getInventory().main.stream().anyMatch(stack -> stack.isOf(ModItems.VOID_MATTER))) {
                    if (String.valueOf(player.getEquippedStack(EquipmentSlot.OFFHAND)).contains("minecraft:wither_rose")
                            || player.getInventory().main.stream().anyMatch(stack -> String.valueOf(stack).contains("minecraft:wither_rose"))) {}
                    else {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 1, false, false, false));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 1, false, false, false));
                    }
                }
            }
        }
    }

}