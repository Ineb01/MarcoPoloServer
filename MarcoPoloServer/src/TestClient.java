import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClient {

	public TestClient() {
		try {
			Socket socket = new Socket("localhost", 1254);
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
//			new Thread() {
//				@Override
//				public void run() {
//					try {
//						BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//						String message = " ";
//						do{
//							System.out.println(message);
//							message = input.readLine();
//						}while(message != null);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//					super.run();
//				}
//			}.start();
			while (true) {
				String keyboardInput = keyboard.readLine();
				output.println(keyboardInput);
			}

		} catch (UnknownHostException e) {
			System.out.println("unknown host");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new TestClient();
	}

}
