package org.zerock.vo;

public class ProductVO {//데이터 저장빈 클래스

	private String name;//상품명
	private double price;
	public ProductVO(String name,double price) {
		this.name=name;
		this.price=price;
		
		}//setter가 필요없음 => 생성자가 있기때문
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	@Override
	public String toString() {
		return "ProductVO [name=" + name + ", price=" + price + "]";
	}
	
	
	
	
	
}
