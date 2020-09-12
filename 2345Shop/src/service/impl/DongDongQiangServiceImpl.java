package service.impl;

import dao.DongDongQiangDao;
import dao.entity.DongDongQiang;
import dao.impl.DongDongQiangDaoImpl;
import dto.Page;
import service.DongDongQiangService;

public class DongDongQiangServiceImpl implements DongDongQiangService {

	DongDongQiangDao ddqDao = new DongDongQiangDaoImpl();
	@Override
	public Page<DongDongQiang> getOnePage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return ddqDao.getOnePage(currentPage, pageSize);
	}

	@Override
	public int save(DongDongQiang ddq) {
		// TODO Auto-generated method stub
		return ddqDao.save(ddq);
	}

	@Override
	public DongDongQiang getById(int id) {
		// TODO Auto-generated method stub
		return ddqDao.getById(id);
	}

	@Override
	public int update(DongDongQiang ddq) {
		// TODO Auto-generated method stub
		return ddqDao.update(ddq);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return ddqDao.deleteById(id);
	}

}
