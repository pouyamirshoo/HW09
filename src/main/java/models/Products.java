package models;

import base.model.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Products extends BaseEntity<Integer> {
    String productName;
    float price;
    int number;
    SubBranch subBranch;

    public Products(int productId, String productName, float price, int number, SubBranch subBranch) {
        super(productId);
        this.productName = productName;
        this.price = price;
        this.number = number;
        this.subBranch = subBranch;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", subBranch=" + subBranch.getSubBranchName() +
                '}';
    }
}
