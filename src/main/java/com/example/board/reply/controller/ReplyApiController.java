package com.example.board.reply.controller;

import com.example.board.crud.CRUDAbstractApiController;
import com.example.board.reply.db.ReplyEntity;
import com.example.board.reply.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyApiController extends CRUDAbstractApiController<ReplyDto, ReplyEntity> {

    /*

    private final ReplyService replyService;

    @PostMapping("")
    public ReplyEntity create(
            @Valid
            @RequestBody ReplyRequest replyRequest
    ){
        return replyService.create(replyRequest);
    }

    */

}
