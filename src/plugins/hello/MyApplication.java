package plugins.hello.world;

import java.io.IOException;
import java.net.URI;

import freenet.client.HighLevelSimpleClient;
import freenet.clients.http.Toadlet;
import freenet.clients.http.ToadletContainer;
import freenet.clients.http.ToadletContext;
import freenet.clients.http.ToadletContextClosedException;
import freenet.l10n.BaseL10n.LANGUAGE;
import freenet.pluginmanager.*;
import freenet.support.Logger;
import freenet.support.api.HTTPRequest;
import freenet.support.plugins.helpers1.PluginContext;
import freenet.support.plugins.helpers1.WebInterface;

import plugins.hello.world.Overview;


public class MyApplication implements FredPlugin, FredPluginL10n {
    PluginRespirator pr;
    // path used to access the plugin web interface
    public static String basePath = "/MyApplication";
    // WebInterface accessor
    public WebInterface webInterface;
    // Overview page
    public Overview oc;

    static {
        Logger.registerClass(MyApplication.class);
    }
    
    @Override
    public void runPlugin(PluginRespirator pr)
    {
        this.pr = pr;
        Logger.error(this, "FOOBAR MYAPPLICATION WEBINTERFACE HELLO WORLD");
        setupWebInterface();
    }
    
    @Override
    public void terminate()
    {
        pr.getToadletContainer().unregister(this.oc);
    }

    // L10n stuff
    public void setLanguage(LANGUAGE newLanguage) {
        Logger.error(this, "Not implemented: Should set LANGUAGE to: " + newLanguage.isoCode);
    }
    public String getString(String untranslated){
            return untranslated;
    }

    private void setupWebInterface()
    {
        PluginContext pluginContext = new PluginContext(pr);
        this.webInterface = new WebInterface(pluginContext);
        
        pr.getPageMaker().addNavigationCategory(basePath + "/","MyApplication.menuName.name", "MyApplication.menuName.tooltip", this);
        ToadletContainer tc = pr.getToadletContainer();
        
        // pages
        this.oc = new Overview(pr.getHLSimpleClient(), basePath, "");
        
        // create fproxy menu items
        tc.register(oc, "MyApplication.menuName.name", basePath + "/", true, "MyApplication.mainPage", "MyApplication.mainPage.tooltip", false, oc, this); // false: MyApplication.allowFullAccessOnly
        tc.register(oc, null, basePath + "/", true, false); // false: do we want to restrict to full access?
        
        // register other toadlets without link in menu but as first item to check
        // so it also works for paths which are included in the above menu links.
        // full access only will be checked inside the specific toadlet
        // for(Toadlet curToad : newToadlets) {
        //     tc.register(curToad, null, curToad.path(), true, false);
        // }
        
        // finally add toadlets which have been registered within the menu to our list
        // newToadlets.add(oc);
    }
}

// private class Resources {
//     
// }
