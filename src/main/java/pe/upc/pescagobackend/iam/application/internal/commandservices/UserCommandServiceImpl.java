package pe.upc.pescagobackend.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import pe.upc.pescagobackend.iam.application.internal.outboundservices.hashing.HashingService;
import pe.upc.pescagobackend.iam.application.internal.outboundservices.tokens.TokenService;
import pe.upc.pescagobackend.iam.domain.model.aggregates.User;
import pe.upc.pescagobackend.iam.domain.model.commands.SignInCommand;
import pe.upc.pescagobackend.iam.domain.model.commands.SignUpCommand;
import pe.upc.pescagobackend.iam.domain.model.valueobjects.Roles;
import pe.upc.pescagobackend.iam.domain.services.UserCommandService;
import pe.upc.pescagobackend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import pe.upc.pescagobackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.email()))
            throw new RuntimeException("Username already exists");
        var roles = command.roles();
        if (roles.isEmpty()) {
            var role = roleRepository.findByName(Roles.ROLE_ENTREPRENEUR);
            roles.add(role.get());
        }
        roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName()).orElseThrow(() -> new RuntimeException("Role not found"))).toList();
        var user = new User(command.email(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return userRepository.findByUsername(command.email());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.email());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var currentUser = user.get();
        var token = tokenService.generateToken(currentUser.getEmail());
            return Optional.of(ImmutablePair.of(currentUser, token));
    }
}
