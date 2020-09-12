package dao;

import dao.entity.FengQiang;
import dto.Page;

public interface FengQiangDao {

	public Page<FengQiang> getOnePage(int currentPage,int pageSize) ;
	public int save(FengQiang fq);
	public FengQiang getById(int id);
	public int update(FengQiang fq);
	public int deleteById(int id) ;
}
