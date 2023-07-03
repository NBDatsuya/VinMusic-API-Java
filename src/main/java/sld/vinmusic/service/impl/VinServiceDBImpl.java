package sld.vinmusic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sld.vinmusic.dao.VinlikeMapper;
import sld.vinmusic.entity.Vinlike;
import sld.vinmusic.service.VinService;

@Service
public class VinServiceDBImpl implements VinService {
    @Autowired
    private VinlikeMapper dao;

    public boolean isLiked(Vinlike vinlike) {
        var vin = dao.selectByMoodAndAccount(vinlike);
        return vin != null;
    }

    public Vinlike getHistoryVin(Vinlike vinlike){
        return dao.selectByMoodAndAccount(vinlike);
    }

    public int addVin(Vinlike vinlike) {
        return dao.insert(vinlike);
    }

    public int withdrawVin(Vinlike vinlike) {
        return dao.deleteByID(vinlike.getId());
    }
}
