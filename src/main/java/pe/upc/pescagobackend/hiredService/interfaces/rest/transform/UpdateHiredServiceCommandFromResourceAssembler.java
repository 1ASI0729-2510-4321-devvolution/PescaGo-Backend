package pe.upc.pescagobackend.hiredService.interfaces.rest.transform;

import pe.upc.pescagobackend.hiredService.domain.model.commands.UpdateHiredServiceCommand;
import pe.upc.pescagobackend.hiredService.interfaces.rest.resources.HiredServiceResource;

public class UpdateHiredServiceCommandFromResourceAssembler {
    public static UpdateHiredServiceCommand toCommandFromResource(Long id, HiredServiceResource resource) {
        return new UpdateHiredServiceCommand(
                id,
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
