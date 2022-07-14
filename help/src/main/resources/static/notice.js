
let cd = 0;

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
    if ($.cookie('token')) {
        $.ajaxSetup({
            headers:{
                'Authorization': $.cookie('token')
            }
        })
    } else {
        window.location.href = '/user/loginView';
    }
    $.ajax({
        type: "POST",
        url: `/user/userinfo`,
        contentType: "application/json",
        success: function (response) {
            const username = response.username;
            const isAdmin = !!response.admin;

            if (!username) {
                window.location.href = '/user/loginView';
            }

            $('#username').text(username);
            if (isAdmin) {
                getNotice(true);
            } else {
                getNotice();
            }
        },
        error: function() {
            window.location.href = '/user/loginView';
        }
    })
})

// 글 정보 가져오기
function getNotice() {
    $('.mycards').empty();

    $.ajax({
        type: 'GET',
        url: `/homepage/notice`,
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
    let text = $('#text').val();
    let data = {'title': title, 'name': name, 'text': text};

    $.ajax({
        type: "POST",
        url: `/homepage/notice`,
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

    let data = {'title':title, 'name':name, 'text': text};
    $.ajax({
        type: "PUT",
        url: `/homepage/notice/${id}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {

        }
    });
}

function deleteOne(id) {

    $.ajax({
        type: "DELETE",
        url: `/homepage/notice/${id}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {

        }
    })
}