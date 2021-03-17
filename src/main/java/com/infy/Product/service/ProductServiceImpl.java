package com.infy.Product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.infy.Product.Dto.OrderDetailsDTO;
import com.infy.Product.Dto.ProductDTO;
import com.infy.Product.Dto.StockDTO;
import com.infy.Product.Dto.SubscribedproductDTO;
import com.infy.Product.entity.Product;
import com.infy.Product.entity.Subscribedproduct;
import com.infy.Product.repository.ProductRepository;
import com.infy.Product.repository.SubscribedproductRepository;






@Service
public class ProductServiceImpl implements ProductService{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SubscribedproductRepository subscribedproductRepository;
	
	//Get the entire product list
	public List<ProductDTO> getAllProducts() throws ProductMSException{
		System.out.println("In service");
		List<Product>products = productRepository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		for(Product p:products) {
			ProductDTO productDTO = ProductDTO.valueOf(p);
			productDTOs.add(productDTO);
		}
		logger.info("Product details : {}", productDTOs);
		return productDTOs;
	}

	@Override
	public List<ProductDTO> getProductByCategory(@PathVariable String category) throws ProductMSException {
		// TODO Auto-generated method stub
		List<Product> product = productRepository.findByCategory(category);
		List<ProductDTO> productDTOs = new ArrayList<>();
		if(product!=null) {
		
		for(Product p:product) {
			productDTOs.add(ProductDTO.valueOf(p));
		}
		logger.info("Product details according to category : {}", productDTOs);
		
		return productDTOs;
		}
		throw new ProductMSException("Service.NO_CATEGOTY_FOUND");
	}

	@Override
	public List<ProductDTO> getProductByName(String productname) throws ProductMSException {
		// TODO Auto-generated method stub
		
		List<Product> products = productRepository.findByProductname(productname);
		List<ProductDTO> productDTOs = new ArrayList<>();
		if(products!=null) {
		for(Product p:products) {
			productDTOs.add(ProductDTO.valueOf(p));
		}
		logger.info("Product details according to product name : {}", productDTOs);
		return productDTOs;
		}
		throw new ProductMSException("Service.NO_PRODUCT_FOUND_BY_NAME");
	}
	public void addProduct(int sellerid,ProductDTO productDTO) {
		productDTO.setSellerid(sellerid);
		Product product=productDTO.createEntity();
		productRepository.save(product);
	}
 
	
		public ProductDTO getProductById(Integer prodid) throws ProductMSException {
			logger.info("====== Product details==== {} ======",prodid);
			ProductDTO productDTO=null;
			Optional<Product> product = productRepository.findById(prodid);
			if(product!=null) {
			if(product.isPresent())
			{
				Product p=product.get();
				productDTO =ProductDTO.valueOf(p);
		}
			return productDTO;
	}
			throw new ProductMSException("Service.NO_PRODUCT_FOUND_BY_ID");
		}
//		
//		public ResponseEntity<String> addSubscription(SubscribedproductDTO subscribedproductDTO) {
//			String url = "http://localhost:8400/api/allorders";
//			RestTemplate restTemplate = new RestTemplate();
//			OrderDetailsDTO[] orderdetailssDTO = restTemplate.getForObject(url, OrderDetailsDTO[].class);
//			//System.out.println(orderdetailssDTO[0]);
//			//System.out.println(orderdetailssDTO.length);
//			int buyerid=subscribedproductDTO.getBuyerid();
//			float totalprice=0;
//			for(int i=0;i<orderdetailssDTO.length;i++) {
//				if(orderdetailssDTO[i].getBuyerid()==buyerid) {
//					System.out.println(orderdetailssDTO[i].getBuyerid());
//					totalprice+=orderdetailssDTO[i].getAmount();
//				}
//			}
//			System.out.println(totalprice);
//			if(totalprice>1000000) {//1 reward point=100 rupee -- privileged if he has 10k points--100X10000
//				//add to respected table code comes here
//				Subscribedproduct subscribedProduct=subscribedproductDTO.createEntity();
//				subscribedproductRepository.save(subscribedProduct);
//				return new ResponseEntity<String>("product order added successfully!!", HttpStatus.OK);
//			}
//			else {
//			return new ResponseEntity<String>("Not a privileged user!!", HttpStatus.BAD_REQUEST);
//				}
//			}
 
		@Override
		public SubscribedproductDTO getDetailsBysubId(Integer subid) throws ProductMSException {
			logger.info("====== Subscription details==== {} ======",subid);
			SubscribedproductDTO subscribedproductDTO=null;
			Optional<Subscribedproduct> product = subscribedproductRepository.findById(subid);
			if(product!=null) {
			if(product.isPresent())
			{
				Subscribedproduct p=product.get();
				subscribedproductDTO =SubscribedproductDTO.valueOf(p);
		}
			return subscribedproductDTO;
		}
			throw new ProductMSException("Service.NO_PRODUCT_FOUND_BY_subID");
		}
		public boolean checkStockBeforeOrder(Integer prodid,Integer quantity) {
			logger.info("Updating Stock after order");
			Optional<Product> product1 = productRepository.findById(prodid);
			Product p=product1.get();
			Boolean value;
			if(p.getStock()>=quantity) {
				p.setStock(p.getStock()-quantity);
				value=true;
			}
			else {
				value=false;
			}
			return value;
		}
		public boolean Stock(StockDTO stockDTO) {
			logger.info("Updating Stock for :{}",stockDTO);
			Optional<Product> product1 = productRepository.findById(stockDTO.getProdid());
			if(stockDTO.getStock()>=10)
			{ 
				Product p=product1.get();
				p.setStock(stockDTO.getStock());
				productRepository.save(p);
				return true;
		}
			else
			{
				return false;
			}
}
		public boolean deleteProduct(Integer prodid)
		{
					Optional<Product> product = productRepository.findById(prodid);
					if(product.isPresent())
					{
				
					productRepository.deleteById(prodid);
					return true;
					}
					return false;			
		}
				
				//delete product using seller id
				
				public boolean deleteProductBySellerId(Integer sellerid)
				{
					Boolean b=null;
					List<Product> product = productRepository.findAll();
					for(Product p:product)
					{
						if(p.getSellerid()==sellerid)
						{
							productRepository.deleteById(p.getProdid());
							b = true;
						}
						else
						{
						b = false;
						}
					}

					return b;
				}

				@Override
				public ResponseEntity<String> addSubscription(SubscribedproductDTO subscribedProductDTO) {
					String url = "http://localhost:8100/api/allorders";
					RestTemplate restTemplate = new RestTemplate();
					OrderDetailsDTO[] orderdetailssDTO = restTemplate.getForObject(url, OrderDetailsDTO[].class);
					
					int buyerid=subscribedProductDTO.getBuyerid();
					float totalprice=0;
					for(int i=0;i<orderdetailssDTO.length;i++) {
						if(orderdetailssDTO[i].getBuyerid()==buyerid) {
							
							totalprice+=orderdetailssDTO[i].getAmount();
						}
					}
					
					int rewardPoints=(int)totalprice/100;
					if(totalprice>1000000) {//1 reward point=100 rupee -- privileged if he has 10k points--100X10000
						//add to respected table code comes here
						Subscribedproduct subscribedProduct=subscribedProductDTO.createEntity();
						subscribedproductRepository.save(subscribedProduct);
						return new ResponseEntity<String>("product order added successfully!!", HttpStatus.OK);
					}
					else {
					return new ResponseEntity<String>("Not a privileged user!!", HttpStatus.BAD_REQUEST);
						}
					}
				
				public void addProduct(ProductDTO productDTO) {
					
					Product product=productDTO.createEntity();
					System.out.println("adding product to product list");
					productRepository.save(product);
				}
				
				
}

	

