package service.impl;

import java.util.List;

import dao.CarDao;
import dao.entity.Car;
import dao.impl.CarDaoImpl;
import service.CarService;

public class CarServiceImpl implements CarService {

	CarDao cd = new CarDaoImpl();
	@Override
	public int save(Car car) {
		// TODO Auto-generated method stub
		return cd.save(car);
	}

	@Override
	public List<Car> getByUid(int uid) {
		// TODO Auto-generated method stub
		return cd.getByUid(uid);
	}

	@Override
	public Car getByid(int id) {
		// TODO Auto-generated method stub
		return cd.getByid(id);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return cd.deleteById(id);
	}

}
