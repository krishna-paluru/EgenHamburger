package com.krishna.TexasHamburger.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "slotsAvailable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotsAvailable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long slotsId;
    private LocalDate date;
    private Long slotsAvailable;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="location_id",nullable = false)
    private Locations locations;

    @Override
    public String toString() {
        return "SlotsAvailable{" +
                "slotsId=" + slotsId +
                ", date=" + date +
                ", slotsAvailable=" + slotsAvailable +
                '}';
    }
}
