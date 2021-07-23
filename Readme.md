
# Pax-CDI Implementation Code Discovery

Module: war-jsf22-cdi

In order to run Pax-CDI successfully these are the features that needs to be enabled

- `feature:install scr`
- `feature:install http`
- `feature:install webconsole`
- `feature:install http-whiteboard`
- `feature:install war`
- `feature:install pax-jsf-support`

Unfortunately `pax-jsf-support` is using Myfaces 2.2 and Pax-CDI was expecting 
2.3

We need to remove the module
- `bundle:list`  and look for the ids of MyFaces libraries
- `bundle:uninstall [id]`

We can now instal MyFaces 2.3
- `install    mvn:org.apache.myfaces.core/myfaces-api/2.3.8`
- `install    mvn:org.apache.myfaces.core/myfaces-impl/2.3.8`

Start the MyFaces 2.3 bundles
`start [ids]`

We need to activate 
Then we can proceed with installation of Pax-CDI dependencies
- `feature:install pax-cdi`
- `feature:install pax-cdi-web`
- `feature:install pax-cdi-web-weld`
- `feature:install pax-cdi-web-weld-jetty`


Building the modules
`mvn clean install`

Install Services Greeter

`bundle:install -s mvn:com.inkman/osgi-intro-sample-service/1.0-SNAPSHOT`

Install JSF App

`bundle:install -s webbundle:mvn:org.ops4j.pax.web.samples/war-jsf22-cdi/1.0-SNAPSHOT/war?Web-ContextPath=war-jsf22-cdi`
