package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ConvenienceStoreItem {
    private String name;
    private String category;
    private int price;
}
