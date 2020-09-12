package service.impl;

import dao.FengQiangDao;
import dao.entity.FengQiang;
import dao.impl.FengQiangDaoImpl;
import dto.Page;
import service.FengQiangService;

public class FengQiangServiceImpl implements FengQiangService{

	FengQiangDao fqDao = new FengQiangDaoImpl();
	@Override
	public Page<FengQiang> getOnePage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fqDao.getOnePage(currentPage, pageSize);
	}

	@Override
	public int save(FengQiang fq) {
		// TODO Auto-generated method stub
		return fqDao.save(fq);
	}

	@Override
	public FengQiang getById(int id) {
		// TODO Auto-generated method stub
		return fqDao.getById(id);
	}

	@Override
	public int update(FengQiang fq) {
		// TODO Auto-generated method stub
		return fqDao.update(fq);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return fqDao.deleteById(id);
	}

}
