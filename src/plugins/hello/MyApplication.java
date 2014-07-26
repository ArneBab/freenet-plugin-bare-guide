package plugins.hello.world;

import freenet.pluginmanager.*;
import freenet.support.Logger;

public class MyApplication implements FredPlugin {
    PluginRespirator pr;

    static {
        Logger.registerClass(MyApplication.class);
    }
    
    public void runPlugin(PluginRespirator pr)
    {
        this.pr = pr;
        Logger.error(this, "FOOBAR MYAPPLICATION HELLO WORLD");
    }
    
    public void terminate()
    {}

    private void setupWebInterface()
    {
        PluginContext pluginContext = new PluginContext(pr);
        this.webInterface = new WebInterface(pluginContext);
        
        pr.getPageMaker().addNavigationCategory(basePath + "/","WebOfTrust.menuName.name", "WebOfTrust.menuName.tooltip", this);
        ToadletContainer tc = pr.getToadletContainer();
        
        // pages
        Overview oc = new Overview(this, pr.getHLSimpleClient(), basePath, db);
        
        // create fproxy menu items
        tc.register(oc, "WebOfTrust.menuName.name", basePath + "/", true, "WebOfTrust.mainPage", "WebOfTrust.mainPage.tooltip", WebOfTrust.allowFullAccessOnly, oc);
        tc.register(oc, null, basePath + "/", true, WebOfTrust.allowFullAccessOnly);
        
        // register other toadlets without link in menu but as first item to check
        // so it also works for paths which are included in the above menu links.
        // full access only will be checked inside the specific toadlet
        for(Toadlet curToad : newToadlets) {
            tc.register(curToad, null, curToad.path(), true, false);
        }
        
        // finally add toadlets which have been registered within the menu to our list
        newToadlets.add(oc);
    }
}

