package ru.mephi.vikingdemo.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.Viking;
import ru.mephi.vikingdemo.repository.VikingRepository;

import java.util.List;

@Service
public class VikingService {
    
    @Autowired
    private VikingRepository vikingRepository;
    
    @Autowired
    private VikingFactory vikingFactory;
    
    private final List<Runnable> listeners = new ArrayList<>();
    public void addListener(Runnable listener) {
        listeners.add(listener);
    }
    private void notifyListeners() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }
    
    public List<Viking> findAll() {
        return vikingRepository.findAll();
    }
    
    public Viking createRandomViking() {
        Viking viking = vikingFactory.createRandomViking();
        Viking saved = vikingRepository.save(viking);
        notifyListeners();
        return saved;
    }
    
    public Viking save(Viking viking) {
        Viking saved = vikingRepository.save(viking);
        notifyListeners();
        return saved;
    }
    
    public void deleteById(Long id) {
        vikingRepository.deleteById(id);
        notifyListeners();
    }
    
    public Viking update(Viking viking) {
        Viking saved = vikingRepository.save(viking);
        notifyListeners();
        return saved;
    }
    
    public List<Viking> generateManyRandom(int count) {
        List<Viking> newVikings = java.util.stream.Stream
                .generate(() -> vikingFactory.createRandomViking())
                .limit(count)
                .collect(java.util.stream.Collectors.toList());
        
        List<Viking> saved = vikingRepository.saveAll(newVikings);
        notifyListeners();
        return saved;
    }
}