package plugins.hello.world;

import freenet.pluginmanager.*;
import freenet.support.Logger;

public class BareBones implements FredPlugin, FredPluginThreadless {
    PluginRespirator pr;

    static {
        Logger.registerClass(BareBones.class);
    }
    
    public void runPlugin(PluginRespirator pr)
    {
        this.pr = pr;
        Logger.error(this, "FOOBAR MYAPPLICATION HELLO WORLD");
    }
    
    public void terminate()
    {}
}
