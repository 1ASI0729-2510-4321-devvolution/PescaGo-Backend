package pe.upc.pescagobackend.iam.interfaces.rest.resources;

public record SignInResource(
    String email,
    String password
) {}
