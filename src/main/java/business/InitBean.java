package business;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@Startup
@Singleton
public class InitBean {

    @PostConstruct
    public void init(){

        System.out.println("Start");


    }
}
