package com.example.demokot.domain.dto.request

import com.querydsl.core.annotations.QueryProjection

class MemberDTO @QueryProjection constructor(
    var member_id: Long,
    var username: String,
    var userPw: String,
    var userEmail: String



){
}