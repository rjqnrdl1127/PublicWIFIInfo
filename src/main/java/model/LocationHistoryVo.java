package model;

import java.sql.Date;

public class LocationHistoryVo {

    private Long id;
    private Float xCoordinate;
    private Float yCoordinate;
    private Date inquiryDate;

    public Long getId() {
        return id;
    }

    public Float getxCoordinate() {
        return xCoordinate;
    }

    public Float getyCoordinate() {
        return yCoordinate;
    }

    public Date getInquiryDate() {
        return inquiryDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setxCoordinate(Float xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(Float yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setInquiryDate(Date inquiryDate) {
        this.inquiryDate = inquiryDate;
    }
}
