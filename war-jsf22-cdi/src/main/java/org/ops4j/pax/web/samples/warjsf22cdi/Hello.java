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


import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;


import com.inkman.osgi.sample.service.definition.Greeter;
import org.ops4j.pax.cdi.api.*;
import org.ops4j.pax.web.service.WebContainerConstants;
import org.osgi.framework.*;
import org.osgi.framework.Filter;
import org.osgi.util.tracker.ServiceTracker;

import static org.osgi.framework.Bundle.ACTIVE;


@Named("hello")
@Immediate
@Component
public class Hello {


   /* @Inject
    @Service
    private DynamicClient dynamicClient;*/


    private String what;
    private String result;
    private long greeterBundleId=-1L;

    public void setWhat(String what) {
        this.what = what;
    }

    public String getWhat() {
        return what;
    }

    public String getResult() {
        return result;
    }

  /* public void say() {
        result = String.format("%s !", dynamicClient.say(what));
    }*/

    /*
    https://www.north-47.com/knowledge-base/how-to-get-a-service-reference-or-bundlecontext-with-no-osgi-context/
    https://stackoverflow.com/questions/6527306/best-technique-for-getting-the-osgi-bundle-context
     */
  /*  public void say1() {
        BundleContext bundleContext = FrameworkUtil.getBundle(Greeter.class).getBundleContext();
        ServiceReference<Greeter> serviceReference = bundleContext.getServiceReference(Greeter.class);

       Greeter greeter = bundleContext.getService(serviceReference);
        result = String.format("%s !", greeter.sayHiTo(what));
    }*/
    public void say() throws InvalidSyntaxException, InterruptedException {

        ServletContext servletContext = (ServletContext) FacesContext
                .getCurrentInstance().getExternalContext().getContext();

        BundleContext bc = (BundleContext) servletContext.getAttribute(
                WebContainerConstants.BUNDLE_CONTEXT_ATTRIBUTE);

        Filter osgiFilter = FrameworkUtil.createFilter( "(objectclass=" + Greeter.class.getName() + ")" );
        ServiceTracker tracker = new ServiceTracker( bc, osgiFilter, null );
        tracker.open( true );

        Greeter svc = (Greeter) tracker.waitForService(5000l);

        if(svc != null){
            result =  String.format("Using Service Tracker %s", svc.sayHiTo(what).getMessage());
        }
        else

        {
            result = "Using Service Tracker Greeter [Service]  not found";
        }

    }
  public void say1() {

      ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();

      Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
      ServletContext servletContext = (ServletContext) FacesContext
              .getCurrentInstance().getExternalContext().getContext();


       // BundleContext bc= FrameworkUtil.getBundle(this.getClass()).getBundleContext();

      BundleContext bc = (BundleContext) servletContext.getAttribute(
              WebContainerConstants.BUNDLE_CONTEXT_ATTRIBUTE);
      ServiceReference<Greeter>[] greeterServiceReference = null;
      try {
          greeterServiceReference = (ServiceReference<Greeter>[]) bc.getAllServiceReferences(Greeter.class.getName(),null);

          Greeter greeter = null;
          if(greeterServiceReference!=null){
              for (ServiceReference<Greeter> reference : greeterServiceReference) {
                  System.out.println("======");
                  System.out.println("Bundle Id : " + reference.getBundle().getBundleId());
                  System.out.println("Bundle Location : " + reference.getBundle().getLocation());
                  System.out.println("State : " + reference.getBundle().getState());

                  if(reference.getBundle().getLocation().equals("mvn:com.inkman/osgi-intro-sample-service/1.0-SNAPSHOT")){
                      greeter = bc.getService(reference);
                  }
              }
          }

              if(greeter != null){
                  result = String.format("%s say 2!", greeter.sayHiTo(what).getMessage());
              }else {
                  result = "Greeter [Service]  not found";
              }


      } catch (InvalidSyntaxException e) {
          e.printStackTrace();
      }

      Thread.currentThread().setContextClassLoader(originalClassLoader);

    }


    public void stopBundle() {

      BundleContext bc= FrameworkUtil.getBundle(this.getClass()).getBundleContext();
      ServiceReference<Greeter> greeterServiceReference = bc.getServiceReference(Greeter.class);
        try {
            Bundle greeterBundle = greeterServiceReference.getBundle();
            greeterBundleId = greeterBundle.getBundleId();
            if(greeterBundle.getState() == ACTIVE){
                greeterBundle.stop();
            }

        } catch (BundleException e) {
            e.printStackTrace();
        }
    }

    public void startBundle() throws BundleException {

        if(greeterBundleId > 0){
            Bundle currentBundle = FrameworkUtil.getBundle(this.getClass());
            BundleContext bc= currentBundle.getBundleContext();


            Bundle bundle =  bc.getBundle(greeterBundleId); // bundle id


            if(bundle != null){
                if(bundle.getState() != ACTIVE){
                    bundle.start();
                }
            }
        } else {
            System.out.println("");
        }
    }

    private String test = "hello from working JSF 2.2/CDI 1.2 example";
    public String getTest() {

        return "TEST";
    }

}



