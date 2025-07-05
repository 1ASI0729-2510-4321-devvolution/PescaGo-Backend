package pe.upc.pescagobackend.carrier.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.upc.pescagobackend.carrier.domain.model.aggregates.Carrier;
import pe.upc.pescagobackend.carrier.domain.model.queries.GetCarrierByIdQuery;
import pe.upc.pescagobackend.carrier.domain.services.CarrierQueryService;
import pe.upc.pescagobackend.carrier.infrastructure.persistence.jpa.repositories.CarrierRepository;

import java.util.Optional;

@Service
public class CarrierQueryServiceImpl implements CarrierQueryService {
    private final CarrierRepository carrierRepository;

    public CarrierQueryServiceImpl(CarrierRepository carrierRepository) {
        this.carrierRepository = carrierRepository;
    }

    @Override
    public Optional<Carrier> handle(GetCarrierByIdQuery query){
        return carrierRepository.findById(query.userId());
    }


}
