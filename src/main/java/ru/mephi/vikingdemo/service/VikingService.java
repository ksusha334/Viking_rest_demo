package ru.mephi.vikingdemo.service;

import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.gui.VikingTableModel;
import ru.mephi.vikingdemo.model.Viking;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class VikingService {
    
    private final CopyOnWriteArrayList<Viking> vikings = new CopyOnWriteArrayList<>();
    private final VikingFactory vikingFactory;
    private VikingTableModel tableModel;
    
    @Autowired
    public VikingService(VikingFactory vikingFactory) {
        this.vikingFactory = vikingFactory;
    }
    
    public void setTableModel(VikingTableModel tableModel) {
        this.tableModel = tableModel;
        tableModel.setVikings(vikings);
    }
    
    public List<Viking> findAll() {
        return List.copyOf(vikings);
    }
    
    public Viking createRandomViking() {
        Viking viking = vikingFactory.createRandomViking();
        vikings.add(viking);
        refreshGui();
        return viking;
    }
    
    public Viking save(Viking viking) {
        vikings.add(viking);
        refreshGui();
        return viking;
    }
    
    public void deleteById(int index) {
        if (index >= 0 && index < vikings.size()) {
            vikings.remove(index);
            refreshGui();
        }
    }
    
    public Viking update(int index, Viking newViking) {
        if (index >= 0 && index < vikings.size()) {
            vikings.set(index, newViking);
            refreshGui();
            return newViking;
        }
        throw new RuntimeException("Викинг не найден по индексу: " + index);
    }
    
    private void refreshGui() {
        if (tableModel != null) {
            tableModel.setVikings(vikings);
        }
    }
}