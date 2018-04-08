package business;

import model.Ingredient;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class IngredientFacade {
    @PersistenceContext
    EntityManager em;

    public void create(Ingredient ingredient){
        if(ingredient.getI_Id() != null && em.find(Ingredient.class, ingredient.getI_Id())!= null){
            em.merge(ingredient);
        }
        else {
            em.persist(ingredient);
        }
    }

    public List<Ingredient> getAllIngredients() {
        TypedQuery<Ingredient> ingredientTypedQuery = em.createQuery("select i from Ingredient i", Ingredient.class);
        return ingredientTypedQuery.getResultList();
    }

    public Ingredient getIngredientById(int i_Id){
        TypedQuery<Ingredient> ingredientTypedQuery = em.createQuery("select i from Ingredient i where i.id =" + i_Id, Ingredient.class);
        return ingredientTypedQuery.getSingleResult();
    }
}
