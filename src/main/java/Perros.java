
import io.github.cdimascio.dotenv.Dotenv;
/**
 *
 * @author Luis Miguel
 */
public class Perros {
    private int ID;
    private String url;
    String apiKey = ;
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
