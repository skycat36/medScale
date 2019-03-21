package com.takeHospital.service;

import com.takeHospital.domain.Worker;
import com.takeHospital.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService implements UserDetailsService{

    @Autowired
    private WorkerRepository workerRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Worker worker = workerRepository.findByLogin(s);

        if (worker == null){
            throw new UsernameNotFoundException("Worker not found");
        }

        return worker;
    }

    public boolean addWorker(Worker worker){
        Worker userFromDb = workerRepository.findByLogin(worker.getLogin());

        if (userFromDb != null){
            return false;
        }
        workerRepository.save(worker);

        return true;
    }

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public Worker updateProfile(Worker workerReal, Worker workerUpdate) {

        workerReal.setLogin(workerUpdate.getLogin());
        workerReal.setPassword(workerUpdate.getPassword());
        workerReal.setFam(workerUpdate.getFam());
        workerReal.setName(workerUpdate.getName());
        workerReal.setSecName(workerUpdate.getSecName());
        workerReal.setPosition(workerUpdate.getPosition());

        workerRepository.save(workerReal);
        return workerReal;
    }

}
