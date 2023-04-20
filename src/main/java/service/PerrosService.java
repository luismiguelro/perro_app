package service;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Perros;
import model.PerrosFav;
import model.ProgressBar;

/**
 *
 * @author Luis Miguel
 */
public class PerrosService {
    // variables stactic
    private static String BASE_URL ="https://api.thedogapi.com/v1";
    private static String SEARCH_ENDPOINT = "/images/search";
    private static String FAVORITE_ENDPOINT = BASE_URL+"favourites";
   
    // declarat objeto progress
    static ProgressBar pb = new ProgressBar();
    
    
      // Metodos del menu(throws EX,E/S)
    public static Perros verPerros() throws IOException{

        
        // traer datos API
        OkHttpClient client = new OkHttpClient();
    
        Request request = new Request.Builder().url(BASE_URL+SEARCH_ENDPOINT).build();
        Response response = client.newCall(request).execute();
        
         
         
        //Guardar rta
        String elJson = response.body().string();
        
        // cortar caracteres (primero y el ultimo)
        elJson = elJson.substring(1, elJson.length()-1);
        
        
        //convertir objeto perro
        Gson gson = new Gson();
        Perros perros = gson.fromJson(elJson, Perros.class);
        return perros;
//        String id_perro = perros.getId();
//        
//         
//        
//        ImageIcon fondoPerrito = redimensionarImagen(perros.getUrl(), 390, 290);
        
        
    }
    public static void favoritoPerros(Perros perro){
        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n    \"image_id\":\""+perro.getId()+"\"\r\n}");
            Request request = new Request.Builder()
              .url(FAVORITE_ENDPOINT)     
              .get()
              .addHeader("Content-Type", "application/json")
              .addHeader("x-api-key",perro.getApiKey())
              .build();
            Response response = client.newCall(request).execute();
        }catch(IOException e){
             System.out.println("Error en favorito perros: "+e);
        }
    }
    
    public static void verFavorito(String apiKey) throws IOException{
        // Inicia el indicador de carga
       pb.setVisible(true);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
          .url(FAVORITE_ENDPOINT)
          .get()
          .addHeader("Content-Type", "application/json")
          .addHeader("x-api-key", apiKey)
          .build();

        Response response = client.newCall(request).execute();
        
        // capturar respuesta
        String elJson = response.body().string();
        
        // para que quede Objeto perro
        Gson gson = new Gson();
        
        //array tipo perrosfav
        PerrosFav[] perrosArray = gson.fromJson(elJson, PerrosFav[].class);
        
        // validar longitud
        if(perrosArray.length>0){
            //num aleatorio
            int min =1;
            int max= perrosArray.length;
            int aleatorio = (int) (Math.random()*((max-min)+1))+min;
            int indice = aleatorio-1;
            
            //objeto perroFav
            PerrosFav perroFav = perrosArray[indice];
            
            // redimensionar en caso de necesitqar
        Image image = null;
        }

               
         
        
    }
    public static void borrarFavorito(PerrosFav perroFav) throws IOException{
        // Inicia el indicador de carga
       pb.setVisible(true);
        try{
            OkHttpClient client = new OkHttpClient();
          MediaType mediaType = MediaType.parse("application/json");
          RequestBody body = RequestBody.create(mediaType, "");
          Request request = new Request.Builder()
            .url(FAVORITE_ENDPOINT+perroFav.getId()+"")
            .method("DELETE", body)
            .addHeader("Content-Type", "application/json")
            .addHeader("x-api-key", perroFav.getApiKey())
            .build();
          Response response = client.newCall(request).execute();
        } catch(IOException e){
            System.out.println("Error en borrar favorito: "+e);
        }
        // Termina el indicador de carga
       pb.setVisible(false);
    }
    
public static ImageIcon redimensionarImagen(String url) {
    try {
        // objeto URL y abrir conexion HTTP
        URL imageUrl = new URL(url);
        HttpURLConnection http = (HttpURLConnection) imageUrl.openConnection();
        http.addRequestProperty("User-Agent", "");

        // leeer la imagen de la URL utilizando ImageIO.read()

        BufferedImage imagenOriginal = ImageIO.read(http.getInputStream());

        //Creamr un objeto ImageIcon a partir de la imagen original.
        ImageIcon imagen = new ImageIcon(imagenOriginal);

        // Obtenemos el ancho y el alto de la imagen original.
        int anchoOriginal = imagen.getIconWidth();
        int altoOriginal = imagen.getIconHeight();

        // Definir los valores máximos de ancho y alto permitidos.
        int anchoMaximo = 390;
        int altoMaximo = 290;

        // Si la imagen es más grande que las dimensiones máximas, calculamos la proporción necesaria para redimensionar la imagen 
        // para que encaje dentro de las dimensiones máximas.
        if (anchoOriginal > anchoMaximo || altoOriginal > altoMaximo) {

            double proporcionAncho = (double) anchoMaximo / anchoOriginal;
            double proporcionAlto = (double) altoMaximo / altoOriginal;
            double proporcionRedimension = Math.min(proporcionAncho, proporcionAlto);

            // Calculamos las dimensiones de la imagen redimensionada y redimensionamos la imagen original utilizando el método getScaledInstance() 
            // de la clase Image.
            int anchoRedimensionado = (int) (anchoOriginal * proporcionRedimension);
            int altoRedimensionado = (int) (altoOriginal * proporcionRedimension);
            Image imagenRedimensionada = imagenOriginal.getScaledInstance(anchoRedimensionado, altoRedimensionado, java.awt.Image.SCALE_SMOOTH);

            // Crea un nuevo objeto ImageIcon a partir de la imagen redimensionada.
            imagen = new ImageIcon(imagenRedimensionada);
        }
        return imagen;
    } catch (IOException e) {
        System.out.println(e);
        return null;
    }
}



}