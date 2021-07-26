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


import javax.inject.Inject;
import javax.inject.Named;


import com.inkman.osgi.sample.service.definition.Greeter;
import org.ops4j.pax.cdi.api.*;
import org.osgi.framework.*;

import static org.osgi.framework.Bundle.ACTIVE;


@Named("hello")
@Immediate
@Component
public class Hello {


    @Inject
    @Dynamic
    @Service
    @Optional
    @Greedy
    private Greeter greeter;



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

    public void say() {
        result = String.format("%s !", greeter.sayHiTo(what));
    }
   /* public void say() {

        BundleContext bc= FrameworkUtil.getBundle(this.getClass()).getBundleContext();
        ServiceReference<Greeter> greeterServiceReference = bc.getServiceReference(Greeter.class);

         if(greeterServiceReference != null){
             Greeter greeter = bc.getService(greeterServiceReference);

             if(greeter != null){
                 result = String.format("%s !", greeter.sayHiTo(what));
             }else {
                 result = "Greeter [Service]  not found";
             }
         }else {
             result = "Greeter [Service Reference] not found";
         }


    }*/

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



