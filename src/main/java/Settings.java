import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;

@Resource.Classpath("application.properties")
public class Settings {
    @Property("baseUrl")
    private String baseUrl;
    private static Settings instance;
    public static Settings getInstance(){
        if (instance == null){
            instance = new Settings();
        }
        return instance;
    }

    private Settings(){
        PropertyLoader propertyLoader = PropertyLoader.newInstance();
        propertyLoader.populate(this);
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
