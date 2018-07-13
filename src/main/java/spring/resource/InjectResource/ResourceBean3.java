package spring.resource.InjectResource;

import org.springframework.core.io.Resource;

public class ResourceBean3 {
    private Resource resource;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }
}
