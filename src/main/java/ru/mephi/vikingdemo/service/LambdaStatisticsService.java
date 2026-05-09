package ru.mephi.vikingdemo.service;

import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.EquipmentItem;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class LambdaStatisticsService {
    public long countByAge(List<Viking> vikings, int age, String condition) {
        return switch (condition) {
            case "older" -> vikings.stream().filter(v -> v.getAge() > age).count();
            case "younger" -> vikings.stream().filter(v -> v.getAge() < age).count();
            case "between" -> vikings.stream().filter(v -> v.getAge() >= age && v.getAge() <= age + 10).count();
            case "outside" -> vikings.stream().filter(v -> v.getAge() < age || v.getAge() > age + 10).count();
            default -> 0;
        };
    }

    public long countByBeardAndHair(List<Viking> vikings, BeardStyle beard, HairColor hair) {
        return vikings.stream()
                .filter(v -> v.getBeardStyle() == beard && v.getHairColor() == hair)
                .count();
    }

    public long countByAxesCount(List<Viking> vikings, int axesCount) {
        return vikings.stream()
                .filter(v -> countAxes(v.getEquipment()) == axesCount)
                .count();
    }

    private long countAxes(List<EquipmentItem> equipment) {
        return equipment.stream()
                .filter(item -> item.getName().toLowerCase().contains("axe"))
                .count();
    }

    public Viking getRandomVikingAboveHeight(List<Viking> vikings, int minHeight) {
        List<Viking> filtered = vikings.stream()
                .filter(v -> v.getHeightCm() > minHeight)
                .toList();
        if (filtered.isEmpty()) return null;
        Random random = new Random();
        return filtered.get(random.nextInt(filtered.size()));
    }

    public List<Viking> getVikingsWithLegendaryGear(List<Viking> vikings) {
        return vikings.stream()
                .filter(v -> v.getEquipment().stream()
                        .anyMatch(item -> "Legendary".equalsIgnoreCase(item.getQuality())))
                .toList();
    }

    public List<Viking> getRedBeardedSortedByAge(List<Viking> vikings) {
        return vikings.stream()
                .filter(v -> v.getBeardStyle() == BeardStyle.LONG && v.getHairColor() == HairColor.Red)
                .sorted(Comparator.comparingInt(Viking::getAge))
                .toList();
    }

    public Long getMaxId(List<Viking> vikings) {
        return vikings.stream()
                .map(Viking::getId)
                .max(Long::compareTo)
                .orElse(-1L);
    }

    public List<Long> getEvenIds(List<Viking> vikings) {
        return vikings.stream()
                .map(Viking::getId)
                .filter(id -> id != null && id % 2 == 0)
                .toList();
    }
}