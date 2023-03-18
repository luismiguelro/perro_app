
import io.github.cdimascio.dotenv.Dotenv;
import static io.github.cdimascio.dotenv.DslKt.dotenv;
/**
 *
 * @author Luis Miguel
 */
public class Perros {
    Dotenv dotenv = Dotenv.load();
    
    private int ID;
    private String url;
    private String apiKey = dotenv.get("API_KEY");
    String image;

    
    // get y set
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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
