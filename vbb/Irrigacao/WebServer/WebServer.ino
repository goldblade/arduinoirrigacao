//Autogenerate code from VirtualBreadboard: www.virtualbreadboard.com
#include <SPI.h>
#include <EthernetUDP.h>
#include <Ethernet.h>
byte mac[]= {(byte)0xDE,(byte) 0xAD,(byte) 0xBE,(byte) 0xEF,(byte) 0xFE,(byte) 0xED};
IPAddress ip(192,168,1,177);
EthernetServer server(82);
void setup()
{
	Ethernet.begin(mac, ip);
	server.begin();
}
void loop()
{
	serverWeb();
}
void serverWeb()
{
EthernetClient client = server.available();
if (client != NULL) {
			// an http request ends with a blank line
			boolean currentLineIsBlank = true;
			while (client.connected()) {
				if (client.available() > 0) {
					char c = (char) client.read();
					// if you've gotten to the end of the line (received a newline
					// character) and the line is blank, the http request has ended,
					// so you can send a reply
					Serial.print(c);
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
