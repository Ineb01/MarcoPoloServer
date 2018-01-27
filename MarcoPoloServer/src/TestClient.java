import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TestClient {

	public TestClient() {

	}

	public static void main(String[] args) throws IOException {
		while (true) {
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			String keyboardInput;
			keyboardInput = keyboard.readLine();

			Socket socket = new Socket("localhost", 1254);
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output.println(keyboardInput);

			String message = input.readLine();
			System.out.println(message);

			socket.close();

		}
	}

}
