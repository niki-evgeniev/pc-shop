package com.example.pcproject.models.entity;

import com.example.pcproject.models.eunums.ComputerType;
import com.example.pcproject.models.eunums.TracesOfUse;
import com.example.pcproject.models.eunums.TypeToUse;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Lob
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "computer_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ComputerType computerType;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "year")
    private Integer year;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @Column(name = "is_sold")
    private Boolean isSold = false;

    @ManyToOne
    private Model model;

    @ManyToOne
    private User seller;

    @Column(name = "type_to_use", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeToUse typeToUse;

    @Column(name = "traces_to_use", nullable = false)
    @Enumerated(EnumType.STRING)
    private TracesOfUse tracesOfUse;

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

    public TracesOfUse getTracesOfUse() {
        return tracesOfUse;
    }

    public void setTracesOfUse(TracesOfUse tracesOfUse) {
        this.tracesOfUse = tracesOfUse;
    }

    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        isSold = sold;
    }
}
