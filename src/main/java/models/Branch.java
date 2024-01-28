package models;

import base.model.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Branch extends BaseEntity<Integer> {
    String branchName;

    public Branch (int branchId , String branchName){
        super(branchId);
        this.branchName = branchName;
    }
}
