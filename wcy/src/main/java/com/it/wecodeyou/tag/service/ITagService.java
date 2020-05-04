package com.it.wecodeyou.tag.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.it.wecodeyou.product.model.ProductVO;
import com.it.wecodeyou.tag.model.TagVO;

public interface ITagService {
	
		//select all
		public ArrayList<TagVO> getAllTag() throws SQLException ;
		
		//select one
		public Integer checkTag(String tagName) throws SQLException ;
		
		//search
		public ArrayList<TagVO> checkTagDetail(String q) throws SQLException;
		
		//insert tag
		public boolean insertTag(String tag) throws SQLException ;

		//update delete 추가 작성
		
		//search products by tag
		public ArrayList<ProductVO> searchProductByTag(Integer tagNo) throws SQLException ;
		
		//search all ptagno
		public ArrayList<TagVO> searchPTagNo() throws SQLException;

}
