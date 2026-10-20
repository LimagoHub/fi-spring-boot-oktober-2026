package de.fi.webapp.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_schweine")
public class SchweinEntity {

    @Id
    @Column(nullable = false, unique = true)
    private UUID id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private int gewicht;

}
