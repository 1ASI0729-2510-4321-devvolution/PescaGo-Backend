package pe.upc.pescagobackend.iam.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pescagobackend.iam.domain.model.commands.SignInCommand;
import pe.upc.pescagobackend.iam.domain.model.commands.SignUpCommand;
import pe.upc.pescagobackend.iam.domain.model.entities.Role;
import pe.upc.pescagobackend.iam.domain.model.valueobjects.Roles;
import pe.upc.pescagobackend.iam.domain.services.UserCommandService;
import pe.upc.pescagobackend.iam.interfaces.rest.resources.SignInResource;
import pe.upc.pescagobackend.iam.interfaces.rest.resources.SignUpResource;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final UserCommandService userCommandService;

    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpResource resource) {
        // Validate userType
        if (resource.userType() == null || (!resource.userType().equalsIgnoreCase("ENTREPRENEUR") && !resource.userType().equalsIgnoreCase("TRANSPORTER"))) {
            return ResponseEntity.badRequest().body("Invalid userType. Must be ENTREPRENEUR or TRANSPORTER.");
        }
        // Validate required fields
        if (resource.companyName() == null || resource.companyName().isBlank() || resource.email() == null || resource.email().isBlank() || resource.password() == null || resource.password().isBlank()) {
            return ResponseEntity.badRequest().body("Missing required fields.");
        }
        List<Role> roles;
        if (resource.userType().equalsIgnoreCase("ENTREPRENEUR")) {
            roles = Collections.singletonList(Role.toRoleFromName(Roles.ROLE_ENTREPRENEUR.name()));
        } else {
            // TRANSPORTER
            if (resource.description() == null || resource.description().isBlank() || resource.deliveryDistricts() == null || resource.deliveryDistricts().isEmpty()) {
                return ResponseEntity.badRequest().body("Missing required fields for Transporter: description and deliveryDistricts.");
            }
            roles = Collections.singletonList(Role.toRoleFromName(Roles.ROLE_TRANSPORTER.name()));
        }
        SignUpCommand command = new SignUpCommand(
                resource.companyName(),
                resource.email(),
                resource.password(),
                roles
        );
        try {
            var userOpt = userCommandService.handle(command);
            if (userOpt.isEmpty()) {
                return ResponseEntity.status(500).body("User registration failed.");
            }
            var user = userOpt.get();
            return ResponseEntity.ok().body("User registered: " + user.getEmail() + ", roles: " + user.getRoles().stream().map(Role::getStringName).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInResource resource) {
        if (resource.email() == null || resource.email().isBlank() || resource.password() == null || resource.password().isBlank()) {
            return ResponseEntity.badRequest().body("Missing email or password.");
        }
        SignInCommand command = new SignInCommand(resource.email(), resource.password());
        try {
            Optional<org.apache.commons.lang3.tuple.ImmutablePair<pe.upc.pescagobackend.iam.domain.model.aggregates.User, String>> result = userCommandService.handle(command);
            if (result.isEmpty()) {
                return ResponseEntity.status(401).body("Invalid credentials.");
            }
            var pair = result.get();
            var user = pair.getLeft();
            var token = pair.getRight();
            return ResponseEntity.ok().body("User signed in: " + user.getEmail() + ", roles: " + user.getRoles().stream().map(Role::getStringName).collect(Collectors.toList()) + ", token: " + token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
