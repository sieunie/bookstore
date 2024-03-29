package Project.Bookstore.Controller;

import Project.Bookstore.Data.Dto.Req.BookPostReq;
import Project.Bookstore.Data.Dto.Res.BookGetRes;
import Project.Bookstore.Service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "책 정보 게시 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "null은 허용되지 않음")
    })
    public ResponseEntity<HttpStatus> post(@ParameterObject @ModelAttribute @Valid BookPostReq bookPostReq, @RequestPart MultipartFile image) {
        return bookService.post(bookPostReq, image);
    }

    @GetMapping
    @Operation(summary = "책 정보 id로 검색해서 조회 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 id")
    })
    public ResponseEntity<BookGetRes> get(@RequestParam Integer id) {
        return bookService.get(id);
    }

    @GetMapping("/list")
    @Operation(summary = "책 정보 리스트로 조회 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공")
    })
    public ResponseEntity<List<BookGetRes>> getList() {
        return bookService.getList();
    }

    @DeleteMapping
    @Operation(summary = "책 정보 id로 검색해서 삭제 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 id")
    })
    public ResponseEntity<HttpStatus> delete(@RequestParam Integer id) {
        return bookService.delete(id);
    }
}
