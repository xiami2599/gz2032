package dao.entity;

public class BaoYou {

	private int id;
	private String title;
	private String picpath;
	private double price;
	private double prix; //原价
	private String describe;
	private int discount; //优惠
	private int sales; //销量
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	@Override
	public String toString() {
		return "BaoYou [id=" + id + ", title=" + title + ", picpath=" + picpath + ", price=" + price + ", prix=" + prix
				+ ", describe=" + describe + ", discount=" + discount + ", sales=" + sales + "]";
	}
	
	
	
	
}
