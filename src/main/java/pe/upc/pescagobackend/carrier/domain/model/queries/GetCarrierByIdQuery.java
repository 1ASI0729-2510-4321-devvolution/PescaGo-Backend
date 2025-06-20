package pe.upc.pescagobackend.carrier.domain.model.queries;

public record GetCarrierByIdQuery(
    Long carrierId
) {
    public GetCarrierByIdQuery {
        if (carrierId == null || carrierId <= 0) {
            throw new IllegalArgumentException("Carrier ID must be a positive number.");
        }
    }
}
