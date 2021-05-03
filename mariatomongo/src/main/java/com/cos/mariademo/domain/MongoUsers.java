package com.cos.mariademo.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document
public class MongoUsers {
    // 실제 id의 타입은 org.bson.types.ObjectId; 타입인데 String으로 하면 알아서 매핑해서 hash값을 찾아준다.
    @Id
    private String _id;

    // 이 부분은 mariadb와 동일하게 맞춤.
    private Long id;
    private String username;
    private String password;

    private List<Board> boards;
    
    // 몽고 DB에 timezone이 UTC로 되어 있어서 값이 들어갈 때 String으로 안하면 9시간이 추가되어 들어감
    // 공식 문서를 봐도 UTC 로 저장되니 알아서 Application layer에서 수정해서 사용하라고 나와 있네요.
    // String으로 저장할께 아니면 시간을 9시간 더해서 save하면 됨.
    private String createDate;

    @Builder
    public MongoUsers(Long id, String username, String password, List<Board> boards, String createDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        
        // boards안에 user를 null 처리하지 않으면 계속 당겨오게 된다.
        boards = boards.stream().map((b)->{
        	b.setUser(null);
        	return b;
        }).collect(Collectors.toList());
        
        this.boards = boards;
        this.createDate = createDate;
    }
}
