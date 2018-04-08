package business;

import model.Order;
import model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Produces;
import java.util.List;

@Stateless
public class OrderFacade {
    @PersistenceContext
    EntityManager em;

    public void create(Order order){
        if(order.getO_Id() != null && em.find(Order.class, order.getO_Id())!= null){
            em.merge(order);
        }
        else {
            em.persist(order);
        }
    }

    public Order getOrderById(int id){
        TypedQuery<Order> orderTypedQuery = em.createQuery("select o from Order o where o.o_Id =" + id, Order.class);
        return orderTypedQuery.getSingleResult();
    }

    public List<Order> getAllOrders() {
        TypedQuery<Order> orderTypedQuery = em.createQuery("select o from Order o", Order.class);
        return orderTypedQuery.getResultList();
    }

    public List<Order> getAllOrdersWithoutDriver() {
        TypedQuery<Order> orderTypedQuery = em.createQuery("select o from Order o where o.o_Driver is NULL order by o.o_ZipCode", Order.class);
        return orderTypedQuery.getResultList();
    }
}
