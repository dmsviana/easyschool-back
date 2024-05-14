package br.edu.ifpb.easyschoolback.presentation.controller.contract;


import br.edu.ifpb.easyschoolback.presentation.dtos.student.CreateStudentRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.error.ErrorResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.StudentResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.student.UpdateStudentRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Student", description = "Operations related to students")
@RequestMapping("/api/v1/students")
public interface StudentApiContract {

    @Operation(
            summary = "Create a student",
            description = "Create a student entity")
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
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PostMapping
    @ResponseStatus(CREATED)
    StudentResponseDto create(
            @RequestBody @Valid CreateStudentRequestDto requestDto
    );

    @Operation(
            summary = "Get all students",
            description = "Get all student entities and their data")
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
    List<StudentResponseDto> getStudents();

    @Operation(
            summary = "Get a student by id",
            description = "Get a student entity by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
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
    @GetMapping("/{studentId}")
    @ResponseStatus(OK)
    StudentResponseDto getStudentById(@PathVariable Long studentId);

    @Operation(
            summary = "Update a student",
            description = "Update a student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found - see the error message",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - see the error message",
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
    @PutMapping("/{studentId}")
    @ResponseStatus(OK)
    StudentResponseDto update(
            @PathVariable Long studentId,
            @RequestBody @Valid UpdateStudentRequestDto requestDto
    );

    @Operation(
            summary = "Delete a student",
            description = "Delete a student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found - see the error message",
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
    @DeleteMapping("/{studentId}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable Long studentId);


}