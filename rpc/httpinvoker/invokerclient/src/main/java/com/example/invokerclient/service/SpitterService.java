package com.example.invokerclient.service;



import com.example.invokerclient.domain.Spitter;

import java.util.List;

/**
 * SpitterService class
 *
 * @author Administrator
 * @date
 */
public interface SpitterService {

    List<Spitter> getRencentSpitters(int count);

    void saveSpitter(Spitter spitter);

    Spitter getSpitter(long id);

}
