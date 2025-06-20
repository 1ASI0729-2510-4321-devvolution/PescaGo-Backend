package pe.upc.pescagobackend.carrier.domain.model.commands;

public record CreateCarrierCommand(
        Long userId,
        String name,
        String description
) {
    public CreateCarrierCommand {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
    }

}
