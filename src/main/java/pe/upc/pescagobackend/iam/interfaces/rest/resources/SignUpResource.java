package pe.upc.pescagobackend.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(
    String companyName,
    String description, // Only for Transporter
    List<String> deliveryDistricts, // Only for Transporter
    String email,
    String password,
    String userType // ENTREPRENEUR or TRANSPORTER
) {}
