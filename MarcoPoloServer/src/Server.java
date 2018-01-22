import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {

	private ServerSocket server;

	private boolean running = true;

	public Server(int port) {
		try {
			server = new ServerSocket(1254);
			
			while (running) {

				Socket socket = server.accept();
				ActionThread action = new ActionThread(socket);
				action.start();
				
				running = action.getRunning();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Server(1254);
	}

}

class ActionThread extends Thread {
	private boolean running = true;
	public ActionThread(Socket socket) {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			boolean requestRunning = true;
			while (requestRunning) {
				String message = input.readLine();
				if (message != null) {
					Pattern p = Pattern.compile("[Mm]arco");
					Matcher m = p.matcher(message);

					System.out.println(message);

					while (m.find()) {
						output.println("Polo");
					}

					if (Pattern.compile("[Ee]nd[Ss]ocket").matcher(message).find()) {
						requestRunning = false;
						running = false;
					} else if (Pattern.compile("[Ee]nd").matcher(message).find()) {
						requestRunning = false;
					}
				}
			}
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean getRunning() {
		
		return running;
	}
}
