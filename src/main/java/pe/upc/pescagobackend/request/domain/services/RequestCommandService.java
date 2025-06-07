package pe.upc.pescagobackend.request.domain.services;

import pe.upc.pescagobackend.request.domain.model.commands.CreateRequestCommand;
import pe.upc.pescagobackend.request.domain.model.commands.DeleteRequestCommand;

public interface RequestCommandService {
    void handle(CreateRequestCommand command);
    void handle(DeleteRequestCommand command);
}
