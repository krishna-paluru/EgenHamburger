package com.krishna.TexasHamburger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Primary
public class Locations implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long locationId;
        private String locationName;
        public String street;
        public String city;
        public String state;
        public String zipcode;
//        private int slotsAvailable;

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "locations", cascade = CascadeType.PERSIST)
        @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
        @Fetch(FetchMode.SUBSELECT)
        @JsonIgnore
        @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
        Set<LocationMenu> locationMenus = new HashSet<>();

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "locations", cascade = CascadeType.PERSIST)
        @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
        @Fetch(FetchMode.SUBSELECT)
        @JsonIgnore
        @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
        Set<OpenHour> OpenHour = new HashSet<>();

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "locations", cascade = CascadeType.PERSIST)
        @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
        @Fetch(FetchMode.SUBSELECT)
        @JsonIgnore
        @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
        Set<SlotsAvailable> slotsAvailables = new HashSet<>();

//        @Override
//        public int hashCode() {
//                final int prime = 31;
//                int result = 1;
//                result = prime * result + ((locationMenus == null) ? 0 : locationName .hashCode());
//                result = prime * result + ((locationMenus == null) ? 0 : street.hashCode());
//                result = prime * result + ((locationMenus == null) ? 0 : city.hashCode());
//                result = prime * result + ((locationMenus == null) ? 0 : state.hashCode());
//                result = prime * result + ((locationMenus == null) ? 0 : zipcode.hashCode());
//                return result;
//        }
        @Override
        public String toString() {
                return super.toString();
        }
}

