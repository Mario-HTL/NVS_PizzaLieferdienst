package web;

import business.DriverFacade;
import business.IngredientFacade;
import business.OrderFacade;
import business.ProductFacade;
import model.Driver;
import model.Ingredient;
import model.Order;
import model.Product;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Named
@ViewScoped
public class IndexController implements Serializable {
    @Inject
    IngredientFacade iFacade;
    @Inject
    DriverFacade dFacade;
    @Inject
    ProductFacade pFacade;
    @Inject
    OrderFacade oFacade;

    private String newProductName;
    private double newProductPrice;
    private String[] selectedProducts;
    private List<String> products = new LinkedList<String>();
    private List<Product> allProducts = new LinkedList<Product>();
    private String addProductErrorMessage;

    private String newIngredientName;
    private List<Ingredient> newIngredients = new LinkedList<Ingredient>();
    private String[] selectedIngredients;
    private List<String> ingredients = new LinkedList<String>();

    private String newDriverFirstName;
    private String newDriverLastName;

    private List<Product> orderedProducts = new LinkedList<Product>();
    private String address;
    private int zipCode;
    private double newOrderTotal = 0;

    private List<Order> orders = new LinkedList<Order>();
    private List<Order> fOrders = new LinkedList<Order>();
    private List<Order> ordersWithoutDriver = new LinkedList<Order>();
    private List<Driver> drivers = new LinkedList<Driver>();

    private List<Ingredient> ingredientsList = new LinkedList<Ingredient>();

    public IndexController() {
    }



    public void addIngredient(){
        Ingredient i = new Ingredient(newIngredientName);
        iFacade.create(i);
    }

    public void addDriver(){
        Driver d = new Driver(newDriverFirstName, newDriverLastName);
        dFacade.create(d);
    }

    public void addProduct(){
        products = pFacade.getAllProductNames();

        if(!products.contains(newProductName)){
            addProductErrorMessage = "Product added!";

            if(selectedIngredients.length == 0){
                Product p = new Product(newProductName, newProductPrice);
                pFacade.create(p);
            }else {
                for(String s : selectedIngredients){
                    newIngredients.add(iFacade.getIngredientByName(s));
                }
                Product p = new Product(newProductName, newProductPrice, newIngredients);
                pFacade.create(p);
            }
        }else{
            addProductErrorMessage = "Product already exists!";
        }

    }

    public void addOrder(){
        newOrderTotal = 0;

        for(String p : selectedProducts){
            orderedProducts.add(pFacade.getProductByName(p.split(" ")[0]));
            newOrderTotal += pFacade.getProductByName(p.split(" ")[0]).getP_Price();
        }
        Order o = new Order(orderedProducts, address, zipCode);
        oFacade.create(o);
    }

    public void addDriverToOrders(){
        ordersWithoutDriver = oFacade.getAllOrdersWithoutDriver();

        for(Order o : ordersWithoutDriver){
            o.setO_Driver(dFacade.getDriverById(1));
            oFacade.create(o);
        }
    }

    public void finishOrder(Order o){
        if(o.getO_Driver() != null) {
            o.setO_done(true);
            oFacade.create(o);
        }
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public String getAddProductErrorMessage() {
        return addProductErrorMessage;
    }

    public void setAddProductErrorMessage(String addProductErrorMessage) {
        this.addProductErrorMessage = addProductErrorMessage;
    }

    public String getNewProductName() {
        return newProductName;
    }

    public void setNewProductName(String newProductName) {
        this.newProductName = newProductName;
    }

    public double getNewProductPrice() {
        return newProductPrice;
    }

    public void setNewProductPrice(double newProductPrice) {
        this.newProductPrice = newProductPrice;
    }

    public String[] getSelectedIngredients() {
        return selectedIngredients;
    }

    public void setSelectedIngredients(String[] selectedIngredients) {
        this.selectedIngredients = selectedIngredients;
    }

    public List<String> getIngredients() {
        ingredients = iFacade.getAllIngredientNames();
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getNewIngredientName() {
        return newIngredientName;
    }

    public void setNewIngredientName(String newIngredientName) {
        this.newIngredientName = newIngredientName;
    }

    public String getNewDriverFirstName() {
        return newDriverFirstName;
    }

    public void setNewDriverFirstName(String newDriverFirstName) {
        this.newDriverFirstName = newDriverFirstName;
    }

    public String getNewDriverLastName() {
        return newDriverLastName;
    }

    public void setNewDriverLastName(String newDriverLastName) {
        this.newDriverLastName = newDriverLastName;
    }

    public List<Ingredient> getNewIngredients() {
        return newIngredients;
    }

    public void setNewIngredients(List<Ingredient> newIngredients) {
        this.newIngredients = newIngredients;
    }

    public String[] getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(String[] selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public List<String> getProducts() {
        List<String> hpList = pFacade.getAllProductNames();
        Product hp;

        for(String p : hpList){
            hp = new Product();
            hp.setP_Name(pFacade.getProductByName(p).getP_Name());
            hp.setP_Price(pFacade.getProductByName(p).getP_Price());

            products.add(hp.getP_Name() + " " + hp.getP_Price() + "€");
        }

        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public List<Product> orderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public double getNewOrderTotal() {
        return newOrderTotal;
    }

    public void setNewOrderTotal(double newOrderTotal) {
        this.newOrderTotal = newOrderTotal;
    }

    public List<Order> getOrders() {
        orders = oFacade.getAllUnfinishedOrders();
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrdersWithoutDriver() {
        return ordersWithoutDriver;
    }

    public void setOrdersWithoutDriver(List<Order> ordersWithoutDriver) {
        this.ordersWithoutDriver = ordersWithoutDriver;
    }

    public List<Driver> getDrivers() {
        drivers = dFacade.getAllDrivers();
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<Order> getfOrders() {
        fOrders = oFacade.getAllFinishedOrders();
        return fOrders;
    }

    public void setfOrders(List<Order> fOrders) {
        this.fOrders = fOrders;
    }

    public List<Ingredient> getIngredientsList() {
        ingredientsList = iFacade.getAllIngredients();
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }
}
