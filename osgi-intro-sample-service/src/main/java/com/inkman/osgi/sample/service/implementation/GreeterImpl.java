package com.inkman.osgi.sample.service.implementation;

import com.inkman.osgi.sample.service.definition.Greeter;
import com.inkman.osgi.sample.service.definition.GreetingResponse;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;

public class GreeterImpl implements Greeter, BundleActivator {

    private ServiceReference<Greeter> reference;
    private ServiceRegistration<Greeter> registration;

    @Override public GreetingResponse sayHiTo(String name) {
        try {
            String hostName = InetAddress.getLocalHost().getHostName();

            return new GreetingResponse("Hello From Greeter : [" + name + "] from " + hostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return new GreetingResponse("Hello From Greeter : [" + name + "]");
    }

    @Override public void start(BundleContext context) throws Exception {
        System.out.println("Registering service. In cluster");
       Hashtable<String,String> props= new Hashtable<String, String>();
        props.put("service.exported.interfaces", "*");
        registration = context.registerService(Greeter.class, new GreeterImpl(), props);
        reference = registration.getReference();
    }

    @Override public void stop(BundleContext context) throws Exception {
        System.out.println("Unregistering service.");
        registration.unregister();
    }
}
