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

    public List<String> getAllIngredientNames() {
        TypedQuery<String> ingredientTypedQuery = em.createQuery("select i.i_Name from Ingredient i", String.class);
        return ingredientTypedQuery.getResultList();
    }

    public Ingredient getIngredientById(int id){
        TypedQuery<Ingredient> ingredientTypedQuery = em.createQuery("select i from Ingredient i where i.i_Id =" + id, Ingredient.class);
        return ingredientTypedQuery.getSingleResult();
    }

    public Ingredient getIngredientByName(String name){
        TypedQuery<Ingredient> ingredientTypedQuery = em.createQuery("select i from Ingredient i where i.i_Name = :name", Ingredient.class)
                .setParameter("name", name);
        return ingredientTypedQuery.getSingleResult();
    }
}
