package com.krishna.TexasHamburger.controller;
import com.krishna.TexasHamburger.model.Category;
import com.krishna.TexasHamburger.model.ExecutionTime;
import com.krishna.TexasHamburger.service.ExecutionTimeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/TexasHamburger")
public class ExecutionTimeController {
    @Autowired
    private ExecutionTimeService executionTimeService;
    Logger logger= LoggerFactory.getLogger(ExecutionTimeController.class);

    @ApiOperation(value = "This Api gives the exuction time of every Api", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = " Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/getExecutionTime")
    public List<ExecutionTime> getExecutionTimes()
    {
        logger.trace("getExecutionTime Method accessed");
        return executionTimeService.getExecutionTimes();
    }


}
