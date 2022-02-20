package co.com.ingeneo.prueba.entities;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "warehouses")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {
    
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    UUID id;

    @Column
    String name;

    @Column
    String address;

    @Column
    String phone;

    @Column
    WarehouseType type;

    @Column
    Boolean enabled;

    @Column(name = "created_at")
    @CreatedDate
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    LocalDateTime updatedAt;

}
