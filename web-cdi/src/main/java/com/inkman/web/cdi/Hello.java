package com.inkman.web.cdi;




import org.ops4j.pax.cdi.api.Component;
import org.ops4j.pax.cdi.api.Immediate;

import javax.inject.Named;


@Named("hello")
//@ManagedBean(name = "hello")
@Immediate
@Component
public class Hello {

    private String what="";
    private String result="";
    private String test = "hello from working JSF 2.2/CDI 1.2 example";

/*
    @Inject
    @Dynamic
    @Service
    @Filter("(!(url.handler.protocol=mvn))")
    private URLStreamHandlerService handler;

    @Inject
    @Dynamic
    @Service
    private ServerControllerFactory serverControllerFactory;*/



   /* @Inject
    @Dynamic
    @Service
    private Greeter greeter;*/




    public void setWhat(String what) {
        this.what = what;
    }

    public String getWhat() {
        return what;
    }

    public String getResult() {
        return result;
    }

  /* public String getTest() {
        return String.format("test, %s, %s", handler, serverControllerFactory);
    }

    public void say() {
        result = String.format("Hello %s! (mvn handler: %s, current web runtime: %s)", what, handler, serverControllerFactory);
    }*/

    public String getTest() {
        return "test";
    }

    public void say() {
        result = String.format("Hello %s!", what);
    }
}

