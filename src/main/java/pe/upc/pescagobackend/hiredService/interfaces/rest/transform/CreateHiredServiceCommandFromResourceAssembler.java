package pe.upc.pescagobackend.hiredService.interfaces.rest.transform;

import pe.upc.pescagobackend.hiredService.domain.model.commands.CreateHiredServiceCommand;
import pe.upc.pescagobackend.hiredService.interfaces.rest.resources.HiredServiceResource;

public class CreateHiredServiceCommandFromResourceAssembler {
    public static CreateHiredServiceCommand toCommandFromResource(HiredServiceResource resource) {
        return new CreateHiredServiceCommand(
                resource.requestId(),
                resource.entrepreneurId(),
                resource.entrepreneurName(),
                resource.carrierId(),
                resource.carrierName(),
                resource.packageDescription(),
                resource.pickupDateTime(),
                resource.carrierData(),
                resource.paymentMethod(),
                resource.status()
        );
    }
}
