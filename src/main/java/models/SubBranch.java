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
public class SubBranch extends BaseEntity<Integer> {
    String subBranchName;
    Branch branch;

    public SubBranch(int subBranchId ,String subBranchName,Branch branch){
        super(subBranchId);
        this.subBranchName = subBranchName;
        this.branch = branch;
    }
}
