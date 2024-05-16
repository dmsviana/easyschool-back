package br.edu.ifpb.easyschoolback.presentation.controller;


import br.edu.ifpb.easyschoolback.business.service.DashboardService;
import br.edu.ifpb.easyschoolback.presentation.controller.contract.DashboardApiContract;
import br.edu.ifpb.easyschoolback.presentation.dtos.dashboard.DashboardDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@RestController
@RequiredArgsConstructor
public class DashboardController implements DashboardApiContract {

    private final DashboardService dashboardService;


    @Override
    public DashboardDataDto getDashboardData() {
        return dashboardService.getDashboardData();
    }


}
