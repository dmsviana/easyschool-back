package br.edu.ifpb.easyschoolback.presentation.controller.contract;

import br.edu.ifpb.easyschoolback.presentation.dtos.course.CourseResponseDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.course.CreateCourseRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.course.UpdateCourseRequestDto;
import br.edu.ifpb.easyschoolback.presentation.dtos.error.ErrorResponseDto;
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

@Tag(name = "Course", description = "Operations related to courses")
@RequestMapping("/courses")
public interface CourseApiContract {

    @Operation(
            summary = "Create a course",
            description = "Create a course entity")
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
    CourseResponseDto create(
            @RequestBody @Valid CreateCourseRequestDto requestDto
    );

    @Operation(
            summary = "Get all courses",
            description = "Get all course entities and their data")
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
    List<CourseResponseDto> getCourses();

    @Operation(
            summary = "Get a course by id",
            description = "Get a course entity by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found - course not found",
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
    @GetMapping("/{courseId}")
    @ResponseStatus(OK)
    CourseResponseDto getCourseById(@PathVariable Long courseId);

    @Operation(
            summary = "Update a course",
            description = "Update a course by id")
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
    @PutMapping("/{courseId}")
    @ResponseStatus(OK)
    CourseResponseDto update(
            @PathVariable Long courseId,
            @RequestBody @Valid UpdateCourseRequestDto requestDto
    );

    @Operation(
            summary = "Delete a course",
            description = "Delete a course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Course deleted successfully"),
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
    @DeleteMapping("/{courseId}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable Long courseId);

    @Operation(
            summary = "Enroll a student in a course",
            description = "Enroll a student in a course by studentId and courseId")
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
    @PostMapping("/{courseId}/enroll/{studentId}")
    @ResponseStatus(OK)
    void enrollStudentInCourse(@PathVariable Long courseId, @PathVariable Long studentId);

    @Operation(
            summary = "Remove a student from a course",
            description = "Remove a student from a course by studentId and courseId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student removed from course successfully"),
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
    @DeleteMapping("/{courseId}/remove/{studentId}")
    @ResponseStatus(NO_CONTENT)
    void removeStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId);
}
