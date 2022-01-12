package com.krishna.TexasHamburger.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Component
@Data
@NoArgsConstructor
@Table(name="ExecutionTime")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    private Long time;
    private Long timeStamp;
}
