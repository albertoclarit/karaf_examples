/*
 * Copyright 2021 OPS4J.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.cdi.sample4.web;



import com.inkman.osgi.sample.service.definition.Greeter;
import org.ops4j.pax.cdi.api.Component;
import org.ops4j.pax.cdi.api.Dynamic;
import org.ops4j.pax.cdi.api.Immediate;
import org.ops4j.pax.cdi.api.Service;



import javax.annotation.PostConstruct;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;


//@ManagedBean(name="framework")
//@javax.faces.bean.RequestScoped
@Named("framework")
@RequestScoped
public class FrameworkBean {
    @Inject
    public FrameworkBean() {
    }

    @PostConstruct
    public void postConstruct () {
        System.out.println("postConstruct is called");
    }
/*
    @Inject
    @Dynamic
    @Service
    Greeter greeter;*/


    private String name;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void submit() {
        System.out.println("form submitted ");
    }
}
