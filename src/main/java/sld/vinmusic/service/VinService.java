package sld.vinmusic.service;

import sld.vinmusic.entity.Vinlike;

public interface VinService {
    boolean isLiked(Vinlike vinlike);
    Vinlike getHistoryVin(Vinlike vinlike);
    int addVin(Vinlike vinlike);
    int withdrawVin(Vinlike vinlike);
}
