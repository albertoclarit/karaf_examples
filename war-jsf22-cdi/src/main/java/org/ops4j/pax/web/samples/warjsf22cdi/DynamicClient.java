package org.ops4j.pax.web.samples.warjsf22cdi;


import com.inkman.osgi.sample.service.definition.Greeter;
import org.ops4j.pax.cdi.api.*;

import javax.inject.Inject;

@Service
@Global
public class DynamicClient {


    @Inject
    @Dynamic
    @Service
    @Optional
    @Greedy
    private Greeter greeter;


    String say(String name ){
        return String.format("%s dynamic!", greeter.sayHiTo(name));
    }


}
