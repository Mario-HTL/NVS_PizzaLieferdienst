package business;

import model.Driver;
import model.Ingredient;
import model.Order;
import model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.List;

@Startup
@Singleton
public class InitBean {

    @Inject
    IngredientFacade iFacade;
    @Inject
    ProductFacade pFacade;
    @Inject
    DriverFacade dFacade;
    @Inject
    OrderFacade oFacade;

    @PostConstruct
    public void init(){
        System.out.println("Start");

        iFacade.create(new Ingredient("tomato sauce"));
        iFacade.create(new Ingredient("cheese"));
        List<Ingredient> ingredientList = iFacade.getAllIngredients();

        pFacade.create(new Product("Margherita", 6.50, ingredientList));
        pFacade.create(new Product("Fanta", 2.00));
        List<Product> productList = pFacade.getAllProducts();

        dFacade.create(new Driver("Max", "Musterman"));

        oFacade.create(new Order(dFacade.getDriverById(1), productList, "Musterstraße 8", 4020));
        List<Order> orderList = oFacade.getAllOrders();
    }
}
