package com.it.wecodeyou.tag.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.wecodeyou.board.model.ArticleVO;
import com.it.wecodeyou.product.model.ProductVO;
import com.it.wecodeyou.tag.model.TagVO;
import com.it.wecodeyou.tag.repository.ITagMapper;

@Service
public class TagService implements ITagService{

	@Autowired
	ITagMapper dao;
	
	@Override
	public ArrayList<TagVO> getAllTag()  throws SQLException {
		return dao.getAllTag();
	}
	
	@Override
	public String getTagName(Integer tagNo) throws SQLException {
		return dao.getTagName(tagNo);
	}

	@Override
	public Integer checkTag(String tagName)  throws SQLException {
		return dao.checkTag(tagName);
	}
	
	@Override
	public ArrayList<TagVO> checkTagDetail(String q) throws SQLException {
		return dao.checkTagDetail(q);
	}

	@Override
	public boolean insertTag(String tag)  throws SQLException {
		
		try {
			dao.insertTag(tag);
		} catch (Exception e) {
			System.out.println("dao insertTag Exeption!: " + tag);
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<ProductVO> searchProductByTag(Integer tagNo) throws SQLException {
		return  dao.searchProductByTag(tagNo);
	}

	@Override
	public ArrayList<ArticleVO> searchArticleByTag(Integer tagNo) throws SQLException {
		return dao.searchArticleByTag(tagNo);
	}

	@Override
	public ArrayList<TagVO> searchPTagNo() throws SQLException {
		return dao.searchPTagNo();
	}

	@Override
	public ArrayList<TagVO> searchATagNo() throws SQLException {
		return dao.searchATagNo();
	}

	@Override
	public ArrayList<TagVO> searchAPTagNo() throws SQLException {
		return dao.searchAPTagNo();
	}



	

}
