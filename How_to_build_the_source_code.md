# How to build the source code #

### Pre-requisites ###
  1. JDK 1.6
  1. Liferay Plugins SDK-5.2.3.
  1. Apache ANT.
  1. Liferay tomcat bundle version 5.2.3.


### Overview of Liferay Plugin SDK-5.2.3 ###
  1. Extract the zip file in your desired drive.  e.g. _E:/project/plugins-5.2.3_

> 2. Plugins SDK Configuration
    1. You will notice that the Plugins SDK contains a file called _build.properties_.This file contains the settings for where you have Liferay installed and where your deployment folder is going to be. However, you should not customize this file. Instead, create a new file in the same folder called _-build.${user.name}.properties_, where _${user.name}_ is your user ID on your machine. For example, if your user ID is "abc" then create a file with name _build.${abc}.properties_. Below is the example of setting:
> > _**Example for server settings**_
> > > app.server.type=tomcat<br />
> > > app.server.dir= E:/project/liferay-portal-5.2.3/tomcat-5.2.3<br />
> > > app.server.deploy.dir=${app.server.dir}/webapps<br />
> > > app.server.lib.global.dir=${app.server.dir}/lib/ext<br />
> > > app.server.portal.dir=${app.server.dir}/webapps/ROOT<br />

> > 2. The main development options available in Liferay Plugins SDK-5.2.3 are portlets, themes, layout templates, hooks. Each option in the Liferay Plugins SDK contains scripts for generating that type of plugin. New plugins are placed in their own subdirectory of the appropriate plugin directory. e.g. a new portlet called **"liferay-facebook-portlet"** would reside in _plugins-5.2.3/portlets/liferay-facebook-portlet_.
> > > Check out **"liferay-facebook-portlet"**  from SVN and get inside the _${Your-check-out-project-home}/plugins-5.2.3/portlets_ e.g _E:/test/portlet/plugins-5.2.3/portlets_.

> > Copy facebook folder to your **plugins-5.2.3 portlets** folder. Customize as per your requirement and deploy it.


> 3. All plugins are hot deployable means new version of particular plugin can be uploaded at run time.


### Deployment Steps ###
  1. Go inside portlets folder of your Plugin SDK-5.2.3. We now have folder facebook which we have checked out earlier, inside it there is _build.xml_ file.
  1. Open console move till above _build.xml_ path and give command
> > _**ant deploy**_
  1. Check whether your portlet is registered and ready for use once server is started.
  1. Login as admin and click on add application link provided in dock. Go to **FKM** category and drag the facebook portlet on your page.

Now you are ready to use the portlet.

**NOTE**: Don't forget to implement step 4 and 6 mentioned in http://code.google.com/p/liferay-facebook-portlet/wiki/Deploy_and_Configure_portlet