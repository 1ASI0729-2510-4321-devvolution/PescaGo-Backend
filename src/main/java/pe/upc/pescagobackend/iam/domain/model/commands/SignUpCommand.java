package pe.upc.pescagobackend.iam.domain.model.commands;

import pe.upc.pescagobackend.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String companyName, String email, String password, List<Role> roles) {
}
