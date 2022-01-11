import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SMTPSession
{
  public String host;
  public int port;
  public String recipient;
  public String sender;
  public String[] message;
  protected Socket sessionSock;
  protected BufferedReader inStream;
  protected PrintWriter outStream;
  
  public static void main(String[] s)
    throws Exception
  {
    if (s.length != 3)
    {
      System.out.println("usage is java SMTPSession <hostname><rec><sen>");
      System.exit(1);
    }
    String[] s1 = {
      "Hello", "Hai" };
    
    SMTPSession sm = new SMTPSession(s[0], s[1], s[2], s1);
    sm.sendMessage();
    sm.close();
  }
  
  public SMTPSession() {}
  
  public SMTPSession(String host, String recipient, String sender, String[] message)
    throws IOException
  {
    this.host = host;
    port = 25;
    this.recipient = recipient;
    this.message = message;
    this.sender = sender;
  }
  
  public SMTPSession(String host, int port, String recipient, String sender, String[] message)
    throws IOException
  {
    this.host = host;
    this.port = port;
    if (this.port <= 0) {
      this.port = 25;
    }
    this.recipient = recipient;
    this.message = message;
    this.sender = sender;
  }
  
  public void close()
    throws IOException
  {
    sessionSock.close();
    sessionSock = null;
  }
  
  protected void connect()
    throws IOException
  {
    sessionSock = new Socket(host, port);
    inStream = new BufferedReader(new InputStreamReader(sessionSock.getInputStream()));
    outStream = new PrintWriter(new OutputStreamWriter(sessionSock.getOutputStream()));
  }
  
  protected String doCommand(String commandString)
    throws IOException
  {
    outStream.println(commandString);
    outStream.flush();
    String response = getResponse();
    return response;
  }
  
  protected String getResponse()
    throws IOException
  {
    String response = "";
    String line;
    do
    {
      line = inStream.readLine();
      if (line == null) {
        throw new IOException("Bad response from server.");
      }
      if (line.length() < 3) {
        throw new IOException("Bad response from server.");
      }
      response = response + line + "\n";
    } while (line.charAt(3) == '-');
    return response;
  }
  
  public void sendMessage()
    throws IOException
  {
    connect();
    String response = getResponse();
    System.out.println(response);
    if (response.charAt(0) != '2') {
      throw new IOException(response);
    }
    response = doCommand("HELO junk");
    System.out.println(response);
    if (response.charAt(0) != '2') {
      throw new IOException(response);
    }
    response = doCommand("MAIL FROM:" + sender);
    System.out.println(response);
    if (response.charAt(0) != '2') {
      throw new IOException(response);
    }
    response = doCommand("RCPT TO:" + recipient);
    if (response.charAt(0) != '2') {
      throw new IOException(response);
    }
    response = doCommand("DATA");
    if (response.charAt(0) != '3') {
      throw new IOException(response);
    }
    for (int i = 0; i < message.length; i++) {
      if (message[i].length() == 0) {
        outStream.println();
      } else if (message[i].charAt(0) == '.') {
        outStream.println("." + message[i]);
      } else {
        outStream.println(message[i]);
      }
    }
    response = doCommand(".");
    if (response.charAt(0) != '2') {
      throw new IOException(response);
    }
    close();
  }
}