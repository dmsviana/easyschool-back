package br.edu.ifpb.easyschoolback.presentation.controller.contract;

import br.edu.ifpb.easyschoolback.presentation.dtos.dashboard.DashboardDataDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.error.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "Dashboard", description = "Operations related to dashboard")
@RequestMapping("/dashboard")
public interface DashboardApiContract {

    @Operation(
            summary = "Get dashboard data",
            description = "Get dashboard data including total students, total teachers, total courses, and total students on current month")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping
    @ResponseStatus(OK)
    DashboardDataDto getDashboardData();
}