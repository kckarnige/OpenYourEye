package com.kckarnige.oye.item;

import net.fabricmc.fabric.api.item.v1.EquipmentSlotProvider;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class ItsInYourHead implements EquipmentSlotProvider {
    @Override
    public EquipmentSlot getPreferredEquipmentSlot(LivingEntity entity, ItemStack stack) {
        return EquipmentSlot.HEAD;
    }
}
