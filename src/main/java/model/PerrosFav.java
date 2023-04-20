package model;
import io.github.cdimascio.dotenv.Dotenv;
/**
 *
 * @author Luis Miguel
 */
public class PerrosFav {
    Dotenv dotenv = Dotenv.load();
    private String id;
    private String image_id;
    private String apiKey=dotenv.get("API_KEY");

    Imagex image;

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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}