package ru.ufanet.coffeeshoporderingsystem.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "employees")
@EntityListeners(AuditingEntityListener.class)
public class EmployeeEntity extends BaseEntity {

    private UUID id;

    private String firstname;

    private String lastname;

}
