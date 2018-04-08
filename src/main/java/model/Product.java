package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "NVS_LS_Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long p_Id;

    private String p_Name;

    private double p_Price;

    @ManyToMany
    private List<Ingredient> p_ingredients;

    public Product(String p_Name, double p_Price, List<Ingredient> p_ingredients) {
        this.p_Name = p_Name;
        this.p_Price = p_Price;
        this.p_ingredients = p_ingredients;
    }

    public Product(String p_Name, double p_Price) {
        this.p_Name = p_Name;
        this.p_Price = p_Price;
    }

    public Product() {
    }

    public Long getP_Id() {
        return p_Id;
    }

    public void setP_Id(Long p_Id) {
        this.p_Id = p_Id;
    }

    public String getP_Name() {
        return p_Name;
    }

    public void setP_Name(String p_Name) {
        this.p_Name = p_Name;
    }

    public double getP_Price() {
        return p_Price;
    }

    public void setP_Price(double p_Price) {
        this.p_Price = p_Price;
    }

    public List<Ingredient> getP_ingredients() {
        return p_ingredients;
    }

    public void setP_ingredients(List<Ingredient> p_ingredients) {
        this.p_ingredients = p_ingredients;
    }
}
