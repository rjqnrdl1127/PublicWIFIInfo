package model;

import java.sql.Date;

public class WifiInfoVo {

    private Float distance;
    private String controlNumber;
    private String borough;
    private String wifiName;
    private String streetNameAddress;
    private String detailedAddress;
    private String installationLocation;
    private String installationType;
    private String installationAgency;
    private String serviceClassification;
    private String typeOfNet;
    private String yearOfInstallation;
    private String indoorOutdoor;
    private String wifiConnectionEnvironment;
    private Float xCoordinate;
    private Float yCoordinate;
    private Date workingDate;

    public Float getDistance() {
        return distance;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public String getBorough() {
        return borough;
    }

    public String getWifiName() {
        return wifiName;
    }

    public String getStreetNameAddress() {
        return streetNameAddress;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public String getInstallationLocation() {
        return installationLocation;
    }

    public String getInstallationType() {
        return installationType;
    }

    public String getInstallationAgency() {
        return installationAgency;
    }

    public String getServiceClassification() {
        return serviceClassification;
    }

    public String getTypeOfNet() {
        return typeOfNet;
    }

    public String getYearOfInstallation() {
        return yearOfInstallation;
    }

    public String getIndoorOutdoor() {
        return indoorOutdoor;
    }

    public String getWifiConnectionEnvironment() {
        return wifiConnectionEnvironment;
    }

    public Float getxCoordinate() {
        return xCoordinate;
    }

    public Float getyCoordinate() {
        return yCoordinate;
    }

    public Date getWorkingDate() {
        return workingDate;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public void setStreetNameAddress(String streetNameAddress) {
        this.streetNameAddress = streetNameAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public void setInstallationLocation(String installationLocation) {
        this.installationLocation = installationLocation;
    }

    public void setInstallationType(String installationType) {
        this.installationType = installationType;
    }

    public void setInstallationAgency(String installationAgency) {
        this.installationAgency = installationAgency;
    }

    public void setServiceClassification(String serviceClassification) {
        this.serviceClassification = serviceClassification;
    }

    public void setTypeOfNet(String typeOfNet) {
        this.typeOfNet = typeOfNet;
    }

    public void setYearOfInstallation(String yearOfInstallation) {
        this.yearOfInstallation = yearOfInstallation;
    }

    public void setIndoorOutdoor(String indoorOutdoor) {
        this.indoorOutdoor = indoorOutdoor;
    }

    public void setWifiConnectionEnvironment(String wifiConnectionEnvironment) {
        this.wifiConnectionEnvironment = wifiConnectionEnvironment;
    }

    public void setxCoordinate(Float xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(Float yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setWorkingDate(Date workingDate) {
        this.workingDate = workingDate;
    }
}
