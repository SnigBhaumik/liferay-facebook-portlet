# How to deploy the war to configure the portlet #

## Prerequisites ##
Upend running Liferay Portal bundled version 5.2.x with Tomcat, running on port 8080.
If you are running on some other port, then you need to create your own app on facebook, and mention the credentials in   _portal-fusionkm-config.properties_ file vailable in _facebook\docroot\WEB-INF\src\_.

### Steps To Follow ###

  1. Download the war file from SVN
  1. Once download is complete , move to your server's **auto.deploy.dir =${app.server.dir}/../deploy** folder and drop the war file there. Liferay is set to scan this directory for plugin war.
  1. Check on your server console you will get messages regarding portlet that portlet is registered successfully and ready to use. Once this message is displayed. Go to next step.
  1. Go to _'WINDOWS\system32\drivers\etc\hosts'_ file and make this entry 	'XXX.XXX.XXX.XXX	fusionkm.infoaxon.com' (where XXX.XXX.XXX.XXX is the IP address of your machine). (**NOTE**: You must not have any duplicate entry in your host file associated with your specified IP address)
  1. Login as admin and click on add application link provided in dock. Go to **FKM** category and add the facebook portlet on your page.
  1. Now in the browser's address bar, you'll be having 'http://localhost:8080' as domain, modify it to 'http://fusionkm.infoaxon.com:8080'

Now you are ready to use the portlet.