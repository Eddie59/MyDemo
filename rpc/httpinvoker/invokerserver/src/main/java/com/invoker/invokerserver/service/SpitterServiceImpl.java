package com.invoker.invokerserver.service;

import com.invoker.invokerserver.domain.Spitter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SpitterServiceImpl class
 *
 * @author Administrator
 * @date
 */
@Service
public class SpitterServiceImpl implements SpitterService {
    @Override
    public List<Spitter> getRencentSpitters(int count) {
        System.out.println("service:"+count);
        return null;
    }

    @Override
    public void saveSpitter(Spitter spitter) {
        System.out.println("save done");
    }

    @Override
    public Spitter getSpitter(long id) {
        Spitter spitter = new Spitter();
        spitter.setUsername("spitter " + id);
        spitter.setId(id);
        System.out.println(spitter.getUsername());
        System.out.println(spitter);
        return spitter;
    }
}
