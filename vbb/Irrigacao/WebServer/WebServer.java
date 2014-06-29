import muvium.compatibility.arduino.SPI; //<include <SPI.h>
import muvium.compatibility.arduino.IPAddress;
import muvium.compatibility.arduino.EthernetServer;
import muvium.compatibility.arduino.EthernetClient;
import muvium.compatibility.arduino.EthernetUDP;
import muvium.compatibility.arduino.Ethernet; //<include <Ethernet.h>
import muvium.compatibility.arduino.Arduino; 
class WebServer extends Arduino{//Automatically Added VBB Framework Code - do not remove
	/*
	Web Server

	A simple web server that shows the value of the analog input pins.
	using an Arduino Wiznet Ethernet shield. 

	Circuit:
	* Ethernet shield attached to pins 10, 11, 12, 13
	* Analog inputs attached to pins A0 through A5 (optional)

	created 18 Dec 2009
	by David A. Mellis
	modified 4 Sep 2010
	by Tom Igoe

	*/

	//#include <SPI.h>
	//#include <Ethernet.h>

	// Enter a MAC address and IP address for your controller below.
	// The IP address will be dependent on your local network:
	byte mac[] = { (byte)0xDE,(byte) 0xAD,(byte) 0xBE,(byte) 0xEF,(byte) 0xFE,(byte) 0xED };
	IPAddress ip = new IPAddress(this, 192, 168, 1, 177);

	// Initialize the Ethernet server library
	// with the IP address and port you want to use 
	// (port 80 is default for HTTP):
	EthernetServer server = new EthernetServer(this, 82);


	public void setup()
	{
		// start the Ethernet connection and the server:
		Ethernet.begin(mac, ip);
		server.begin();
	}

	public void loop()
	{
		serverWeb();
	}
	
	public void serverWeb()
	{
		// listen for incoming clients
		EthernetClient client = server.available();
		if (client != null) {
			// an http request ends with a blank line
			boolean currentLineIsBlank = true;
			while (client.connected()) {
				if (client.available() > 0) {
					char c = (char) client.read();
					// if you've gotten to the end of the line (received a newline
					// character) and the line is blank, the http request has ended,
					// so you can send a reply
					tokenString(c);
					if (c == '\n' && currentLineIsBlank) {
						// send a standard http response header
						client.println("HTTP/1.1 200 OK");
						client.println("Content-Type: text/html");
						client.println();
						client.println("Cliente conectado");
						
						break;
					}
					if (c == '\n') {
						// you're starting a new line
						currentLineIsBlank = true;
					} 
					else if (c != '\r') {
						// you've gotten a character on the current line
						currentLineIsBlank = false;
					}
				}
			}
			// give the web browser time to receive the data
			delay(1);
			// close the connection:
			client.stop();
		}	
	}
	
	public String[] tokenString(String text)
	{
		String returnValue[];
		
		Serial.print(text);
		
		return returnValue;
	}

}

