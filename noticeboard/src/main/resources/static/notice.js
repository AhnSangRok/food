let pscheck = 0;
let cd = 0;
function getPassword(id) {
    let password = $(`#${id}-password`).val();
    if (password == ""){
        alert("비밀번호를 입력해주세요");
        return;
    }
    console.log(password)
    $.ajax({
        type: 'GET',
        url: '/api/notice',
        async:false,
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let notice = response[i];
                if (notice['id']==id){
                    if (notice['password'] == password){
                        pscheck = true;
                        console.log(pscheck);
                        return pscheck;
                    }else {
                        pscheck = false;
                        alert("비밀번호를 다시 입력해주세요");
                        console.log(pscheck);
                        return pscheck;
                    }
                    break;
                }
            }
        }
    })
}
function updateForm(id) {
    $(`.${id}-text-form`).hide();
    $(`#${id}-content`).show();
    $(`#${id}-delete`).show();
    $(`#${id}-submit`).show();
    $(`#${id}-back`).show();
    $(`#${id}-edit`).hide();
    $(`#${id}-update`).hide();
}

function showEdits(id) {
    if (cd == 0){
        $(`.${id}-text-form`).show();
        cd = 1;
    }else{
        $(`.${id}-text-form`).hide();
        cd = 0;
    }
}

function hideEdits(id) {
    $(`#${id}-content`).hide();
    $(`.${id}-text-form`).hide();
    $(`#${id}-delete`).hide();
    $(`#${id}-submit`).hide();
    $(`#${id}-back`).hide();
}

function backForm(id) {
    $(`.${id}-text-form`).hide();
    $(`#${id}-content`).hide();
    $(`#${id}-delete`).hide();
    $(`#${id}-submit`).hide();
    $(`#${id}-back`).hide();
    $(`#${id}-edit`).show();
    $(`#${id}-update`).show();
}

$(document).ready(function () {
    getNotice();
})

// 글 정보 가져오기
function getNotice() {
    $('.mycards').empty();

    $.ajax({
        type: 'GET',
        url: '/api/notice',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let notice = response[i];
                let id = notice['id'];
                let title = notice['title'];
                let name = notice['name'];
                let text = notice['text'];
                let modifiedAt = notice['modifiedAt'];
                addHTML(id, title, name, text, modifiedAt);
            }
        }
    })
}

function addHTML(id, title, name, text, modifiedAt) {
    let temp_html = `<div class="card">
                        <div class="card-body">
                            <blockquote class="blockquote mb-0" id="title_view">
                                <p><span id="${id}-title">${title}</span> - <span id="${id}-date">${modifiedAt}</span></p>
                                <div class="${id}-text-form">
                                    <p id="text-line">${text}</p>
                                </div>
                                <div id="${id}-content" class="content">
                                    <div class="form-floating mb-3" id="${id}-ps">
                                        <input type="password" class="form-control" id="${id}-password" placeholder="url">
                                        <label for="floatingInput">비밀번호</label>
                                    </div>
                                    <div id="${id}-editarea" class="edit">
                                        <textarea id="${id}-textarea" class="te-edit" name="" id="" cols="30" rows="5">${text}</textarea>
                                    </div>
                                </div>
                
                                <footer class="blockquote-footer" id="${id}-name">${name}</footer>
                                <div class="footer">
                                    <button class="show" id="${id}-edit" alt="" onclick="showEdits('${id}')">자세히보기</button>
                                    <button class="upfrom" id="${id}-update" alt="" onclick="updateForm('${id}')">수정</button>
                                    <button class="del" id="${id}-delete" alt="" onclick="deleteOne('${id}')">삭제</button>
                                    <button class="update" id="${id}-submit" alt="" onclick="updateEdits('${id}')">수정</button>
                                    <button class="bkfrom" id="${id}-back" alt="" onclick="backForm('${id}')">돌아가기</button>
                                </div>
                            </blockquote>
                        </div>
                    </div>`
    $('.mycards').append(temp_html);
    hideEdits(id);
}

function writeNotice() {
    let title = $('#title').val();
    let name = $('#name').val();
    let password = $('#password').val();
    let text = $('#text').val();

    let data = {'title': title, 'name': name, 'password': password, 'text': text};

    $.ajax({
        type: "POST",
        url: "/api/notice",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지가 성공적으로 작성되었습니다.');
            window.location.reload();
        }
    });
}

function updateEdits(id) {
    let title = $(`#${id}-title`).text();
    let name = $(`#${id}-name`).text();
    let text = $(`#${id}-textarea`).val();
    let password = $(`#${id}-password`).val();

    getPassword(id);

    if (pscheck == 0){
        return;
    }else if(pscheck == false){
        return;
    }

    let data = {'title':title, 'name':name, 'text': text, 'password' :password};
    $.ajax({
        type: "PUT",
        url: `/api/notice/${id}`,
        contentType: "application/json",
        async: false,
        data: JSON.stringify(data),
        success: function (response) {
            alert('내용 변경에 성공하였습니다.');
            window.location.reload();
        }
    });
}


function deleteOne(id) {
    getPassword(id);

    if (pscheck == 0){
        return;
    }else if(pscheck == false){
        return;
    }

    $.ajax({
        type: "DELETE",
        url: `/api/notice/${id}`,
        async: false,
        success: function (response) {
            alert('메시지 삭제에 성공하였습니다.');
            window.location.reload();
        }
    })
}