/*
   Irrigacao Remota
 
   O projeto consiste em disponibilizar via web opcoes para fechar e abrir torneiras especificas
   alem de programar para ligar e desligar dependendo da humidade detectada pelo os sensores de humidade
 
   @author Eduardo da Penha Figueredo Junior
   @since 08/06/2014
   
 */

#include <SPI.h>
#include <Ethernet.h>
#include <SD.h>

File arquivo;
//String arquivoString = "";

//  MAC addres and ip from controller
byte mac[] = { 
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress ip(192,168,1,177);

//  Iniciando biblioteca Ethernet server
EthernetServer server(80);

void setup (){
  //  Abrindo conexao serial e esperando abertura da porta:
  Serial.begin(9600);
  while (!Serial) {
    ;
  }
  
  //  iniciando conexao Ethernet e servidor:
  Ethernet.begin(mac, ip);
  server.begin();
  Serial.print("servidor esta rodando em ");
  Serial.println(Ethernet.localIP());
}

/*void carregaArquivo(String arquivo) {
  if (SD.exists(arquivo)) {
  } else {
    //arquivo requisitado nao existe.. carregar arquivo 404.html exibindo que arquivo nao exist e enviando um cabecaclho com 404
  }
}*/

void loop() {
  // listen for incoming clients
  EthernetClient client = server.available();
  if (client) {
    Serial.println("novo cliente");
    // an http request ends with a blank line
    boolean currentLineIsBlank = true;
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();
        Serial.write(c);
        // if you've gotten to the end of the line (received a newline
        // character) and the line is blank, the http request has ended,
        // so you can send a reply
        if (c == '\n' && currentLineIsBlank) {
          //  Pegar o nome do arquivo e enviar para a funcao carregaArquivo
          //client.print(carregaArquivo(c.algumacoisa));
          // send a standard http response header
          client.println("HTTP/1.1 200 OK");
          client.println("Content-Type: text/html");
          client.println("Connection: close");  // the connection will be closed after completion of the response
	  //client.println("Refresh: 5");  // refresh the page automatically every 5 sec
          client.println();
          client.println("<!DOCTYPE HTML>");
          client.println("<html>");
          client.println(" EXIBINDO DADOS :D ");
          client.println("</html>");
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
    // dando ao navegador tempo para receber os dados
    delay(1);
    // fechando a conexao:
    client.stop();
    Serial.println("cliente disconectado");
  }
}
