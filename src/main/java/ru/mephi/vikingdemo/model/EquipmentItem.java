package ru.mephi.vikingdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;

@Schema(description = "Предмет снаряжения")
@Embeddable
public class EquipmentItem {
    
    @Schema(description = "Название предмета", example = "Iron Axe")
    private String name;
    
    @Schema(description = "Редкость или качество", example = "Rare")
    private String quality;

    public EquipmentItem() {}

    public EquipmentItem(String name, String quality) {
        this.name = name;
        this.quality = quality;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getQuality() { return quality; }
    public void setQuality(String quality) { this.quality = quality; }
}