package dao.entity;

public class DongDongQiang {

	private int id;
	private String title;
	private String picpath;
	private String descri;
	private double price;
	private double pririce; //原价
	
	
	public DongDongQiang() {
		super();
	}
	
	public DongDongQiang(int id, String title, String picpath, String descri, double price, double pririce) {
		super();
		this.id = id;
		this.title = title;
		this.picpath = picpath;
		this.descri = descri;
		this.price = price;
		this.pririce = pririce;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

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
	@Override
	public String toString() {
		return "DongDongQiang [id=" + id + ", title=" + title + ", descri=" + descri + ", price=" + price + ", Pririce="
				+ pririce + "]";
	}
	
	

}
