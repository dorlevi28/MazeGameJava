package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private IClientStrategy clientStrategy;

    public Client(InetAddress serverIP, int port, IClientStrategy clientStrategy) {
        this.clientStrategy = clientStrategy;
        start(serverIP, port);
    }

    public void start(InetAddress serverIP, int port)
    {
        try {
            Socket socket = new Socket(serverIP,port);
            System.out.println("Client is connected to server!");
            clientStrategy.clientStrategy(socket.getInputStream(),socket.getOutputStream());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void communicateWithServer() {
    }
}
