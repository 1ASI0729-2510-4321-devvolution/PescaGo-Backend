package pe.upc.pescagobackend.request.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pescagobackend.request.domain.model.commands.DeleteRequestCommand;
import pe.upc.pescagobackend.request.domain.model.queries.GetRequestByIdQuery;
import pe.upc.pescagobackend.request.domain.services.RequestCommandService;
import pe.upc.pescagobackend.request.domain.services.RequestQueryService;
import pe.upc.pescagobackend.request.interfaces.rest.resources.CreateRequestResource;
import pe.upc.pescagobackend.request.interfaces.rest.resources.RequestResource;
import pe.upc.pescagobackend.request.interfaces.rest.transform.CreateRequestCommandFromResourceAssembler;
import pe.upc.pescagobackend.request.interfaces.rest.transform.RequestResourceFromEntityAssembler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/requests",produces = APPLICATION_JSON_VALUE)
@Tag(name = "Request", description = "Available Request Endpoints")
public class RequestController {
    private final RequestCommandService requestCommandService;
    private final RequestQueryService requestQueryService;

    public RequestController(RequestCommandService requestCommandService, RequestQueryService requestQueryService) {
        this.requestCommandService = requestCommandService;
        this.requestQueryService = requestQueryService;
    }


    @PostMapping
    @Operation(summary = "Create a request", description = "Create a request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Request created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public void createRequest(@RequestBody CreateRequestResource resource) {
        var createRequestCommand = CreateRequestCommandFromResourceAssembler.toCommandFromResource(resource);
        requestCommandService.handle(createRequestCommand);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get request by id", description = "Get request by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request found"),
            @ApiResponse(responseCode = "404", description = "Request not found")
    })
    public ResponseEntity<RequestResource> getRequestById(@PathVariable Long id ) {
        var getRequestByIdQuery = new GetRequestByIdQuery(id);
        var request = requestQueryService.handle(getRequestByIdQuery);
        if (request.isEmpty()) return ResponseEntity.notFound().build();
        var requestEntity = request.get();
        var requestResource = RequestResourceFromEntityAssembler.toResourceFromEntity(requestEntity);
        return ResponseEntity.ok(requestResource);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete request by id", description = "Delete request by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Request deleted"),
            @ApiResponse(responseCode = "404", description = "Request not found")
    })
    public ResponseEntity<?> deleteRequestById(@PathVariable Long id) {
        var deleteRequestCommand = new DeleteRequestCommand(id);
        requestCommandService.handle(deleteRequestCommand);
        return ResponseEntity.ok("Request deleted successfully");
    }
}
