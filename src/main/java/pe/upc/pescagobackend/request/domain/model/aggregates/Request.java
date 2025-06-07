package pe.upc.pescagobackend.request.domain.model.aggregates;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import pe.upc.pescagobackend.request.domain.model.commands.CreateRequestCommand;
import pe.upc.pescagobackend.shared.domain.model.entities.AuditableModel;

import java.time.LocalDateTime;

@Getter
@Entity
public class Request extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    private Long entrepreneurId;
    @Setter
    private String entrepreneurName;
    @Setter
    private Long carrierId;
    @Setter
    private String carrierName;
    @Setter
    private String packageDescription;
    @Setter
    private Integer quantity;
    @Setter
    private Double weightTotal;
    @Setter
    private Dimensions dimensions;
    @Setter
    private String pickupLocation;
    @Setter
    private String deliveryLocation;
    @Setter
    private LocalDateTime pickupDateTime;
    @Setter
    private Double price;
    @Setter
    private String status;

    public Request() {
        this.entrepreneurId = 0L;
        this.entrepreneurName = "";
        this.carrierId = 0L;
        this.carrierName = "";
        this.packageDescription = "";
        this.quantity = 0;
        this.weightTotal = 0.0;
        this.dimensions = new Dimensions();
        this.pickupLocation = "";
        this.deliveryLocation = "";
        this.pickupDateTime = LocalDateTime.now();
        this.price = 0.0;
        this.status = "PENDING";
    }

    public Request(CreateRequestCommand command){
        super();
        this.entrepreneurId = command.entrepreneurId();
        this.entrepreneurName = command.entrepreneurName();
        this.carrierId = command.carrierId();
        this.carrierName = command.carrierName();
        this.packageDescription = command.packageDescription();
        this.quantity = command.quantity();
        this.weightTotal = command.weightTotal();
        this.dimensions = command.dimensions();
        this.pickupLocation = command.pickupLocation();
        this.deliveryLocation = command.deliveryLocation();
        this.pickupDateTime = command.pickupDateTime();
        this.price = command.price();
        this.status = command.status();
    }

}