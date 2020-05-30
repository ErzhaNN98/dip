package resources;

import java.net.URL;

public class ResourcesResolver {
    private static ResourcesResolver resourcesResolver;

    public static ResourcesResolver getResourcesResolver() {
        if (resourcesResolver == null) {
            resourcesResolver = new ResourcesResolver();
        }
        return resourcesResolver;
    }

    private ResourcesResolver() {}

    public URL getResource(String resource) {
        return getClass().getResource(resource);
    }
}
