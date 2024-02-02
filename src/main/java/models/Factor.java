package models;

import base.model.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Factor extends BaseEntity<Integer> {

    Products product;

    public Factor (int factorId , Products product){
        super(factorId);
        this.product = product;
    }
}
