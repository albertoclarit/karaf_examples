/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ops4j.pax.web.samples.warjsf22cdi;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;


import com.inkman.osgi.sample.service.definition.Greeter;
import org.ops4j.pax.cdi.api.*;
import org.osgi.framework.*;
import org.osgi.framework.wiring.FrameworkWiring;

@Named("hello")
@Immediate
@Component
public class Hello {

    @PostConstruct
    void postContruct(){
        System.out.println("post construct");
    }
    private String what;
    private String result;
    private String test = "hello from working JSF 2.2/CDI 1.2 example";



    @Inject
    @Dynamic
    @Service
    @Optional
    @Greedy
    private Greeter greeter;



    public void setWhat(String what) {
        this.what = what;
    }

    public String getWhat() {
        return what;
    }

    public String getResult() {
        return result;
    }

    public String getTest() {

        return String.format("TEST");
    }

    public void say() {
        result = String.format("%s !", greeter.sayHiTo(what));
    }

    public void stopBundle() {
       // System.out.println("Stopping Greeter Service");
        BundleContext bc= FrameworkUtil.getBundle(this.getClass()).getBundleContext();


      ServiceReference<Greeter> greeterServiceReference = bc.getServiceReference(Greeter.class);
        try {
            greeterServiceReference.getBundle().stop();
        } catch (BundleException e) {
            e.printStackTrace();
        }
    }

    public void startBundle() {
       // System.out.println("Starting Greeter Service");
        Bundle bundle = FrameworkUtil.getBundle(this.getClass());
        BundleContext bc= bundle.getBundleContext();

        try {


            ServiceReference<?>[] serviceReferences = bc.getAllServiceReferences(null,null);

            for(ServiceReference r:serviceReferences){

                System.out.println(r.getBundle().getSymbolicName() + "======" + r.getBundle().getState());

            }
           // greeterServiceReference.getBundle().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

   /* @Inject
    @Dynamic
    @Service
    @Filter("(!(url.handler.protocol=mvn))")
    private URLStreamHandlerService handler;

    @Inject
    @Dynamic
    @Service
    private ServerControllerFactory serverControllerFactory;
*/

/*public String getTest() {

        return String.format("test, %s, %s", handler, serverControllerFactory);
    }

    public void say() {
        result = String.format("Hello %s! (mvn handler: %s, current web runtime: %s)", what, handler, serverControllerFactory);
    }*/

    /*@Inject
    @Dynamic
    @Service
    private BundleContext bc;*/
