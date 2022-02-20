package co.com.ingeneo.prueba.entities;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "deliveries")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {
    
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    UUID id;

    @Column
    DeliveryType type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    Product product;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_measure_id", referencedColumnName = "id")
    UnitMeasure unitMeasure;

    @Column
    Long quantity;

    @Column(name = "delivery_date")
    LocalDateTime deliveryDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    Warehouse warehouse;

    @Column
    BigDecimal price;

    @Column
    BigDecimal discount;

    @Column
    BigDecimal total;

    @Column(name = "vehicle_number")
    String vehicleNumber;

    @Column(name = "tracking_number")
    String trackingNumber;

    @Column
    Boolean enabled;

    @Column(name = "created_at")
    @CreatedDate
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    LocalDateTime updatedAt;
}

