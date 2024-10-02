package com.kckarnige.oye.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class EndCatalyst extends Item {

    public EndCatalyst(Settings settings) {
        super(settings);
    }

    public void inventoryTick(ItemStack nullStack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player) {
                if (player.getInventory().containsAny((stack -> stack.isOf(ModItems.VOID_MATTER)))) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 40, 1, false, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 40, 1, false, false, false));
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.openyoureye.end_matter.lore").formatted(Formatting.DARK_GRAY));
    }

}