package service.impl;

import java.util.List;

import dao.GoodChoiseDao;
import dao.entity.GoodChoise;
import dao.impl.GoodChoiseDaoImpl;
import dto.Page;
import service.GoodChoiseService;

public class GoodChoiseServiceImpl implements GoodChoiseService{
	
	GoodChoiseDao fqDao = new GoodChoiseDaoImpl();
	@Override
	public Page<GoodChoise> getOnePage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fqDao.getOnePage(currentPage, pageSize);
	}

	@Override
	public int save(GoodChoise fq) {
		// TODO Auto-generated method stub
		return fqDao.save(fq);
	}

	@Override
	public GoodChoise getById(int id) {
		// TODO Auto-generated method stub
		return fqDao.getById(id);
	}

	@Override
	public int update(GoodChoise fq) {
		// TODO Auto-generated method stub
		return fqDao.update(fq);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return fqDao.deleteById(id);
	}

	@Override
	public List<GoodChoise> souSuoGoods(String name) {
		// TODO Auto-generated method stub
		return fqDao.souSuoGoods(name);
	}

	@Override
	public List<GoodChoise> souSuoGoodsByPrice(double minPrice, double maxPrice) {
		// TODO Auto-generated method stub
		return fqDao.souSuoGoodsByPrice(minPrice, maxPrice);
	}


}
