package pe.upc.pescagobackend.carrier.domain.services;

import pe.upc.pescagobackend.carrier.domain.model.aggregates.Carrier;
import pe.upc.pescagobackend.carrier.domain.model.queries.GetCarrierByIdQuery;

import java.util.Optional;

public interface CarrierQueryService {
    Optional<Carrier> handle(GetCarrierByIdQuery query);
}
