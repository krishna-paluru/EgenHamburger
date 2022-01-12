package com.krishna.TexasHamburger.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name="openHour")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OpenHour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long openHourId;
    private String day;
    private String openTime;
    private String closeTime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="location_Id")
    private Locations locations;


}
