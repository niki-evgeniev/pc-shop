package com.example.pcproject.models.entity;

import com.example.pcproject.models.eunums.ComputerType;
import com.example.pcproject.models.eunums.TypeToUse;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "computer_type")
    @Enumerated(EnumType.STRING)
    private ComputerType computerType;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "year")
    private Integer year;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;
    @ManyToOne
    private Model model;

    @ManyToOne
    private User seller;

    @Column(name = "type_to_use")
    @Enumerated(EnumType.STRING)
    private TypeToUse typeToUse;

    public Product() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ComputerType getComputerType() {
        return computerType;
    }

    public void setComputerType(ComputerType computerType) {
        this.computerType = computerType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public TypeToUse getTypeToUse() {
        return typeToUse;
    }

    public void setTypeToUse(TypeToUse typeToUse) {
        this.typeToUse = typeToUse;
    }
}
