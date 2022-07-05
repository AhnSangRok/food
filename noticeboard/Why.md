## 1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
* 수정과 삭제 모두 param을 이용했다. 
```java
    @PutMapping("/api/notice/{id}")
    public Long updateNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto) {
        noticeService.update(id, requestDto);
        return id;
    }
    @DeleteMapping("/api/notice/{id}")
    public Long deleteNotice(@PathVariable Long id) {
        noticeRepository.deleteById(id);
        return id;
    }
```
* 위의 코도와 같이 주소에 있는 id의 값을 이용해 관련 데이터를 불러와 사용을 했고, 수정같은 경우 req.body를 이용해 JSON형태로 보낸 데이터를 가져와 사용했다.
## 2. 어떤 상황에 어떤 방식의 request를 써야하나요?
* 요청한 데이터를 조회할때 GET을 이용해 JpaRepository를 상속받은 Repository를 List 형태로 가져와 리턴해준다.
* 데이터를 입력할 때 POST를 이용해 DTO를 JSON 형태로 받아와 저장한다.
* 데이터를 수정할 때 PUT을 이용해 변경할 데이터의 ID값을 PARAM을 이용해 주소에 넣어 넘겼고 변경할 데이터는 JSON형태로 넘어와 기존의 값을 찾아 수정한다.
* 데이터를 삭제할 때 DELETE를 이용해 삭제할 데이터의 ID값을 PARAM을 이용해 주소에 넣어 가져와 ID가 같은 데이터를 찾아와 삭제한다.
## 3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
* RESTful한 API를 설계했냐고 물어본다면 중간이라고 생각한다. CRUD를 잘적용했지만 그 이상을 활용하지 못했다고 생각한다. 
그렇다고 생각한 이유는 이번 프로젝트에서 password를 넣어 password를 확인 후 수정과 삭제가 이루어지도록 하는 기능을 데이터를 주고 받을때 
정보를 확인한 후 실행할 수 있다면 좀 더 효율적으로 실행할 수 있을 것이라 생각하기 때문이다.