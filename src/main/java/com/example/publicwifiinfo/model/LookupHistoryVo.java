package com.example.publicwifiinfo.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class LookupHistoryVo {

    private Long id;
    private BigDecimal xCoordinate;
    private BigDecimal yCoordinate;
    private Timestamp inquiryDate;

    public Long getId() {
        return id;
    }

    public BigDecimal getxCoordinate() {
        return xCoordinate;
    }

    public BigDecimal getyCoordinate() {
        return yCoordinate;
    }

    public Timestamp getInquiryDate() {
        return inquiryDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setxCoordinate(BigDecimal xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(BigDecimal yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setInquiryDate(Timestamp inquiryDate) {
        this.inquiryDate = inquiryDate;
    }
}
