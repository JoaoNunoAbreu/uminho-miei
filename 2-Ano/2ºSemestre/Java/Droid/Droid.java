public class Droid {
  
  int batteryLevel;
  
  public Droid() {
    batteryLevel = 100;
  }
  
  public void activate() {
    System.out.println("Activated. How can I help you?");
    batteryLevel = batteryLevel - 5;
    System.out.println("Battery level is: " + batteryLevel + " percent.");
  }
    
  public void chargeBattery(int hours) {
    System.out.println("Droid charging...");
    batteryLevel = batteryLevel + hours;
    if (batteryLevel > 100) batteryLevel = 100;
        System.out.println("Battery level is: " + batteryLevel + " percent.");
  }
  
  public int checkBatteryLevel() {
    System.out.println("Battery level is: " + batteryLevel + " percent.");
    return (batteryLevel);
  }
  
  public void hover(int feet) {
    if (feet > 2) System.out.println("Error! I cannot hover above 2 feet.");
    else {
      System.out.println("Hovering...");
      batteryLevel = batteryLevel - 20;
    }
    System.out.println("Battery level is: " + batteryLevel + " percent.");
  }
  
  public static void main (String [] args) {
    Droid spike = new Droid();
    spike.activate();
    spike.chargeBattery(5);
    spike.hover(1);
  }  
}