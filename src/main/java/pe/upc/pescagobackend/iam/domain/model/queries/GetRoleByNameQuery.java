package pe.upc.pescagobackend.iam.domain.model.queries;

import pe.upc.pescagobackend.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
