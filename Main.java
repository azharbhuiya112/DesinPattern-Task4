//Roll:2007112

/*
The Bridge pattern is used to connect or bridge the functionality of remotes and TVs. 
This allows for adding new TVs or new remote functionalities without affecting each other. 
The TV interface acts as the abstraction, and the SmartTV and GeneralTV classes act as the refined abstractions.
 The Remote and SmartRemote classes act as the implementors, providing the functionality for the remotes.

Proxy Design Pattern:
The Proxy pattern is used to control access to the YoutubeClass object. 
The proxyLoadYoutube class is a proxy for the FirstLoadYoutube class. 
The first time runYoutube() is called, it initializes and runs the FirstLoadYoutube. 
Subsequent calls to runYoutube() use the existing instance of FirstLoadYoutube without reinitializing it. 
*/
interface TV{
  boolean isEnabled();

  void enable();

  void disable();

  int getVolume();

  void setVolume(int volume);

  int getChannel();

  void setChannel(int channel);

}

class SmartTV implements TV{
  boolean powerOn=false;
  int volume=22;
  int channel=1;

  @Override
  public boolean isEnabled() {
      return powerOn;
  }

  @Override
  public void enable() {
      powerOn=true;
  }

  @Override
  public void disable() {
      powerOn=false;
  }

  @Override
  public int getVolume() {
      return volume;
  }

  @Override
  public void setVolume(int vol) {
      volume=vol;
  }

  @Override
  public int getChannel() {
      return channel;
  }

  @Override
  public void setChannel(int channel) {
      this.channel=channel;
  }
  public void Youtube(YoutubeClass yt){
      System.out.println("Youtube for SmartTV");
      yt.runYoutube();
  }
}

class GeneralTV implements TV{
  boolean powerOn=false;
  int volume=20;
  int channel=1;

  @Override
  public boolean isEnabled() {
      return powerOn;
  }

  @Override
  public void enable() {
      powerOn=true;
  }

  @Override
  public void disable() {
      powerOn=false;
  }

  @Override
  public int getVolume() {
      return volume;
  }

  @Override
  public void setVolume(int vol) {
      volume=vol;
  }

  @Override
  public int getChannel() {
      return channel;
  }

  @Override
  public void setChannel(int channel) {
      this.channel=channel;
  }
}


class Remote{
  protected TV tv;

  Remote(){tv=null;}
  Remote(TV tv){
      this.tv=tv;
  }
  public void togglePower() {
      if(tv.isEnabled()){
          tv.disable();
          System.out.println("Power off");
      }
      else {
          tv.enable();
          System.out.println("Power on");
      }
  }

  public void volumeUp() {
      if(tv.isEnabled()){
          tv.setVolume(tv.getVolume()+1);
          System.out.println("Increasing volume +1");
      }else{
          System.out.println("Please Turn on tv first");
      }
  }

  public void volumeDown() {
      if(tv.isEnabled()){
          tv.setVolume(tv.getVolume()-1);
          System.out.println("Decreasing volume -1");
      }else{
          System.out.println("Please Turn on tv first");
      }

  }

  public void channelUp() {
      if(tv.isEnabled()){
          tv.setChannel(tv.getVolume()+1);
          System.out.println("Channel +1");
      }else{
          System.out.println("Please Turn on tv first");
      }


  }

  public void channelDown() {
      if(tv.isEnabled()){
          tv.setChannel(tv.getVolume()-1);
          System.out.println("Channel -1");
      }else{
          System.out.println("Please Turn on tv first");
      }

  }
}

class SmartRemote extends Remote{

  SmartRemote(){

  }
  SmartRemote(TV tv){
      super(tv);
  }
  void showYoutube(YoutubeClass yt){
      ((SmartTV)tv).Youtube(yt);
  }
}


interface YoutubeClass{
  void runYoutube();
}
class FirstLoadYoutube implements YoutubeClass{

  @Override
  public void runYoutube() {
      System.out.println("->Running Youtube");
  }
}

class proxyLoadYoutube implements YoutubeClass{
  private FirstLoadYoutube firstLoadYoutube;
  @Override
  public void runYoutube() {
      if(firstLoadYoutube==null){
          firstLoadYoutube=new FirstLoadYoutube();
          System.out.println("->Starting Youtube");
      }
      firstLoadYoutube.runYoutube();
  }
}

public class Main {
  public static void main(String[] args) {


     

      GeneralTV gtv=new GeneralTV();
      Remote remote= new Remote(gtv);
      System.out.println("General Tv::");
      remote.togglePower();
      remote.volumeUp();
      remote.channelUp();
      remote.channelDown();
      remote.volumeDown();

    
      System.out.println();
      System.out.println("Smart Tv::");
      SmartTV stv=new SmartTV();
      SmartRemote sremote= new SmartRemote(stv);
      sremote.togglePower();
      sremote.volumeUp();
      sremote.channelUp();
      sremote.channelDown();
      sremote.volumeDown();

      System.out.println();
    
      YoutubeClass yt=new proxyLoadYoutube();

    
      sremote.showYoutube(yt);
      sremote.showYoutube(yt);
      
  }

}