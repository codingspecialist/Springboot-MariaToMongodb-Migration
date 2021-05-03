package com.cos.mongodemo.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * 참고
 * 
 * @DBRef annotation
 */

@Data
@Document
public class Users {

    // 실제 id의 타입은 org.bson.types.ObjectId; 타입인데 String으로 하면 알아서 매핑해서 hash값을 찾아준다.
    @Id
    private String _id;

    // 이 부분은 mariadb와 동일하게 맞춤.
    private Long id;
    private String username;
    private String password;

    private List<Board> boards;

    @Builder
    public Users(Long id, String username, String password, List<Board> boards) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.boards = boards;
    }
}
