package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "NVS_LS_Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long o_Id;

    @ManyToOne
    private Driver o_Driver;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> o_Products;

    private String o_Address;

    private int o_ZipCode;

    private boolean o_done;

    public Order(Driver o_Driver, List<Product> o_Products, String o_Address, int o_ZipCode, boolean o_done) {
        this.o_Driver = o_Driver;
        this.o_Products = o_Products;
        this.o_Address = o_Address;
        this.o_ZipCode = o_ZipCode;
        this.o_done = o_done;
    }

    public Order(List<Product> o_Products, String o_Address, int o_ZipCode) {
        this.o_Products = o_Products;
        this.o_Address = o_Address;
        this.o_ZipCode = o_ZipCode;
    }

    public Order() {
    }

    public Long getO_Id() {
        return o_Id;
    }

    public void setO_Id(Long o_Id) {
        this.o_Id = o_Id;
    }

    public Driver getO_Driver() {
        return o_Driver;
    }

    public void setO_Driver(Driver o_Driver) {
        this.o_Driver = o_Driver;
    }

    public List<Product> getO_Products() {
        return o_Products;
    }

    public void setO_Products(List<Product> o_Products) {
        this.o_Products = o_Products;
    }

    public String getO_Address() {
        return o_Address;
    }

    public void setO_Address(String o_Address) {
        this.o_Address = o_Address;
    }

    public int getO_ZipCode() {
        return o_ZipCode;
    }

    public void setO_ZipCode(int o_ZipCode) {
        this.o_ZipCode = o_ZipCode;
    }

    public boolean isO_done() {
        return o_done;
    }

    public void setO_done(boolean o_done) {
        this.o_done = o_done;
    }
}
