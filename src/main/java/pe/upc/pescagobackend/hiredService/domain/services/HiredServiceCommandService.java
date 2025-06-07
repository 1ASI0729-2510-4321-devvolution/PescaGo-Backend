package pe.upc.pescagobackend.hiredService.domain.services;

import pe.upc.pescagobackend.hiredService.domain.model.commands.CreateHiredServiceCommand;
import pe.upc.pescagobackend.hiredService.domain.model.commands.DeleteHiredServiceCommand;
import pe.upc.pescagobackend.hiredService.domain.model.commands.UpdateHiredServiceCommand;

public interface HiredServiceCommandService {
    void handle(CreateHiredServiceCommand command);
    void handle(DeleteHiredServiceCommand command);
    void handle(UpdateHiredServiceCommand command);
}
