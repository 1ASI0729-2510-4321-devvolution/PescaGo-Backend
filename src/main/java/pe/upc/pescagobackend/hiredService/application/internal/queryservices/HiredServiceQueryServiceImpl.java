package pe.upc.pescagobackend.hiredService.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.upc.pescagobackend.hiredService.domain.model.aggregates.HiredService;
import pe.upc.pescagobackend.hiredService.domain.model.queries.GetHiredServiceByIdQuery;
import pe.upc.pescagobackend.hiredService.domain.services.HiredServiceQueryService;
import pe.upc.pescagobackend.hiredService.infrastructure.persistence.jpa.repositories.HiredServiceRepository;

import java.util.Optional;

@Service
public class HiredServiceQueryServiceImpl implements HiredServiceQueryService {
    private final HiredServiceRepository hiredServiceRepository;

    public HiredServiceQueryServiceImpl(HiredServiceRepository hiredServiceRepository) {
        this.hiredServiceRepository = hiredServiceRepository;
    }

    @Override
    public Optional<HiredService> handle(GetHiredServiceByIdQuery query){
        return hiredServiceRepository.findById(query.id());
    }
}
