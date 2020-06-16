package network.server4.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import network.server4.vo.Guest;
import network.server4.vo.Message;

public class TestDevice extends Application{

	private BorderPane root;
	private FlowPane bottom, right;
	
//	private Button ledBtn, heatBtn, coolBtn;
//	private Button blindBtn, bedBtn;
	
	private Button connBtn, disconnBtn;
	private TextField inputField;
	private TextArea textarea;
	
	//
	private String deviceID = "" + this.hashCode();
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	private ExecutorService executor;
	
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	
	
	// ======================================================
	public void displayText(String msg) {
		Platform.runLater(() -> {
			textarea.appendText(msg + "\n");
		});
	}
	
	
	
	// ======================================================
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();
		root.setPrefSize(700, 500);
		
		// Center ----------------------------------------------
		textarea = new TextArea();
		textarea.setEditable(false);
		root.setCenter(textarea);
		
		// Bottom ----------------------------------------------
		connBtn = new Button("conn");
		connBtn.setPrefSize(70, 40);
		connBtn.setOnAction((e) -> {
			startClient();
		});
		
		disconnBtn = new Button("disconn");
		disconnBtn.setPrefSize(70, 40);
		disconnBtn.setOnAction((e) -> {
			stopClient();
		});
		
		inputField = new TextField();
		inputField.setPrefSize(350, 40);
		inputField.setOnAction((e) -> {
			send(new Message(null, null, null, inputField.getText()));
			inputField.clear();
		});
		
		Button latte1 = new Button("latte1");
		latte1.setPrefSize(70, 40);
		latte1.setOnAction((e) -> {
			send(new Message(null, "LOGIN", null, gson.toJson(new Guest("latte1", "latte1"))));
		});
		
		Button latte8 = new Button("latte8");
		latte8.setPrefSize(70, 40);
		latte8.setOnAction((e) -> {
			send(new Message(null, "LOGIN", null, gson.toJson(new Guest("latte8", "latte1"))));
		});
		
		Button DEVICE011 = new Button("DEVICE011");
		DEVICE011.setPrefSize(100, 40);
		DEVICE011.setOnAction((e) -> {
			send(new Message("DEVICE011", "CONNECT", null, null));
			displayText("DEVICE011");
		});
		Button DEVICE012 = new Button("DEVICE012");
		DEVICE012.setPrefSize(100, 40);
		DEVICE012.setOnAction((e) -> {
			send(new Message("DEVICE012", "CONNECT", null, null));
			displayText("DEVICE012");
		});
		Button DEVICE021 = new Button("DEVICE021");
		DEVICE021.setPrefSize(100, 40);
		DEVICE021.setOnAction((e) -> {
			send(new Message("DEVICE021", "CONNECT", null, null));
			displayText("DEVICE021");
		});
		Button DEVICE022 = new Button("DEVICE022");
		DEVICE022.setPrefSize(100, 40);
		DEVICE022.setOnAction((e) -> {
			send(new Message("DEVICE022", "CONNECT", null, null));
			displayText("DEVICE022");
		});
		
		bottom = new FlowPane();
		bottom.getChildren().addAll(connBtn, disconnBtn, latte1, latte8, inputField, DEVICE011, DEVICE012, DEVICE021, DEVICE022);
		root.setBottom(bottom);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Test");
		primaryStage.setOnCloseRequest((e) -> {
			stopClient();
		});
		primaryStage.show();
	}// start()
	
	
	
	// ======================================================
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	// ======================================================
	public void startClient() {
		
		executor = Executors.newFixedThreadPool(1);
		
		Runnable runnable = () -> {
			try {
				socket = new Socket();
				socket.connect(new InetSocketAddress("192.168.21.2", 55566));
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				output = new PrintWriter(socket.getOutputStream());
			} catch (IOException e) {
//				e.printStackTrace();
				stopClient();
				return;
			}
			
//			output.println(deviceID);
//			output.flush();
			
			String line = "";
			while(true) {
				try {
					line = input.readLine();
//					System.out.println(line);
					if(line == null) {
						displayText("server error. disconnected");
						throw new IOException();
					} else {
						displayText(line);
						//
					}
				} catch (IOException e) {
//					e.printStackTrace();
					stopClient();
					break;
				}
			} // while()
			stopClient();
			displayText("disconnected");
		};
		executor.submit(runnable);
	} // startClient()
	
	public void stopClient() {
		try {
			if(socket != null && !socket.isClosed()) {
				socket.close();
				if(input != null) input.close();
				if(output != null) output.close();
			}
			if(executor != null && !executor.isShutdown()) {
				executor.shutdownNow();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // stopClient()
	
	public void send(String msg) {
//		output.println(gson.toJson(new Message(this.deviceID, "MESSAGE", msg)));
		output.println(msg);
		output.flush();
	}
	
	public void send(Message msg) {
		send(gson.toJson(msg));
	}

}