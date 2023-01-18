package com.example.demokot.controller

import com.example.demokot.domain.dto.request.Community.BoardModifyRequestDTO
import com.example.demokot.domain.dto.request.Community.BoardRegisterRequestDTO
import com.example.demokot.domain.dto.response.MyResponse
import com.example.demokot.domain.dto.response.MyResponse.Companion.getResponse
import com.example.demokot.domain.dto.response.BoardDTO
import com.example.demokot.domain.entity.community.Board
import com.example.demokot.service.board.BoardService
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/sample/")
class BoardController(
    private val boardService: BoardService
) {

    /** 게시판 리스트 조회 **/
    @GetMapping("/boardList")
    fun boardList(
        @Parameter(name = "pageable", example = "{\"page\": 0,\"size\": 10}")
        @PageableDefault(size = 10) pageable: Pageable
    ): ResponseEntity<MyResponse<List<Board>>> {
        val boardList = boardService.boardList(pageable)

        return getResponse(boardList)
    }

    /**  게시판 상세 **/
    @GetMapping("/detailBoard")
    fun detailBoard(
        @RequestParam board_id: Long
    ): ResponseEntity<MyResponse<BoardDTO>> {
        val boards = boardService.detailBoard(board_id)
        return getResponse(boards)
    }


    /** 게시판 글 작성 **/
    @PostMapping("/postBoard")
    fun postBoard(
        @RequestBody dto: BoardRegisterRequestDTO
    ): ResponseEntity<MyResponse<Long?>> {
        val postBoard = boardService.postBoard(dto)

        return getResponse(postBoard)
    }


    /** 게시판 글 수정 **/
    @PutMapping("/editBoard")
    fun editBoard(
        @RequestBody dto: BoardModifyRequestDTO
    ): ResponseEntity<MyResponse<Boolean>> {
        val result = boardService.editBoard(dto)
        return getResponse(result)
    }


    /**  게시판 글 삭제 **/
    @DeleteMapping("/deleteBoard")
    fun deleteBoard(
        @RequestParam board_id: Long,
        @RequestParam userId: Long,
    ): ResponseEntity<MyResponse<Boolean>> {
        val result = boardService.deleteBoard(board_id, userId)
        return getResponse(result)
    }

}




