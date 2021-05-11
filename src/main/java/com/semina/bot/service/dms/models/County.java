package com.semina.bot.service.dms.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "county_table")
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countyId;

    @Column(columnDefinition = "VARCHAR(96)")
    private String countyName;

}
