package br.edu.ifpb.easyschoolback.presentation.controller.contract;

import br.edu.ifpb.easyschoolback.presentation.dtos.grade.CreateGradeRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.grade.GradeResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.error.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Grade", description = "Operations related to grades")
@RequestMapping("/grades")
public interface GradeApiContract {

    @Operation(
            summary = "Assign a grade to a student",
            description = "Assign a grade to a student by studentId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - see the error message",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found - student not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/{studentId}")
    @ResponseStatus(CREATED)
    GradeResponseDto assignGradeToStudent(
            @PathVariable Long studentId,
            @RequestBody @Valid CreateGradeRequestDto gradeRequest
    );
}