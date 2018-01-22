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
				new ActionThread(socket).start();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	private class ActionThread extends Thread{
		public ActionThread(Socket socket) {
			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
				
				String message = input.readLine();
				
				Pattern p = Pattern.compile("[mM]arco");
				Matcher m = p.matcher(message);
				
				System.out.println(message);
				
				while(m.find()) {
					output.println("Polo");
				}
				
				if(Pattern.compile("end").matcher(message).find()) {
					running = false;
				}
				
				socket.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Server(1254);
	}

}
