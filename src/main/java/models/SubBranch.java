package models;

import base.model.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubBranch extends BaseEntity<Integer> {
    String subBranchName;
    Branch branch;

    public SubBranch(int subBranchId ,String subBranchName,Branch branch){
        super(subBranchId);
        this.subBranchName = subBranchName;
        this.branch = branch;
    }
    public SubBranch(String subBranchName){
        this.subBranchName = subBranchName;
    }

    @Override
    public String toString() {
        return "SubBranch{" +
                "subBranchName='" + subBranchName + '\'' +
                ", branch=" + branch.getBranchName() +
                '}';
    }
}
