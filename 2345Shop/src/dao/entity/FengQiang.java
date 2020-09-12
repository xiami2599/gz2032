package dao.entity;

public class FengQiang {

	private int id;
	private String title;
	private String picpath;
	private String descri;
	private double price;
	private double pririce; //原价
	private int youhui;
	private int xiaoliang;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicpath() {
		return picpath;
	}
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	public String getDescri() {
		return descri;
	}
	public void setDescri(String descri) {
		this.descri = descri;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPririce() {
		return pririce;
	}
	public void setPririce(double pririce) {
		this.pririce = pririce;
	}
	public int getYouhui() {
		return youhui;
	}
	public void setYouhui(int youhui) {
		this.youhui = youhui;
	}
	public int getXiaoliang() {
		return xiaoliang;
	}
	public void setXiaoliang(int xiaoliang) {
		this.xiaoliang = xiaoliang;
	}
	public FengQiang(int id, String title, String picpath, String descri, double price, double pririce, int youhui,
			int xiaoliang) {
		super();
		this.id = id;
		this.title = title;
		this.picpath = picpath;
		this.descri = descri;
		this.price = price;
		this.pririce = pririce;
		this.youhui = youhui;
		this.xiaoliang = xiaoliang;
	}
	public FengQiang() {
		super();
	}
	
	
}
