package com.example.demokot.service.board

import com.example.demokot.domain.dto.request.Community.BoardModifyRequestDTO
import com.example.demokot.domain.dto.request.Community.BoardRegisterRequestDTO
import com.example.demokot.domain.dto.response.BoardDTO
import com.example.demokot.domain.entity.community.Board
import org.springframework.data.domain.Pageable

interface BoardService {

    /**
     * 제목 내용으로 검색
     * */
    fun searchBoards(keyword: String?): List<BoardDTO>


    /**
     *게시글 리스트 조회
     **/
    fun boardList(pageable: Pageable): List<Board>


    /**
     *게시글 상세 조회
     **/
    fun findDetailBoard(boardId: Long): BoardDTO


    /**
     *게시글 작성
     **/
    fun postBoard(dto: BoardRegisterRequestDTO): Long?


    /**
     *게시글 수정
     **/
    fun editBoard(dto: BoardModifyRequestDTO): Boolean


    /**
     *삭제
     **/
    fun deleteBoard(userId:Long, boardId: Long): Boolean
}


