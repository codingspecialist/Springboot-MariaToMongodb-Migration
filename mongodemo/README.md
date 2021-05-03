# 몽공DB

### ObjectId 사용 이유
```txt
 전통적인 RDBMS에서 Primary key를 만들 때는 DB 서버로 data를 보내서 중복되지 않는 key를 골라서1) 그 값을 key로 저장하는 방식을 이용한다.
 하지만 MongoDB와 같은 분산 database에서는 key를 서버에서 만들지 않고 클라이언트에서 만든다.
 그 이유는 MongoDB가 query를 날릴 shard를 결정하는 방식을 보면 알 수 있다.
 MongoDB는 자신이 필요한 shard에게만 query를 요청한다. 다시 말해서 client에 해당하는 mongos2)가 config server의 data를 토대로 어떤 shard가 어느 범위의 값을 가졌는지를 저장하고 있다가 query를 요청할 때 자신이 필요로 하는 shard에게만 요청한다.
 따라서 shard key에 해당하는 data가 미완성인 상태로 서버에 저장하도록 요청할 수 없고, client가 ObjectId를 생성하여 값을 저장하도록 요청한다.
```

### ObjectId 구성
```txt
4Byte는 시간 정보(timestamp)
5Byte는 Random 값
3Byte는 Random 값이 1씩 증가하는 번호
총 12Byte를 해시한 값이 ObjectId의 구성이다.
```
