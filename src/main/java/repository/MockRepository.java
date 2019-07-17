package repository;

import domain.ConvenienceStoreItem;

import java.util.List;

public interface MockRepository {
    List<ConvenienceStoreItem> findAll();

    ConvenienceStoreItem findByName(String name);

    ConvenienceStoreItem updateCategoryByName(String name, String category);

    void addConvenientStore(ConvenienceStoreItem convenienceStoreItem);
}
