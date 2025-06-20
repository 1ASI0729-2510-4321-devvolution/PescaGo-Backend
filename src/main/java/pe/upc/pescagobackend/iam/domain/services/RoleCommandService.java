package pe.upc.pescagobackend.iam.domain.services;

import pe.upc.pescagobackend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
