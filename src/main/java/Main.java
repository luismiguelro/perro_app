
import perros.PerrosService;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Miguel
 */
public class Main {
    public static void main (String [] args){
        int menu = -1;
       String [] botones ={"1.Ver perritos :)","2.Salir"};
        
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
                   break;
               case 1: PerrosService.verPerros();
                   break;
           }
       }while(menu != 1);
    }
    
}
