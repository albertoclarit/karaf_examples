package org.ops4j.pax.web.samples.warjsf22cdi;


import com.inkman.osgi.sample.service.definition.Greeter;
import com.inkman.osgi.sample.service.definition.GreetingResponse;
import org.ops4j.pax.cdi.api.*;

import javax.enterprise.inject.Any;
import javax.inject.Inject;


//@Service
//@Global
public class DynamicClient {


   /* @Inject
    @Dynamic
    @Service
    @Optional
    @Greedy
    private Greeter greeter;


    String say(String name ){
        GreetingResponse response =greeter.sayHiTo(name);
        return String.format("%s dynamic!", response.getMessage() );
    }
*/

}
