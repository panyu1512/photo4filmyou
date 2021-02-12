package auth.util;

public class Container {
    private static HttpManager httpManager;

    public static HttpManager getHttpManager()
    {
        if(httpManager == null){
            httpManager = new HttpManager();
        }

        return httpManager;
    }
}
