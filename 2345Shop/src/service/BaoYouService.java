package service;

import dao.entity.BaoYou;
import dto.Page;

public interface BaoYouService {
	
	public Page<BaoYou> getOnePage(int currentPage,int pageSize) ;
	public int save(BaoYou fq);
	public BaoYou getById(int id);
	public int update(BaoYou fq);
	public int deleteById(int id) ;

}
