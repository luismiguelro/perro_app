package model;
import io.github.cdimascio.dotenv.Dotenv;
/**
 *
 * @author Luis Miguel
 */
public class PerrosFav {
    public Imagex image;
    Dotenv dotenv = Dotenv.load();
    public String id;
    public String image_id;
    private String apiKey=dotenv.get("API_KEY");
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

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Imagex getImage() {
        return image;
    }

    public void setImage(Imagex image) {
        this.image = image;
    }    

    public String getUrl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}