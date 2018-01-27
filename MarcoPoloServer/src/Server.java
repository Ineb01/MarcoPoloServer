import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server extends ServerSocket {

	public Server() throws IOException {
		super(1254);

		while (true) {

			Socket socket = this.accept();
			ActionThread action = new ActionThread(socket);
			action.start();

		}

	}

	public static void main(String[] args) throws IOException {
		new Server();
	}

}

class ActionThread extends Thread {

	public ActionThread(Socket socket) {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message = input.readLine();
			
			String returnValue = "";
			if (message != null) {
				Pattern p = Pattern.compile("[Mm][Aa4][Rr][Cc][Oo0]");
				Matcher m = p.matcher(message);

				System.out.println(message);

				if (m.find()) {
					returnValue = "polo";
				} else {
					returnValue = "no polo";
				}
			}
			
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			output.println(returnValue);
			
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
