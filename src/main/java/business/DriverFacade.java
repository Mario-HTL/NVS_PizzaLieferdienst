package business;

import model.Driver;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class DriverFacade {
    @PersistenceContext
    EntityManager em;

    public void create(Driver driver){
        if(driver.getD_Id() != null && em.find(Driver.class, driver.getD_Id())!= null){
            em.merge(driver);
        }
        else {
            em.persist(driver);
        }
    }

    public Driver getDriverById(int d_Id){
        TypedQuery<Driver> driverTypedQuery = em.createQuery("select d from Driver d where d.id =" + d_Id, Driver.class);
        return driverTypedQuery.getSingleResult();
    }
}
