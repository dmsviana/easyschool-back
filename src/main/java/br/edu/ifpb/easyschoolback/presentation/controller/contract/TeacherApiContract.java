package br.edu.ifpb.easyschoolback.presentation.controller.contract;

import br.edu.ifpb.easyschoolback.presentation.dtos.error.ErrorResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.teacher.CreateTeacherRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.teacher.TeacherResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.teacher.UpdateTeacherRequestDto;
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

@Tag(name = "Teacher", description = "Operations related to teachers")
@RequestMapping("/api/v1/teachers")
public interface TeacherApiContract {

    @Operation(
            summary = "Create a teacher",
            description = "Create a teacher entity")
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
    TeacherResponseDto create(
            @RequestBody @Valid CreateTeacherRequestDto requestDto
    );

    @Operation(
            summary = "Get all teachers",
            description = "Get all teacher entities and their data")
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
    @GetMapping("/get-all")
    @ResponseStatus(OK)
    List<TeacherResponseDto> getTeachers();

    @Operation(
            summary = "Get a teacher by id",
            description = "Get a teacher entity by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found - teacher not found",
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
    @GetMapping("/{teacherId}")
    @ResponseStatus(OK)
    TeacherResponseDto getTeacherById(@PathVariable Long teacherId);

    @Operation(
            summary = "Update a teacher",
            description = "Update a teacher by id")
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
    @PutMapping("/{teacherId}")
    @ResponseStatus(OK)
    TeacherResponseDto update(
            @PathVariable Long teacherId,
            @RequestBody @Valid UpdateTeacherRequestDto requestDto
    );

    @Operation(
            summary = "Delete a teacher",
            description = "Delete a teacher by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Teacher deleted successfully"),
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
    @DeleteMapping("/{teacherId}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable Long teacherId);

    @Operation(
            summary = "Assign a course to a teacher",
            description = "Assign a course to a teacher by their ids")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found - teacher or course not found",
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
    @PostMapping("/{teacherId}/assign/{courseId}")
    @ResponseStatus(NO_CONTENT)
    void assignCourseToTeacher(
            @PathVariable Long teacherId,
            @PathVariable Long courseId
    );

    @Operation(
            summary = "Remove a course from a teacher",
            description = "Remove a course from a teacher by their ids")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found - teacher or course not found",
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
    @DeleteMapping("/{teacherId}/remove/{courseId}")
    @ResponseStatus(NO_CONTENT)
    void removeCourseFromTeacher(
            @PathVariable Long teacherId,
            @PathVariable Long courseId
    );
}