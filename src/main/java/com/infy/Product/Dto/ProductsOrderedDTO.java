package com.infy.Product.Dto;





public class ProductsOrderedDTO {
	Integer orderid;
	Integer prodid;
	Integer sellerid;
	Integer quantity;
	String status;
	Double price;
	
	public ProductsOrderedDTO()
	{
		super();
	}
	public ProductsOrderedDTO(Integer orderid, Integer prodid, Integer sellerid, Integer quantity, String status, Double price)
	{
		this();
		this.orderid=orderid;
		this.prodid=prodid;
		this.sellerid=sellerid;
		this.quantity=quantity;
		this.status=status;
		this.price=price;
	}
	public Integer getOrderId() {
		return orderid;
	}
	public void setOrderId(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getProdId() {
		return prodid;
	}
	public void setProdId(Integer prodid) {
		this.prodid = prodid;
	}
	public Integer getSellerId() {
		return sellerid;
	}
	public void setSellerId(Integer sellerid) {
		this.sellerid = sellerid;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}


	
	
	@Override
	public String toString() {
		return "ProductsOrderedDTO [OrderId = " + orderid + ", price = " + price + ", prodId = " + prodid + ", quantity = " + quantity + ", sellerId = " + sellerid + ", status = " + status; 
	}
}

