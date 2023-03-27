/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perro_app;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
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
            String id_perro =perros.getID();
            
            // interfaz
            String opcion = (String) JOptionPane.showInputDialog(null,menu,perros.getID(),JOptionPane.INFORMATION_MESSAGE,fondoPerrito,opciones,opciones[0]);
            
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
                case 1://favoritoPerro(perros);
                    break;
                default:
                    break;
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public static void favoritoPerros(Perros perro){
        
    }
    
}
