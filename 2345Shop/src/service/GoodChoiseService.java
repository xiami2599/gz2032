package service;

import java.util.List;

import dao.entity.GoodChoise;
import dto.Page;

public interface GoodChoiseService {

	public Page<GoodChoise> getOnePage(int currentPage,int pageSize) ;
	public int save(GoodChoise fq);
	public GoodChoise getById(int id);
	public int update(GoodChoise fq);
	public int deleteById(int id) ;
	public List<GoodChoise> souSuoGoods(String name);
	public List<GoodChoise> souSuoGoodsByPrice(double minPrice,double maxPrice);
}
