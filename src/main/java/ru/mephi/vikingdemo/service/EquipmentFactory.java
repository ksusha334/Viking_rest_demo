package ru.mephi.vikingdemo.service;

import ru.mephi.vikingdemo.model.EquipmentItem;

public class EquipmentFactory {
    
    public static EquipmentItem createItem() {
        String[] names = {"Sword", "Axe", "Shield", "Helmet", "Bow"};
        String[] qualities = {"Common", "Rare", "Epic", "Legendary"};
        java.util.Random random = new java.util.Random();
        return new EquipmentItem(
                names[random.nextInt(names.length)],
                qualities[random.nextInt(qualities.length)]
        );
    }
}