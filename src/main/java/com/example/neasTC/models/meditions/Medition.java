package com.example.neasTC.models.meditions;

import com.example.neasTC.enums.meditions.MeditionStatus;

import java.math.BigDecimal;
import java.time.Instant;

public class Medition {
    private String id;

    private String room;

    private BigDecimal temperature;
    private BigDecimal humidity;
    private BigDecimal pressure;

    private MeditionStatus status;

    private Instant createdAt;

    public Medition(
            String room,
            BigDecimal temperature,
            BigDecimal humidity,
            BigDecimal pressure
    ) {
        this.room = room;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

        this.createdAt = Instant.now();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPressure() {
        return pressure;
    }

    public void setPressure(BigDecimal pressure) {
        this.pressure = pressure;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public MeditionStatus getStatus() {
        return status;
    }

    public void setStatus(MeditionStatus status) {
        this.status = status;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public MeditionStatus verifyStatus(
            BigDecimal base,
            BigDecimal gap
    ) {
        BigDecimal upperLimit = base.add(gap);
        BigDecimal lowerLimit = base.subtract(gap);

        if (temperature.compareTo(upperLimit) > 0) {
            this.status = MeditionStatus.HOT;
        } else if (temperature.compareTo(lowerLimit) < 0) {
            this.status = MeditionStatus.COLD;
        } else {
            this.status = MeditionStatus.NORMAL;
        }

        return this.status;
    }
}
