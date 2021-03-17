package com.infy.Product.service;



import java.util.List;

import org.springframework.http.ResponseEntity;

import com.infy.Product.Dto.ProductDTO;
import com.infy.Product.Dto.StockDTO;
import com.infy.Product.Dto.SubscribedproductDTO;











public interface ProductService {
	public List<ProductDTO> getAllProducts() throws ProductMSException;
	public List<ProductDTO> getProductByCategory(String category)throws ProductMSException;
	public List<ProductDTO> getProductByName(String productname) throws ProductMSException;
	public void addProduct(int sellerid, ProductDTO productDTO)throws ProductMSException;
	public ProductDTO getProductById(Integer prodid) throws ProductMSException;;
	public SubscribedproductDTO getDetailsBysubId(Integer subid)throws ProductMSException;
	public boolean checkStockBeforeOrder(Integer prodid,Integer quantity);
	public boolean Stock(StockDTO stockDTO) throws ProductMSException;
	public boolean deleteProduct(Integer prodid)throws ProductMSException;
	public boolean deleteProductBySellerId(Integer sellerid)throws ProductMSException;
	public ResponseEntity<String> addSubscription(SubscribedproductDTO subscribedProductDTO);
	public void addProduct(ProductDTO productDTO);
	
	
}
