package ru.mephi.vikingdemo.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Schema(description = "Модель викинга")
@Entity
@Table(name = "vikings")
public class Viking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Имя викинга", example = "Bjorn")
    private String name;

    @Schema(description = "Возраст", example = "31")
    private int age;

    @Schema(description = "Рост в сантиметрах", example = "184")
    private int heightCm;

    @Schema(description = "Цвет волос", example = "Blond")
    @Enumerated(EnumType.STRING)
    private HairColor hairColor;

    @Schema(description = "Форма бороды")
    @Enumerated(EnumType.STRING)
    private BeardStyle beardStyle;

    @ArraySchema(schema = @Schema(implementation = EquipmentItem.class), arraySchema = @Schema(description = "Снаряжение викинга"))
    @ElementCollection(fetch = FetchType.EAGER)
    private List<EquipmentItem> equipment;

    public Viking() {}

    public Viking(String name, int age, int heightCm, HairColor hairColor, BeardStyle beardStyle, List<EquipmentItem> equipment) {
        this.name = name;
        this.age = age;
        this.heightCm = heightCm;
        this.hairColor = hairColor;
        this.beardStyle = beardStyle;
        this.equipment = equipment;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getHeightCm() { return heightCm; }
    public void setHeightCm(int heightCm) { this.heightCm = heightCm; }

    public HairColor getHairColor() { return hairColor; }
    public void setHairColor(HairColor hairColor) { this.hairColor = hairColor; }

    public BeardStyle getBeardStyle() { return beardStyle; }
    public void setBeardStyle(BeardStyle beardStyle) { this.beardStyle = beardStyle; }

    public List<EquipmentItem> getEquipment() { return equipment; }
    public void setEquipment(List<EquipmentItem> equipment) { this.equipment = equipment; }
}