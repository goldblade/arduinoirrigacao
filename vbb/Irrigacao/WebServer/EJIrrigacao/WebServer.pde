	
	/*
	* Web Server
	*
	* A simple web server that shows the value of the analog input pins.
	*/

	#include <Arduino.h>
	#include <string>
	#include <Ethernet.h>

	byte mac[] = {  (byte)0xDE, (byte) 0xAD, (byte) 0xBE, (byte) 0xEF, (byte)0xFE, (byte) 0xED };
	byte ip[] = { 10, 0, 0, (byte) 177 };

	Server server(81);

	void setup()
	{
		Ethernet.begin(mac, ip);
		server.begin();
	    //Serial.begin(9600);
	}

	void loop()
	{
		webServer();//chamando servidor web	
	}

	char verificaToken(char c)
	//String verificaToken(String c)
	{	
		//Serial.println("chamou aqui");
		//Serial.println(c.trim());
		Serial.println(c);
		return c;
	}

	void webServer()
	{
		Client client = server.available();
		if ( client ) {
			// an http request ends with a blank line
			//Serial.println("Request");			
			boolean current_line_is_blank = true;
			String token = String();
			String resource = String();
			while (client.connected()) {
				if ( client.available() ){
					
					char c = (char) client.read();	
					token += c;
					// if we've gotten to the end of the line (received a newline
					// character) and the line is blank, the http request has ended,
					// so we can send a reply					
				
					if (c == '\n' && current_line_is_blank) {
						//	chegou ao final da linha.. quebrar o char e verificar o segundo token
						//char teste = verificaToken(c);
						//Serial.print(token.trim());
						//GET / HTTP/1.1
						resource = token.substring(4, 6);
						//Serial.println("TESTE:::" + resource + "::::");
						// send a standard http response header
						client.println("HTTP/1.1 200 OK");
						client.println("Content-Type: text/html");
						client.println();
						client.println("CLIENTE CONECTADO");
						if (resource.equals("/ ")){
							//carregar o index.html
							Serial.println("ESTA TENTANDO ACESSAR O RECURSO / ");
						}
						//client.println("TOKEN: " + resource + "|");
						token = "";
						resource = "";
						// give the web browser time to receive the data
						break;
					}					
					if (c == '\n') {
						// we're starting a new line
						current_line_is_blank = true;
					} else if (c != '\r') {
						// we've gotten a character on the current line
						current_line_is_blank = false;
					}
				}
			}
			// give the web browser time to receive the data
			delay(1);
			client.stop();
			//Serial.println("Response Complete");
		}
	}