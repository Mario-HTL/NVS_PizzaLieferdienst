package business;

import model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Produces;
import java.util.List;

@Stateless
public class ProductFacade {
    @PersistenceContext
    EntityManager em;

    public void create(Product product){
        if(product.getP_Id() != null && em.find(Produces.class, product.getP_Id())!= null){
            em.merge(product);
        }
        else {
            em.persist(product);
        }
    }

    public Product getProductById(int p_Id){
        TypedQuery<Product> productTypedQuery = em.createQuery("select p from Product p where p.id =" + p_Id, Product.class);
        return productTypedQuery.getSingleResult();
    }

    public List<Product> getAllProducts() {
        TypedQuery<Product> productTypedQuery = em.createQuery("select p from Product p", Product.class);
        return productTypedQuery.getResultList();
    }
}
