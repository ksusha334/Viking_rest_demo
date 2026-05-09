package ru.mephi.vikingdemo.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.gui.VikingTableModel;
import ru.mephi.vikingdemo.model.Viking;
import ru.mephi.vikingdemo.repository.VikingRepository;

import java.util.List;

@Service
public class VikingService {
    
    @Autowired
    private VikingRepository vikingRepository;
    
    @Autowired
    private VikingFactory vikingFactory;
    
    private VikingTableModel tableModel;
    
    public void setTableModel(VikingTableModel tableModel) {
        this.tableModel = tableModel;
        refreshGui();
    }
    
    public List<Viking> findAll() {
        List<Viking> all = vikingRepository.findAll();
        System.out.println("findAll returned " + (all != null ? all.size() : "null") + " vikings");
        return all != null ? all : new ArrayList<>();
    }
    
    public Viking createRandomViking() {
        Viking viking = vikingFactory.createRandomViking();
        Viking saved = vikingRepository.save(viking);
        refreshGui();
        return saved;
    }
    
    public Viking save(Viking viking) {
        Viking saved = vikingRepository.save(viking);
        refreshGui();
        return saved;
    }
    
    public void deleteById(Long id) {
        vikingRepository.deleteById(id);
        refreshGui();
    }
    
    public Viking update(Viking viking) {
        Viking saved = vikingRepository.save(viking);
        refreshGui();
        return saved;
    }
    
    public List<Viking> generateManyRandom(int count) {
        List<Viking> newVikings = new java.util.ArrayList<>();
        for (int i = 0; i < count; i++) {
            newVikings.add(vikingFactory.createRandomViking());
        }
        List<Viking> saved = vikingRepository.saveAll(newVikings);
        refreshGui();
        return saved;
    }
    
    private void refreshGui() {
        if (tableModel != null) {
            tableModel.setVikings(vikingRepository.findAll());
        }
    }
}