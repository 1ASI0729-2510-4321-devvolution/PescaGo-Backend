package pe.upc.pescagobackend.hiredService.domain.services;

import pe.upc.pescagobackend.hiredService.domain.model.aggregates.HiredService;
import pe.upc.pescagobackend.hiredService.domain.model.queries.GetHiredServiceByIdQuery;

import java.util.Optional;

public interface HiredServiceQueryService {
    Optional<HiredService> handle(GetHiredServiceByIdQuery query);
}
