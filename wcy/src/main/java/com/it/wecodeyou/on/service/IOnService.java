package com.it.wecodeyou.on.service;

import java.util.ArrayList;

import com.it.wecodeyou.on.model.OnVO;
import com.it.wecodeyou.product.model.ProductVO;
import com.it.wecodeyou.purchase.model.PurchaseVO;

public interface IOnService {

	public Integer insert(ProductVO pvo, OnVO ovo, ArrayList<Integer> sendTagList);

	public OnVO getOne(Integer productNo);
	
	public String getAuthor(Integer productNo);

	public Integer getDays(PurchaseVO pvo);

}
