package pe.upc.pescagobackend.request.domain.services;

import pe.upc.pescagobackend.request.domain.model.aggregates.Request;
import pe.upc.pescagobackend.request.domain.model.queries.GetRequestByIdQuery;

import java.util.Optional;

public interface RequestQueryService {
    Optional<Request> handle(GetRequestByIdQuery query);
}
