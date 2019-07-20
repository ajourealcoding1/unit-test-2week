package service;

import domain.ConvenienceStoreItem;
import repository.MockRepository;

import java.util.List;

public class MockService {
    private final MockRepository mockRepository;

    public MockService(MockRepository mockRepository) {
        this.mockRepository = mockRepository;
    }

    public List<ConvenienceStoreItem> findAllConvinienceStoreItem() {
        return mockRepository.findAll();
    }

    public ConvenienceStoreItem findByName(String name) {
        ConvenienceStoreItem convenienceStoreItem = mockRepository.findByName(name);
        return convenienceStoreItem;
    }

    public int addTwoConvenienceStoreItemPricesByName (String name1,String name2){
        ConvenienceStoreItem convenienceStoreItem1 = mockRepository.findByName(name1);
        ConvenienceStoreItem convenienceStoreItem2 = mockRepository.findByName(name2);

        return convenienceStoreItem1.getPrice()+convenienceStoreItem2.getPrice();
    }

    public ConvenienceStoreItem updateCategoryByName(String name, String category) {
        ConvenienceStoreItem convenienceStoreItem = findByName(name);
        convenienceStoreItem.setCategory(category);

        return convenienceStoreItem;
    }

    public ConvenienceStoreItem updatePriceByName(String name, int price) {
        ConvenienceStoreItem convenienceStoreItem = findByName(name);
        convenienceStoreItem.setPrice(price);

        return convenienceStoreItem;
    }

    //새로운 상품을 등록할때, 한꺼번에 이름,카테고리,가격 다 입력하기
    public void addConvenienceStoreItem(ConvenienceStoreItem convenienceStoreItem){
        ConvenienceStoreItem addConvenienceStoreItem
                = new ConvenienceStoreItem(convenienceStoreItem.getName(), convenienceStoreItem.getCategory(), convenienceStoreItem.getPrice());
        return;
    }


}
