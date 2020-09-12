package service;

import java.util.List;

import dao.entity.Car;

public interface CarService {
	
	public int save(Car car);
	public List<Car> getByUid(int uid);
	public Car getByid(int id);
	public int deleteById(int id) ;

}
