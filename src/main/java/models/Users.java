package models;

import base.model.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users extends BaseEntity<Integer> {
    String fullName;
    String username;
    String password;
    String email;

    public Users(Integer id, String fullName, String username, String password, String email) {
        super(id);
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}