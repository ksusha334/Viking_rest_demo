package ru.mephi.vikingdemo.gui;

import ru.mephi.vikingdemo.model.EquipmentItem;
import ru.mephi.vikingdemo.model.Viking;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VikingTableModel extends AbstractTableModel {

    private final String[] columns = {"Name", "Age", "Height (cm)", "Hair color", "Beard style", "Equipment"};
    private final List<Viking> data = new ArrayList<>();

    public void addViking(Viking viking) {
        int row = data.size();
        data.add(viking);
        fireTableRowsInserted(row, row);
    }

    public void setVikings(List<Viking> newVikings) {
        System.out.println("setVikings: received " + (newVikings != null ? newVikings.size() : "null") + " vikings");
        data.clear();
        if (newVikings != null) {
            data.addAll(newVikings);
        }
        // КРИТИЧЕСКИ ВАЖНО — уведомляем таблицу, что данные изменились
        fireTableDataChanged();
        System.out.println("setVikings: data now has " + data.size() + " rows, fireTableDataChanged called");
    }

    @Override
    public int getRowCount() {
        int size = data.size();
        System.out.println("getRowCount called: " + size);
        return size;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Viking viking = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> viking.getName();
            case 1 -> viking.getAge();
            case 2 -> viking.getHeightCm();
            case 3 -> viking.getHairColor();
            case 4 -> viking.getBeardStyle();
            case 5 -> formatEquipment(viking.getEquipment());
            default -> "";
        };
    }

    private String formatEquipment(List<EquipmentItem> equipment) {
        return equipment.stream()
                .map(item -> item.getName() + " [" + item.getQuality() + "]")
                .collect(Collectors.joining(", "));
    }
}