package pe.upc.pescagobackend.iam.application.internal.outboundservices.tokens;

public interface TokenService {
    String generateToken(String email);

    boolean validateToken(String token);

    String getUsernameFromToken(String token);
}
