/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.facade;

import gov.sp.health.entity.EstimateBill;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IT
 */
@Stateless
public class EstimateBillFacade extends AbstractFacade<EstimateBill> {
    @PersistenceContext(unitName = "HOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstimateBillFacade() {
        super(EstimateBill.class);
    }
    
    /**
     * 
     * This is a comment
     * 
     * with a second line
     * 
     * 
     * this is a thired line 
     */
    
    
    
    
}
