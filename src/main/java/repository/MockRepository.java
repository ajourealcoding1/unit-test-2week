package repository;

import domain.ConvenienceStoreItem;

import java.util.List;

public class MockRepository {

    List<ConvenienceStoreItem> convenienceStoreItems = null;

    public List<ConvenienceStoreItem> findAll(){
        return convenienceStoreItems;
    }

    public ConvenienceStoreItem findByName(String name){
        for(int i=0; i < convenienceStoreItems.size(); i++) {
            if (convenienceStoreItems.get(i).getName().equals(name))
                return convenienceStoreItems.get(i);
        }
        return null;
    }

    public ConvenienceStoreItem updateCategoryByName(String name, String category){

        return null;
    }

    public ConvenienceStoreItem updatePriceByName(String name, int price){
        return null;
    }

    public void addConvenienceStoreItem(ConvenienceStoreItem convenienceStoreItem){}
}
