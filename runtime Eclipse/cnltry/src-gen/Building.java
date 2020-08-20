class Building
{
	private float length;
	private float breadth;
	private float height;
	
	Building()
	{
		setLength(0);
		setBreadth(0);
		setHeight(0);
	}
	
	Building(float l, float b, float h)
	{
		setLength(l);
		setBreadth(b);
		setHeight(h);
	}
	
	public boolean equals(Building b)
	{
		if (this.length == b.getLength() && this.breadth == b.getBreadth() && this.height == b.getHeight())
			return true;
		return false;
	}
	
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	public float getBreadth() {
		return breadth;
	}
	public void setBreadth(float breadth) {
		this.breadth = breadth;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	
}