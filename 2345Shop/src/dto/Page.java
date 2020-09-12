package dto;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	private int currentPage;//当前页数
	private int totalPage;//总共页数
	private int count;//总共记录
	private int pageSize;//当前页数的记录数
	private List<T> lists = new ArrayList<>();//当前页面有几个数据

	public Page() {
		super();
	}
	
	public Page(int currentPage, int count, int pageSize, List<T> lists) {
		super();
		this.currentPage = currentPage;
		this.count = count;
		this.pageSize = pageSize;
		this.lists = lists;
	}

	public Page(int currentPage, int totalPage, int count, int pageSize, List<T> lists) {
		super();
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.count = count;
		this.pageSize = pageSize;
		this.lists = lists;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		if(count%pageSize==0){
			totalPage = count/pageSize;
		}else {
			totalPage = count/pageSize+1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getLists() {
		return lists;
	}
	public void setLists(List<T> lists) {
		this.lists = lists;
	}
	
}
