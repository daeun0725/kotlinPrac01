package com.example.demokot.controller

import com.example.demokot.domain.dto.request.CommentRequestDTO
import com.example.demokot.domain.dto.request.MyResponse
import com.example.demokot.domain.dto.request.MyResponse.Companion.getResponse
import com.example.demokot.service.comment.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/sample01")
class CommentController(
    private val commentService: CommentService
) {


    /** 댓글 조회  **/



    /** 댓글 작성 **/
    @PostMapping("/postComment")
    fun postBoardComment(
        @RequestBody dto: CommentRequestDTO
    ): ResponseEntity<MyResponse<Long?>> {
        val postBoardComment = commentService.postComment(dto)
        return getResponse(postBoardComment)
    }


    /** 댓글 수정 **/
    @PutMapping("/editComment")
    fun editBoardComment(
        @RequestBody dto: CommentRequestDTO
    ): ResponseEntity<MyResponse<Boolean>>{
        val result = commentService.editComment(dto)
        return getResponse(result)
    }


    /** 댓글 삭제  **/

}