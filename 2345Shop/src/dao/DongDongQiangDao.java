package dao;


import dao.entity.DongDongQiang;
import dto.Page;

public interface DongDongQiangDao {
	
	public Page<DongDongQiang> getOnePage(int currentPage,int pageSize) ;
	public int save(DongDongQiang ddq);
	public DongDongQiang getById(int id);
	public int update(DongDongQiang ddq);
	public int deleteById(int id) ;

}
