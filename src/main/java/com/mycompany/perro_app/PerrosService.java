package com.mycompany.perro_app;

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
        
        // cortar caracteres (primero y el ultimo)
        elJson = elJson.substring(1, elJson.length()-1);
        
        
        //convertir objeto perro
        Gson gson = new Gson();
        Perros perros = gson.fromJson(elJson, Perros.class);
        String id_perro = perros.getId();
       
        
        // redimensionar en caso de necesitqar
        Image image = null;
        try{
            URL url = new URL(perros.getUrl());
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.addRequestProperty("User-Agent", "");
            BufferedImage bufferedImage = ImageIO.read(http.getInputStream());
            ImageIcon fondoPerrito = new ImageIcon(bufferedImage);
           
            // validar width img
            if(fondoPerrito.getIconWidth()>800){
                // redimensionar
                Image fondo = fondoPerrito.getImage();
                Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                fondoPerrito = new ImageIcon(modificada);
            }
            
            // menu
            String menu = "Opciones: \n"
                    + "1. Ver otra imagen\n"
                    + "2. Favorito\n"
                    + "3. Volver al menú\n";
            
            //Menu
            String [] opciones = {"Ver otra imagen","Favorito","Volver al menú"};
            
            // guardar id perrito ( valueof: convertir string)
            
            
            // interfaz
            String opcion = (String) JOptionPane.showInputDialog(null,menu,perros.getId(),JOptionPane.INFORMATION_MESSAGE,fondoPerrito,opciones,opciones[0]);
            
            // validar selección del usuario
            int seleccion =-1;
            for(int i=0; i<opciones.length;i++){
                if(opcion.equals(opciones[i])){
                    seleccion =i;
                }
            }
            
            switch(seleccion){
                case 0: verPerros();
                    break;
                case 1:favoritoPerros(perros);
                    break;
                default:
                    break;
            }
            PerrosService.verPerros();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public static void favoritoPerros(Perros perro){
        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n    \"image_id\":\""+perro.getId()+"\"\r\n}");
            Request request = new Request.Builder()
              .url("https://api.thedogapi.com/v1/favourites")     
              .get()
              .addHeader("Content-Type", "application/json")
              .addHeader("x-api-key",perro.getApiKey())
              .build();
            Response response = client.newCall(request).execute();
        }catch(IOException e){
             System.out.println(e);
        }
    }
    
    public static void verFavorito(String apiKey) throws IOException{
        
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
          .url("https://api.thedogapi.com/v1/favourites")
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
        try{
            URL url = new URL(perroFav.image.getUrl());
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.addRequestProperty("User-Agent", "");
            BufferedImage bufferedImage = ImageIO.read(http.getInputStream());
            ImageIcon fondoPerrito = new ImageIcon(bufferedImage);
           
            // validar width img
            if(fondoPerrito.getIconWidth()>800){
                // redimensionar
                Image fondo = fondoPerrito.getImage();
                Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                fondoPerrito = new ImageIcon(modificada);
            }
            
            // menu
            String menu = "Opciones: \n"
                    + "1. Ver otra imagen\n"
                    + "2. Eliminar Favorito\n"
                    + "3. Volver al menú\n";
            
            //Menu
            String [] opciones = {"Ver otra imagen","Eliminar Favorito","Volver al menú"};
            
            // guardar id perrito ( valueof: convertir string)
            
            
            // interfaz
            String opcion = (String) JOptionPane.showInputDialog(null,menu,perroFav.getId(),JOptionPane.INFORMATION_MESSAGE,fondoPerrito,opciones,opciones[0]);
            
            // validar selección del usuario
            int seleccion =-1;
            for(int i=0; i<opciones.length;i++){
                if(opcion.equals(opciones[i])){
                    seleccion =i;
                }
            }
            
            switch(seleccion){
                case 0: verFavorito(apiKey);
                    break;
                case 1: borrarFavorito(perroFav);
                    break;
                default:
                    break;
            }
        }catch(IOException e){
            System.out.println(e);
        }
        }
    }
    public static void borrarFavorito(PerrosFav perroFav){
        
    }
}
