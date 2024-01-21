package ru.stileyn.taxi.service.impl;

import java.util.List;
import ru.stileyn.taxi.dao.ManufacturerDao;
import ru.stileyn.taxi.lib.Inject;
import ru.stileyn.taxi.lib.Service;
import ru.stileyn.taxi.model.Manufacturer;
import ru.stileyn.taxi.service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Inject
    private ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return manufacturerDao.create(manufacturer);
    }

    @Override
    public Manufacturer get(Long id) {
        return manufacturerDao.get(id).get();
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerDao.getAll();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return manufacturerDao.update(manufacturer);
    }

    @Override
    public boolean delete(Long id) {
        return manufacturerDao.delete(id);
    }
}
