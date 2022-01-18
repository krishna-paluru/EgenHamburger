package com.krishna.TexasHamburger.service.impl;
import com.krishna.TexasHamburger.model.ExecutionTime;
import com.krishna.TexasHamburger.repository.ExecutionTimeRepository;
import com.krishna.TexasHamburger.service.ExecutionTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class ExecutionTimeServiceImpl implements ExecutionTimeService {

    @Autowired
    private ExecutionTimeRepository executionTimeRepository;
    @Override
    public List<ExecutionTime> getExecutionTimes() {
        List<ExecutionTime> times =  executionTimeRepository.findAll();
//        times.sort(Comparator.comparing(ExecutionTime::getTime));
        return times;

    }
}
