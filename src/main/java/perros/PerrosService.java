package perros;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 *
 * @author Luis Miguel
 */
public class PerrosService {
    
    // Metodos del menu(throws EX,E/S)
    public static void verPerros() throws IOException{
        // traer datos API
        OkHttpClient client = new OkHttpClient();
    
        Request request = new Request.Builder().url("https://api.thedogapi.com/v1/images/search").build();
        Response response = client.newCall(request).execute();
        
        //Guardar rta
        String elJson = response.body().string();
        
        // cortar primer y ultimo caracter(substring)
        elJson = elJson.substring(1,elJson.length());
        elJson = elJson.substring(0,elJson.length()-1);
        
        
        //convertir objeto perro
        Gson gson = new Gson();
        Perros perros = gson.fromJson(elJson, Perros.class);
        
        // redimensionar en caso de necesitqar
        Image image = null;
        try{
            URL url = new URL(perros.getUrl());
            
            // convertir a tipo imagen y así poder cargarla
            image =  ImageIO.read(url);
            
            ImageIcon fondoPerrito = new ImageIcon();
            
            // validar width img
            if(fondoPerrito.getIconWidth()>800){
                // redimensionar
                Image fondo = fondoPerrito.getImage();
                Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                fondoPerrito = new ImageIcon(modificada);
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
}
