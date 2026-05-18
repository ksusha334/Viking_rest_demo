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
    public long countByAge(List<Viking> vikings, int age, String condition) { //Условиям по возрасту (больше, меньше, в диапазоне, вне диапазона)
        return switch (condition) {
            case "older" -> vikings.stream().filter(v -> v.getAge() > age).count();
            case "younger" -> vikings.stream().filter(v -> v.getAge() < age).count();
            case "between" -> vikings.stream().filter(v -> v.getAge() >= age && v.getAge() <= age + 10).count();
            case "outside" -> vikings.stream().filter(v -> v.getAge() < age || v.getAge() > age + 10).count();
            default -> 0;
        };
    }

    public long countByBeardAndHair(List<Viking> vikings, BeardStyle beard, HairColor hair) { //Условиям по форме бороды И цвету волос (одновременное)
        return vikings.stream()
                .filter(v -> v.getBeardStyle() == beard && v.getHairColor() == hair)
                .count();
    }

    public long countByAxesCount(List<Viking> vikings, int axesCount) { //Имеющих один топор или два топора
        return vikings.stream()
                .filter(v -> countAxes(v.getEquipment()) == axesCount)
                .count();
    }

    private long countAxes(List<EquipmentItem> equipment) {
        return equipment.stream()
                .filter(item -> item.getName().toLowerCase().contains("axe"))
                .count();
    }

    public Viking getRandomVikingAboveHeight(List<Viking> vikings, int minHeight) { //Случайного викинга ростом выше 180
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
                .anyMatch(item -> "Legendary".equalsIgnoreCase(item.getQuality()))) //Всех викингов с легендарным снаряжением
                .toList();
    }

    public List<Viking> getRedBeardedSortedByAge(List<Viking> vikings) { //сортированный по возрасту список рыжебородых викингов
        return vikings.stream()
                .filter(v -> v.getHairColor() == HairColor.Red && v.getBeardStyle() != BeardStyle.CLEAN_SHAVEN)
                .sorted(Comparator.comparingInt(Viking::getAge))
                .toList();
    }

    public int getMaxId(List<Viking> vikings) {
        int[] ids = vikings.stream()
                .mapToInt(v -> v.getId().intValue())
                .toArray();
        int max = -1;
        for (int id : ids) {
            if (id > max) {
                max = id;
            }
        }
        return max;
    }

    public int[] getEvenIds(List<Viking> vikings) {
        int[] ids = vikings.stream()
                .mapToInt(v -> v.getId().intValue())
                .toArray();
        java.util.ArrayList<Integer> evenList = new java.util.ArrayList<>();
        for (int id : ids) {
            if (id % 2 == 0) {
                evenList.add(id);
            }
        }
        int[] result = new int[evenList.size()];
        for (int i = 0; i < evenList.size(); i++) {
            result[i] = evenList.get(i);
        }
        return result;
    }
}