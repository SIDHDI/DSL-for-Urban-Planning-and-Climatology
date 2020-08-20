@SuppressWarnings("all")
public class DetailsExtractor {
  private static int buildingCount = 20;
  
  public int getBuildingCount() {
    return this.buildingCount;
  }
  
  public void setBuildingCount(final int buildingCount) {
    this.buildingCount = buildingCount;
  }
  
  private static int l = 12;
  
  public int getL() {
    return this.l;
  }
  
  public void setL(final int l) {
    this.l = l;
  }
  
  private static int b = 10;
  
  public int getB() {
    return this.b;
  }
  
  public void setB(final int b) {
    this.b = b;
  }
  
  private static int h = 12;
  
  public int getH() {
    return this.h;
  }
  
  public void setH(final int h) {
    this.h = h;
  }
  
  private static int region = 1;
  
  public int getRegion() {
    return this.region;
  }
  
  public void setRegion(final int region) {
    this.region = region;
  }
  
  public static void main(final String... args) {
    System.out.println("findtempBuilAction");
    Region region1 = new Region();
    region1.calculateInitialLCZ(region);
    region1.findTempBuildAction(buildingCount,l,b,h);
    
  }
}
