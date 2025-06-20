package pe.upc.pescagobackend.entrepreneur.domain.model.queries;

public record GetEntreprenuerByIdQuery(
    Long entreprenuerId
) {
    public GetEntreprenuerByIdQuery {
        if (entreprenuerId == null || entreprenuerId <= 0) {
            throw new IllegalArgumentException("Entreprenuer ID must be a positive number.");
        }
    }
}
