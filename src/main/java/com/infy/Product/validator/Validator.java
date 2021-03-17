package com.infy.Product.validator;

import com.infy.Product.Dto.ProductDTO;


public class Validator {
		public static void validateProduct(ProductDTO product) throws Exception {
			if(!validateName(product.getProductname())) {
				throw new Exception("Validator.INVALID_NAME");
			}
			if(!validateDescription( product.getDescription())) {
				throw new Exception("Validator.INVALID_DESCRIPTION");
			}
			if(!validatePrice(product.getPrice())) {
				throw new Exception("Validator.LESS_PRICE");
			}
			if(!validateStock(product.getStock())) {
				throw new Exception("Validator.INSUFFICENT_NO_OF_STOCK");
			}
			if(!validateImage(product.getImage())) {
				throw new Exception("Validator.INVALID_IMAGE");
			}
		}
	public static Boolean validateName(String name) {
		String regex = "[A-Za-z]+(\\s[A-Za-z]+){,100}*";
		if(name.matches(regex)) {
			return true;
		}
		return false;
	}
	public static Boolean validateDescription(String description) {
		String regex = "[A-Za-z]{0,500}";
		if(description.matches(regex)) {
			return true;
		}
		return false;
	}
	public static Boolean validatePrice(float f) {
		if(f>= 200) {
			return true;
		}
		return false;
	}
	
	public static Boolean validateStock(int stock) {
		
		if(stock>=10) {
			return true;
		}
		return false;
	}
public static Boolean validateImage(String image) {
		
		if(image.endsWith("png") || image.endsWith("jpeg")) {
			return true;
		}
		return false;
	}
	
}

