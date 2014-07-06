import com.virtualbreadboard.interfaces.*;
import com.muvium.compatibility.arduino.*; 
class WebServer extends com.muvium.compatibility.arduino.Arduino{//Automatically Added VBB Framework Code - do not remove
	 //!>0:0
	/* //!>0:1
	* Web Server //!>0:2
	* //!>0:3
	* A simple web server that shows the value of the analog input pins. //!>0:4
	*/ //!>0:5
 //!>0:6
	//#include <Arduino.h> //!>0:7
	//#include <string> //!>0:8
	//#include <Ethernet.h> //!>0:9
 //!>0:10
	byte mac[] = {  (byte)0xDE, (byte) 0xAD, (byte) 0xBE, (byte) 0xEF, (byte)0xFE, (byte) 0xED }; //!>0:11
	byte ip[] = { 10, 0, 0, (byte) 177 }; //!>0:12
 //!>0:13
	Server server;//; //!>0:14
 //!>0:15
	
public void setup() //!>0:16
	{
		server = createServer(81);
 //!>0:17
		Ethernet.begin(mac, ip); //!>0:18
		server.begin(); //!>0:19
	    //Serial.begin(9600); //!>0:20
	} //!>0:21
 //!>0:22
	public void loop() //!>0:23
	{ //!>0:24
		webServer();//chamando servidor web	 //!>0:25
	} //!>0:26
 //!>0:27
	char verificaToken(char c) //!>0:28
	//String verificaToken(String c) //!>0:29
	{	 //!>0:30
		//Serial.println("chamou aqui"); //!>0:31
		//Serial.println(c.trim()); //!>0:32
		Serial.println(c); //!>0:33
		return c; //!>0:34
	} //!>0:35
 //!>0:36
	void webServer() //!>0:37
	{ //!>0:38
		Client client = server.available(); //!>0:39
if( iff ( client )) { //!>0:40
			// an http request ends with a blank line //!>0:41
			//Serial.println("Request");			 //!>0:42
			boolean current_line_is_blank = true; //!>0:43
			String token = String(); //!>0:44
			String resource = String(); //!>0:45
while( iff (client.connected())) { //!>0:46
if( iff ( client.available() )){ //!>0:47
					 //!>0:48
					char c = (char) client.read();	 //!>0:49
					token += c; //!>0:50
					// if we've gotten to the end of the line (received a newline //!>0:51
					// character) and the line is blank, the http request has ended, //!>0:52
					// so we can send a reply					 //!>0:53
				 //!>0:54
if( iff (c == '\n' && current_line_is_blank)) { //!>0:55
						//	chegou ao final da linha.. quebrar o char e verificar o segundo token //!>0:56
						//char teste = verificaToken(c); //!>0:57
						//Serial.print(token.trim()); //!>0:58
						//GET / HTTP/1.1 //!>0:59
						resource = token.substring(4, 6); //!>0:60
						Serial.println("TESTE:::" + resource + "::::"); //!>0:61
						// send a standard http response header //!>0:62
						client.println("HTTP/1.1 200 OK"); //!>0:63
						client.println("Content-Type: text/html"); //!>0:64
						client.println(); //!>0:65
						client.println("CLIENTE CONECTADO"); //!>0:66
if( iff (resource.equals("/ "))){ //!>0:67
							Serial.println("ESTA TENTANDO ACESSAR O RECURSO / "); //!>0:68
						} //!>0:69
						//client.println("TOKEN: " + resource + "|"); //!>0:70
						token = ""; //!>0:71
						resource = ""; //!>0:72
						// give the web browser time to receive the data //!>0:73
						break; //!>0:74
					}					 //!>0:75
if( iff (c == '\n')) { //!>0:76
						// we're starting a new line //!>0:77
						current_line_is_blank = true; //!>0:78
} else if( iff (c != '\r')) { //!>0:79
						// we've gotten a character on the current line //!>0:80
						current_line_is_blank = false; //!>0:81
					} //!>0:82
				} //!>0:83
			} //!>0:84
			// give the web browser time to receive the data //!>0:85
			delay(1); //!>0:86
			client.stop(); //!>0:87
			//Serial.println("Response Complete"); //!>0:88
		} //!>0:89
	} //!>0:90

public static void main(String[] args ){ 
  new WebServer().run(); }
};
