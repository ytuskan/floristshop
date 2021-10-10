package com.example.finalreview.entity;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedQuery(name = "Delivery.findByName",
        query = "Select d From Delivery d Where d.name=:name")

@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;
    @Nationalized
    private String name;
    @Column(length = 500, name = "address_full")
    private String address;
    private LocalDateTime deliveryTime;
    @Type(type = "yes_no")
    private boolean completed;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Plant> plants;

    public Delivery() {
    }

    public Delivery(String name, String address, LocalDateTime deliveryTime) {
        this.name = name;
        this.address = address;
        this.deliveryTime = deliveryTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryTime;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryTime = deliveryDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
