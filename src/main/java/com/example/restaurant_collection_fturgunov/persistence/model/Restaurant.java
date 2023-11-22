package com.example.restaurant_collection_fturgunov.persistence.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;
    private String name;
    @Builder.Default
    private String city = "";
    @Builder.Default
    @Column(name = "estimated_cost")
    private Integer estimatedCost = 0;
    @Builder.Default
    @Column(name = "average_rating")
    private String averageRating = "0";
    @Builder.Default
    private Integer votes = 0;
}