package plugins.hello.world;

import java.io.IOException;
import java.net.URI;

import freenet.client.HighLevelSimpleClient;
import freenet.clients.http.LinkEnabledCallback;
import freenet.clients.http.Toadlet;
import freenet.clients.http.ToadletContainer;
import freenet.clients.http.ToadletContext;
import freenet.clients.http.ToadletContextClosedException;
import freenet.support.api.HTTPRequest;


public class Overview extends Toadlet implements LinkEnabledCallback {

    protected String path;
    protected String filePath;
    
    public Overview(HighLevelSimpleClient client, String filepath, String URLPath) {
        super(client);
        this.path = URLPath;
        this.filePath = filepath;
    }
    public boolean isEnabled(ToadletContext tc) {
        return true;
    }
    
    //this handles an HTTP GET to the path exposed in the method path()
    public void handleMethodGET(URI uri, HTTPRequest request, ToadletContext ctx) throws ToadletContextClosedException, IOException {
        writeHTMLReply(ctx, 200, "pageContent", "Hello world!");
    }
    
    //the relative URL that this page is at (i.e. /my_app/mypage)
    @Override
    public String path() {
        return path;
    }
}
