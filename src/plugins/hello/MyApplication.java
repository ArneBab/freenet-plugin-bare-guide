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
}
