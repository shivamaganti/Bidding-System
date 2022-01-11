import java.util.Enumeration;
import java.util.Hashtable;

class SessionCache
  implements Runnable
{
  private Hashtable sessionCache;
  private long flush;
  private Thread reaper;
  
  SessionCache(long flush)
  {
    this.flush = flush;
    sessionCache = new Hashtable(100);
    reaper = new Thread(this);
    reaper.setPriority(1);
    reaper.start();
  }
  
  public void run()
  {
    try
    {
      Enumeration sessions;
      for (;; sessions.hasMoreElements())
      {
        Thread.sleep(flush);
        long expire = System.currentTimeMillis();
        sessions = sessionCache.elements(); 
		//continue;
        
        Session s = (Session)sessions.nextElement();
        if (expire >= s.getExpires()) {
          sessionCache.remove(s.key());
        }
      }
      //return;
    }
    catch (Exception e) {}
  }
  
  Session put(Session s)
  {
    return (Session)sessionCache.put(s.key(), s);
  }
  
  Session get(String key)
  {
    return (Session)sessionCache.get(key);
  }
  
  Enumeration elements()
  {
    return sessionCache.elements();
  }
  
  Enumeration keys()
  {
    return sessionCache.keys();
  }
  
  void remove(Session s)
  {
    sessionCache.remove(s.key());
  }
}