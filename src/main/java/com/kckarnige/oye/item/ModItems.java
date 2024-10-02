package com.kckarnige.oye.item;

import com.kckarnige.oye.oye;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    public static final Item VOID_MATTER = registerItem("end_matter", new EndCatalyst(new Item.Settings().maxCount(16).rarity(Rarity.UNCOMMON)));
    public static final Item ANTIV_MATTER = registerItem("anti_end_matter", new AntiCatalyst(new Item.Settings().maxCount(16).rarity(Rarity.RARE)));

    private static void addItemToItemGroup(FabricItemGroupEntries entries) {
        entries.add(VOID_MATTER);
        entries.add(ANTIV_MATTER);
    }

    private static Item registerItem (String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(oye.MOD_ID, name), item);
    }

    public static void registerModItems () {
        oye.LOGGER.info("[Open Your Eye] Registering End Catalyst item...");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemToItemGroup);
    }
}
