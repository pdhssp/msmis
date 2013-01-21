/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of 
 Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package gov.sp.health.bean;

import gov.sp.health.entity.Institution;
import gov.sp.health.facade.EstimateBillFacade;
import gov.sp.health.entity.EstimateBill;
import gov.sp.health.facade.InstitutionFacade;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@ManagedBean
@SessionScoped
public final class EstimateBillController implements Serializable {

    @EJB
    private EstimateBillFacade ejbFacade;
    @EJB
    InstitutionFacade institutionFacade;
    @ManagedProperty(value = "#{sessionController}")
    SessionController sessionController;
    // Estimate Items for the current estimate bill
    List<EstimateBill> lstItems;
    // Current Estimate Bill
    private EstimateBill current;
    // Selectted Bills
    private List<EstimateBill> items = null;
    // Entering text
    String selectText = "";
    Integer estimateYear;
    Date estimateDate;

    public Date getEstimateDate() {
        return estimateDate;
    }

    public void setEstimateDate(Date estimateDate) {
        this.estimateDate = estimateDate;
        Calendar now = Calendar.getInstance();
        now.setTime(estimateDate);
        estimateYear = now.get(Calendar.YEAR);
    }

    public String prepareAnnualEstimate(){
        if (estimateYear==null){
            JsfUtil.addErrorMessage("Please select a year");
            return "";
        }
        current = getFacade().findFirstBySQL("select e from EstimateBill e where e.estimateYear = " + estimateYear);
        if (current==null){
            current = new EstimateBill();
            current.setEstimateYear(estimateYear);
            current.setAnnualEstimate(Boolean.TRUE);
            current.setBillDate(Calendar.getInstance().getTime());
            current.setCreatedAt(Calendar.getInstance().getTime());
            current.setBillTime(Calendar.getInstance().getTime());
            
        
        }
        return "";
    }
    
    public EstimateBillController() {
    }

    public InstitutionFacade getInstitutionFacade() {
        return institutionFacade;
    }

    public void setInstitutionFacade(InstitutionFacade institutionFacade) {
        this.institutionFacade = institutionFacade;
    }

    public String getSelectText() {
        return selectText;
    }

    public void setSelectText(String selectText) {
        this.selectText = selectText;
    }

    public Integer getEstimateYear() {
        if (estimateYear == null) {
            Calendar now = Calendar.getInstance();
            return now.get(Calendar.YEAR);
        } else {
            return estimateYear;
        }
    }

    public void setEstimateYear(Integer estimateYear) {
        this.estimateYear = estimateYear;
    }

    public List<EstimateBill> getLstItems() {
        return getFacade().findBySQL("Select d From EstimateBill d WHERE d.retired=false ORDER BY d.name");
    }

    public void setLstItems(List<EstimateBill> lstItems) {
        this.lstItems = lstItems;
    }

    public EstimateBill getCurrent() {
        if (current == null) {
            current = getFacade().findFirstBySQL(selectText);

            current = new EstimateBill();
        }
        return current;
    }

    public void setCurrent(EstimateBill current) {
        this.current = current;
    }

    private EstimateBillFacade getFacade() {
        return ejbFacade;
    }

    public List<EstimateBill> getItems() {
        return (getFacade().findBySQL("SELECT u FROM EstimateBill u WHERE u.retired=false ORDER BY u.name"));
    }

    public static int intValue(long value) {
        int valueInt = (int) value;
        if (valueInt != value) {
            throw new IllegalArgumentException(
                    "The long value " + value + " is not within range of the int type");
        }
        return valueInt;
    }

    public EstimateBillFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(EstimateBillFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    @FacesConverter(forClass = EstimateBill.class)
    public static class EstimateBillControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstimateBillController controller = (EstimateBillController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estimateBillController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof EstimateBill) {
                EstimateBill o = (EstimateBill) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: " + EstimateBillController.class.getName());
            }
        }
    }
}
