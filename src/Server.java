
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.scenario.animation.SplineInterpolator;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;


public class Server {
    public static void main (String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/move", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
    static class MyHandler implements HttpHandler {
        @Override
       synchronized public void handle(HttpExchange t) throws IOException {
            new Thread(() -> {
                String response = "This is the response";
                try {
                    t.sendResponseHeaders(200, response.length());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                URI url = t.getRequestURI();
                String urlTratement = url.getQuery();
                String [] spliter = urlTratement.split("&");
                for (int i = 0; i<spliter.length;i++)
                {
                    spliter[i] = spliter[i].substring(2);

                }
                String click = spliter[2]+spliter[3];
                String[] spliter2 = click.split("=");
                spliter2[1] = spliter2[1].substring(0, spliter2[1].length() - 2);
                System.out.println(spliter2[2]);
                Move mov = new Move(Double.valueOf(spliter[0]), Double.valueOf(spliter[1]));
                try {
                    mov.move();
                    mov.click(spliter2[1],spliter2[2]);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                OutputStream os = t.getResponseBody();
                try {
                    os.write(response.getBytes());
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}