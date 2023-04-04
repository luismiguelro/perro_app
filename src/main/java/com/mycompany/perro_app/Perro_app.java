package com.mycompany.perro_app;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Miguel
 */
public class Perro_app {

    public static void main(String[] args) throws IOException {
         int menu = -1;
       String [] botones ={"1. Ver perritos U・ᴥ・U ","2. Ver favoritos ❤️" ,"3. Salir 💨 "};
        
       do{
           // Menu principal
           String opcion = (String) JOptionPane.showInputDialog(null,"Perritos Java","Menú Principal",JOptionPane.INFORMATION_MESSAGE,null,botones,botones[0]);
           
           // validar opcion usuario
           for(int i=0;i<botones.length;i ++){
              if(opcion.equals(botones[i])){
                  menu = i;
              } 
           }
           switch(menu){
               case 0:
                    PerrosService.verPerros();
                    break;
               case 1:
                   Perros perrofav = new Perros();
                   PerrosService.verFavorito(perrofav.getApiKey());
                   break;
                default:
                    break;
           }
       }while(menu != 1);
    }
  }

