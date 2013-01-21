/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Temporal;

/**
 *
 * @author buddhika
 */
@Entity
@Inheritance
public class EstimateBill extends Bill implements Serializable {

    //Estimated For
    @Temporal(javax.persistence.TemporalType.DATE)
    Date fromDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date toDate;
    //Types of Estimates
    Boolean annualEstimate;
    Boolean supplimentary;
    Boolean recorrection;
    Integer estimateYear;

    public Integer getEstimateYear() {
        return estimateYear;
    }

    public void setEstimateYear(Integer estimateYear) {
        this.estimateYear = estimateYear;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Boolean getAnnualEstimate() {
        return annualEstimate;
    }

    public void setAnnualEstimate(Boolean annualEstimate) {
        this.annualEstimate = annualEstimate;
    }

    public Boolean getSupplimentary() {
        return supplimentary;
    }

    public void setSupplimentary(Boolean supplimentary) {
        this.supplimentary = supplimentary;
    }

    public Boolean getRecorrection() {
        return recorrection;
    }

    public void setRecorrection(Boolean recorrection) {
        this.recorrection = recorrection;
    }
}
