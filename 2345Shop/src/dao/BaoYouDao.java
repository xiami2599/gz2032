package dao;

import dao.entity.BaoYou;
import dto.Page;
/*
 * 包邮接口
 */
public interface BaoYouDao {
	
	
	public Page<BaoYou> getOnePage(int currentPage,int pageSize) ;
	public int save(BaoYou ddq);
	public BaoYou getById(int id);
	public int update(BaoYou ddq);
	public int deleteById(int id) ;

}
