package com.ecnv.overflow.board.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "board_tb")
@Entity
public class BoardEntity{


    private Long userId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId;
    private String boardContent;
    private String boardTitle;
    private Timestamp createdDate;
    private Timestamp updateDate;

}
