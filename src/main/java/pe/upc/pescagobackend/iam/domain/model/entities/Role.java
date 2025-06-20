package pe.upc.pescagobackend.iam.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import pe.upc.pescagobackend.iam.domain.model.valueobjects.Roles;

import java.util.List;

@Getter
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, length = 20)
    private Roles name;

    public Role() {}

    public Role(Roles name){
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static Role toRoleFromName(String name) {
        return new Role(Roles.valueOf(name));
    }

    public static List<Role> validateRoleSet(List<Role> roles) {
        return roles;
    }

}
