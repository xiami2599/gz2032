package service.impl;

import dao.BaoYouDao;
import dao.entity.BaoYou;
import dao.impl.BaoYouDaoImpl;
import dto.Page;
import service.BaoYouService;

public class BaoYouServiceImpl implements BaoYouService{
	
	BaoYouDao fqDao = new BaoYouDaoImpl();
	@Override
	public Page<BaoYou> getOnePage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fqDao.getOnePage(currentPage, pageSize);
	}

	@Override
	public int save(BaoYou fq) {
		// TODO Auto-generated method stub
		return fqDao.save(fq);
	}

	@Override
	public BaoYou getById(int id) {
		// TODO Auto-generated method stub
		return fqDao.getById(id);
	}

	@Override
	public int update(BaoYou fq) {
		// TODO Auto-generated method stub
		return fqDao.update(fq);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return fqDao.deleteById(id);
	}


}
