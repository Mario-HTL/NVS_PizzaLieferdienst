package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "NVS_LS_Driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long d_Id;

    private String d_FirstName;

    private String d_LastName;

    public Driver(String d_FirstName, String d_LastName) {
        this.d_FirstName = d_FirstName;
        this.d_LastName = d_LastName;
    }

    public Driver() {
    }

    public Long getD_Id() {
        return d_Id;
    }

    public void setD_Id(Long d_Id) {
        this.d_Id = d_Id;
    }

    public String getD_FirstName() {
        return d_FirstName;
    }

    public void setD_FirstName(String d_FirstName) {
        this.d_FirstName = d_FirstName;
    }

    public String getD_LastName() {
        return d_LastName;
    }

    public void setD_LastName(String d_LastName) {
        this.d_LastName = d_LastName;
    }
}

