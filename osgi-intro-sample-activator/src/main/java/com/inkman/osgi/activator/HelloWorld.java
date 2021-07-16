package com.inkman.osgi.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/*
public class HelloWorld implements BundleActivator {

    public void start(BundleContext ctx) {
        System.out.println("Hello World.");
    }

    public void stop(BundleContext bundleContext) {
        System.out.println("Goodbye World.");
    }

}
*/

// using CDI
@Component
public class HelloWorld {

    @Activate
    public void activate(BundleContext bundleContext) throws Exception {
        System.out.println("Hello World.");
    }
    @Deactivate
    public void deactivate() throws Exception {
        System.out.println("Goodbye World.");
    }
}
