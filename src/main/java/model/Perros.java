
package model;
import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author Luis Miguel
 */
public class Perros {
    
    Dotenv dotenv = Dotenv.load();
    private String id;
    private String url;
    private String apiKey= dotenv.get("API_KEY");;
    private String image;
    // get y set

    public Dotenv getDotenv() {
        return dotenv;
    }

    public void setDotenv(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}

