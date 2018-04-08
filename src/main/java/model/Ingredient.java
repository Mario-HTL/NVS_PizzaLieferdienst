package model;

import javax.persistence.*;

@Entity
@Table(name = "NVS_LS_Ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long i_Id;

    private String i_Name;

    public Ingredient(String i_Name) {
        this.i_Name = i_Name;
    }

    public Ingredient() {
    }

    public String getI_Name() {
        return i_Name;
    }

    public void setI_Name(String i_Name) {
        this.i_Name = i_Name;
    }

    public Long getI_Id() {
        return i_Id;
    }

    public void setI_Id(Long i_Id) {
        this.i_Id = i_Id;
    }
}
