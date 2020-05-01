package com.it.wecodeyou.off.service;

import java.util.List;

import com.it.wecodeyou.off.model.OffVO;
import com.it.wecodeyou.product.model.ProductVO;

public interface IOffService {
	
	public Integer insert(ProductVO pvo, OffVO ovo);
	
	/* public List<OffVO> listByUser(MemberVO mvo); */
	
	public List<OffVO> getInfoByAuthor(String offAuthor);
	public List<OffVO> list();
	
	public void update(OffVO ovo);
	
	public void delete(Integer offNo);
	
	public OffVO getOneInfo(Integer offNo);
	
	public OffVO getInfoByProductNo(Long productNo);
}