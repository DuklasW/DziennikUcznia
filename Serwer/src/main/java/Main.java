import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.util.List;

public class Main {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("MyUnit");

    public static void main(String[] args) {

        //Querries querries = new Querries();
        //List<String> frekwencja = querries.findFrekwencjaRodzajOrderByGodzinaLekcji("KD001", Date.valueOf("2022-09-09"));
        //System.out.println(frekwencja);

        System.out.println("Czekam...");
        try{
            ServerSocket server = new ServerSocket(8081);
            while(true) {
                Socket socket = server.accept();
                ThreadForClient thc = new ThreadForClient(socket);
                thc.start();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
