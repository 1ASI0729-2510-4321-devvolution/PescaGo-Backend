package pe.upc.pescagobackend.hiredService.application.internal.commandservices;

import org.springframework.stereotype.Service;

import pe.upc.pescagobackend.hiredService.domain.model.aggregates.HiredService;
import pe.upc.pescagobackend.hiredService.domain.model.commands.CreateHiredServiceCommand;
import pe.upc.pescagobackend.hiredService.domain.model.commands.DeleteHiredServiceCommand;
import pe.upc.pescagobackend.hiredService.domain.model.commands.UpdateHiredServiceCommand;
import pe.upc.pescagobackend.hiredService.domain.services.HiredServiceCommandService;
import pe.upc.pescagobackend.hiredService.infrastructure.persistence.jpa.repositories.HiredServiceRepository;

@Service
public class HIredServiceCommandServiceImpl implements HiredServiceCommandService {

    private final HiredServiceRepository hiredServiceRepository;

    public HIredServiceCommandServiceImpl(HiredServiceRepository hiredServiceRepository) {
        this.hiredServiceRepository = hiredServiceRepository;
    }

    @Override
    public void handle(CreateHiredServiceCommand command) {
        var hiredService = new HiredService(command);
        try {
            hiredServiceRepository.save(hiredService);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving the hired service: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void handle(DeleteHiredServiceCommand command) {
        if (!hiredServiceRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Hired service with id %s not found".formatted(command.id()));
        }
        try {
            hiredServiceRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting hired service: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void handle(UpdateHiredServiceCommand command) {
        if (!hiredServiceRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Hired service with id %s not found".formatted(command.id()));
        }
        var hiredService = new HiredService(command);
        try {
            hiredServiceRepository.save(hiredService);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating hired service: %s".formatted(e.getMessage()));
        }
    }

}
