import io.github.cdimascio.dotenv.Dotenv;


/**
 *
 * @author Luis Miguel
 */
public class Perros {
    
    Dotenv dotenv = Dotenv.load();
    
    private String ID;
    private String url;
    private String apiKey;
    String image;

    public Perros() {
        this.apiKey = dotenv.get("/*API_KEY*/");
    }

    
    // get y set
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
