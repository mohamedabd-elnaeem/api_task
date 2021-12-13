package com.maiia.pro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    /*
    * @ManyToOne
	@JoinColumn(name = "practitioner_Id", nullable = false)
    * private Practitioner practitionerId;
    * to set the relation between this two entity in the ORM layer
    * */
    private Integer practitionerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
