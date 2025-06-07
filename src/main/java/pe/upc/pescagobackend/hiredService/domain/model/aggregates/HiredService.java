package pe.upc.pescagobackend.hiredService.domain.model.aggregates;

import pe.upc.pescagobackend.hiredService.domain.model.commands.CreateHiredServiceCommand;
import pe.upc.pescagobackend.hiredService.domain.model.commands.UpdateHiredServiceCommand;
import pe.upc.pescagobackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
public class HiredService extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    private Long requestId;
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
    private LocalDateTime pickupDateTime;

    @Setter
    @Embedded
    private CarrierData carrierData;

    @Setter
    private String status;
    @Setter
    private String paymentMethod;

    public HiredService() {
        this.requestId = 0L;
        this.entrepreneurId = 0L;
        this.entrepreneurName = "";
        this.carrierId = 0L;
        this.carrierName = "";
        this.packageDescription = "";
        this.pickupDateTime = LocalDateTime.now();
        this.carrierData = new CarrierData();
        this.status = "PENDING";
        this.paymentMethod = "";
    }

    public HiredService(CreateHiredServiceCommand command) {
        super();
        this.requestId = command.requestId();
        this.entrepreneurId = command.entrepreneurId();
        this.entrepreneurName = command.entrepreneurName();
        this.carrierId = command.carrierId();
        this.carrierName = command.carrierName();
        this.packageDescription = command.packageDescription();
        this.pickupDateTime = command.pickupDateTime();
        this.carrierData = command.carrierData();
        this.status = command.status();
        this.paymentMethod = command.paymentMethod();
    }

    public HiredService(UpdateHiredServiceCommand command) {
        super();
        this.requestId = command.requestId();
        this.entrepreneurId = command.entrepreneurId();
        this.entrepreneurName = command.entrepreneurName();
        this.carrierId = command.carrierId();
        this.carrierName = command.carrierName();
        this.packageDescription = command.packageDescription();
        this.pickupDateTime = command.pickupDateTime();
        this.carrierData = command.carrierData();
        this.status = command.status();
        this.paymentMethod = command.paymentMethod();
    }
}