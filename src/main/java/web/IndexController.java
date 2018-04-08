package web;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class IndexController implements Serializable{


    @PostConstruct
    public void init(){

    }


    public IndexController() {
    }


}
