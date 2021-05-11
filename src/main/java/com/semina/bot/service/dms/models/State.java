package com.semina.bot.service.dms.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "state_table")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stateId;

    @Column(columnDefinition = "VARCHAR(96)")
    private String stateName;

}
