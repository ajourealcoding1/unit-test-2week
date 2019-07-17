package service;

import domain.ConvenienceStoreItem;
import repository.MockRepository;

import java.util.List;

public class MockService {
    private final MockRepository mockRepository;

    public MockService(MockRepository mockRepository) {
        this.mockRepository = mockRepository;
    }

    public List<ConvenienceStoreItem> findAllConvinientStore() {
        return mockRepository.findAll();
    }

    public ConvenienceStoreItem findByName(String name) {
        ConvenienceStoreItem convenienceStoreItem = mockRepository.findByName(name);
        return convenienceStoreItem;
    }

    public ConvenienceStoreItem updateCategoryByName(String name, String category) {
        ConvenienceStoreItem convenienceStoreItem = findByName(name);
        convenienceStoreItem.setName(category);

        return convenienceStoreItem;
    }

    public void addConvenienceStoreItem(ConvenienceStoreItem convenienceStoreItem){
        ConvenienceStoreItem addConvenienceStoreItem = new ConvenienceStoreItem(convenienceStoreItem.getName(), convenienceStoreItem.getCategory(), convenienceStoreItem.getPrice());
        mockRepository.addConvenienceStoreItem(addConvenienceStoreItem);
        return;
    }
}
