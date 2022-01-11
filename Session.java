import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;

public class Session
{
  private String theuser;
  private int id;
  private long expires;
  
  public Session(String theuser)
  {
    this.theuser = URLEncoder.encode(theuser);
    id = Math.abs(new Random(System.currentTimeMillis()).nextInt());
    expires = 0L;
  }
  
  public synchronized void setExpires(long e)
  {
    expires = e;
  }
  
  public long getExpires()
  {
    return expires;
  }
  
  public String key()
  {
    return String.valueOf(id);
  }
  
  public String getEncodedUser()
  {
    return theuser;
  }
  
  public String getUser()
  {
    return URLDecoder.decode(theuser);
  }
  
  public int getId()
  {
    return id;
  }
}