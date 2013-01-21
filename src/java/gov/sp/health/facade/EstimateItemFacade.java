/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.sp.health.facade;

import gov.sp.health.entity.EstimateItem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IT
 */
@Stateless
public class EstimateItemFacade extends AbstractFacade<EstimateItem> {
    @PersistenceContext(unitName = "HOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstimateItemFacade() {
        super(EstimateItem.class);
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
