// common
const usericon = document.getElementsByClassName('fa-user');
if (usericon.length !== 0) {
	usericon[0].addEventListener('mouseover', () => {
        document.getElementsByClassName('icon-slide-down')[0].style.bottom='-90%';
        setTimeout(() => {
             document.getElementsByClassName('icon-slide-down')[0].style.zIndex='0';
        }, 300);
    });
    document.getElementsByClassName('icon-slide-down')[0].addEventListener('mouseleave', () => {
        document.getElementsByClassName('icon-slide-down')[0].style.bottom='0';
        document.getElementsByClassName('icon-slide-down')[0].style.zIndex='-1';
    });
}

// productAdd.jsp
function checkFileFormat() {
	const name = addProductForm.name.value;
	const brief = addProductForm.brief.value;
	const detailpage = addProductForm.detailpage.value;
	const index = addProductForm.imgfile.value.lastIndexOf('.');
    const format = addProductForm.imgfile.value.substr(index+1,3);
	if(!name || !brief || !detailpage){
        alert("모든 항목을 빠짐없이 입력해주세요.")
    }else{
        if (format !== 'jpg') {
	        alert('jpg 형식의 파일을 입력해주세요.');
	    } else {
	        addProductForm.submit();
	    }
    }
}

// productList.jsp
function getPage() {
	const page = parseInt(document.getElementsByName('currentPage')[0].value);
	return page;
}
function setPage() {
	const page = parseInt(document.getElementsByName('currentPage')[0].value);
	document.getElementsByName('currentPage')[0].value = page + 1;
}
function getProducts() {
	const page = getPage();
	if (page > document.getElementsByName('totalPages')[0].value) {
		return false;
	}
    const obj = {"page" : page};
    const jsonData = JSON.stringify(obj);

    $.ajax({
        type : 'POST',
        dataType : 'json',
        url : 'http://localhost:8080/DEMO/productListAppend.do',
        data : {
            json : jsonData
        },
        success : function(data) {
            for (let i=0; i<Object.keys(data).length; i++) {
                const ul = document.querySelector('.product-container'),
	                a = document.createElement("a"),
	                div = document.createElement("div"),
	                img = document.createElement("img"),
	                h3 = document.createElement("h3"),
	                p = document.createElement("p");
                a.className = 'item';
                div.className = 'img-wrap';
                a.href = data[i].detailpage;
                img.src = data[i].imgfile;
                h3.innerText = data[i].name;
                p.innerText = data[i].brief;
                div.append(img);
                a.append(div, h3, p);
                ul.append(a);
            }
            setPage();
            console.log(page);
        },
        error : function(e) {
            console.log('ajax err : ', e);
        }
    });
}
$(document).ready(function() {
	if (window.location.pathname === '/DEMO/productList.do') {
		getProducts();
	}
});
function selectAll(el) {
	const checkboxes = document.getElementsByName('delete_num');
	checkboxes.forEach((checkbox) => {
		checkbox.checked = el.checked;
	});
}

// signUp.jsp
function checkId() {
    const id = joinForm.userid.value;
    if (id === "") {
        alert("아이디를 입력하세요.");
    } else {
        window.open("/DEMO/signUpCheckId.do?userid="+id, "","width=350 height=200 left=300 top=200");
    }
}
function checkPassword() {
    const pw1 = joinForm.userpw.value,
        pw2 = joinForm.re_userpw.value,
        guide = document.getElementById('pw-guide');
    if (pw2 === "") {
        guide.innerText = "";
        return false;
    }
    if (pw1 !== pw2) {
        guide.innerText = '불일치';
        guide.style.color = 'red';
    } else {
        guide.innerText = '일치';
        guide.style.color = '#00a100';
    }
}
function checkInputValue() {
    const readOnly = joinForm.userid.readOnly;
    if (!readOnly) {
        alert('아이디 중복체크를 해주세요.');
        return false;
    }
    const guide = document.getElementById('pw-guide');
    if (guide.innerText !== '일치' ) {
        alert('비밀번호를 확인해주세요.');
        return false;
    }
    const err_msg = document.getElementsByClassName('err_msg')[0];
    if(joinForm.userid.value === "" || joinForm.userpw.value === "" || joinForm.name.value === "" || joinForm.mail.value === "") {
        err_msg.style.display = 'block';
        return false;
    }
    joinForm.submit();
}

// signUpCheckId.jsp
function checkIdFormClose(id) {
	opener.joinForm.userid.value = id;
	opener.joinForm.userid.readOnly = true;
	window.close();
}
function isEmpty() {
	const id = checkIdForm.userid.value;
    if (id === "") {
        alert("아이디를 입력하세요.");
    } else {
    	checkIdForm.submit();
    }
}

// findId.jsp
function findId() {
    const mail = document.getElementsByName('mail')[0].value;
    const obj = {"mail" : mail};
    const jsonData = JSON.stringify(obj);

    $.ajax({
        type : 'POST',
        dataType : 'json',
        url : 'http://localhost:8080/DEMO/findId.do',
        data : {
            json : jsonData
        },
        success : function(data) {
            const result_div = document.querySelector('#result');
            while (result_div.hasChildNodes()) {
                result_div.removeChild(result_div.firstChild);
            }
            if (Object.keys(data).length === 0) {
                const div = document.createElement('div');
                div.className = 'null_id';
                div.innerText = '존재하지 않는 이메일입니다.';
                result_div.append(div);
            } else {
                for (let i=0; i<Object.keys(data).length; i++) {
                    const div = document.createElement('div');
                    div.className = 'id';
                    div.innerText = 'ID : ' + data[i];
                    result_div.append(div);
                }
            }
        },
        error : function(e) {
            console.log('ajax err : ', e);
        }
    });
}

// boardRead.jsp
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    const regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    	results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
function getCommentList() {
    const brdNo = getParameterByName('brdNo');
	const obj = {"brdNo" : brdNo};
    const jsonData = JSON.stringify(obj);

    $.ajax({
        type : 'POST',
        dataType : 'json',
        url : 'http://localhost:8080/DEMO/commentList.do',
        data : {
            json : jsonData
        },
        success : function(data) {
            const cmt_count = document.querySelector('.cmt-count');
            cmt_count.innerText = Object.keys(data).length;
            for (let i=0; i<Object.keys(data).length; i++) {
                const ul = document.querySelector('.comment-list'),
                    li = document.createElement('li'),
                    div1 = document.createElement('div'),
                    div2 = document.createElement('div'),
                    div3= document.createElement('div'),
                    div4= document.createElement('div'),
                    span1 = document.createElement('span'),
                    span2 = document.createElement('span'),
                    input1 = document.createElement('input'),
                    input2 = document.createElement('input');
                div1.className = 'cmt-info';
                span1.className = 'cmt-writer';
                span1.innerText = data[i].name;
                span2.className = 'cmt-regdate';
                span2.innerText = data[i].regdate;
                div4.className = 'cmt-con';
                div4.innerText = data[i].content;
                if (sessionStorage.getItem('currentId') === data[i].userid ) {
                    input1.type = 'hidden';
                    input1.name = 'cmtNo';
                    input1.value = data[i].num;
                    input2.type = 'button';
                    input2.value = '삭제';
                    input2.className = 'cmt-delete-btn';
                    input2.addEventListener('click', function(){
                    	deleteComment(this)
                    });
                    div3.append(input1, input2);
                }
                div2.append(span1, span2);
                div1.append(div2, div3);
                li.append(div1, div4);
                ul.append(li);
            }
        },
        error : function(e) {
            console.log('ajax err : ', e);
        }
    });
}
$(document).ready(function() {
	if (window.location.pathname === '/DEMO/boardRead.do') {
		getCommentList();
	}
});
function writeComment() {
    const userid = sessionStorage.getItem('currentId'),
        name = sessionStorage.getItem('currentName'),
        content = document.getElementsByName('content')[0].value,
        parentnum = document.getElementsByName('parentnum')[0].value;
    if(content === '') {
    	alert('내용을 입력해주세요.');
    	return false;
    }
        
    const obj = {
        "userid" : userid,
        "name" : name,
        "content" : content,
        "parentnum" : parentnum
    };
    const jsonData = JSON.stringify(obj);

    $.ajax({
        type : 'POST',
        dataType : 'json',
        url : 'http://localhost:8080/DEMO/commentWrite.do',
        data : {
            json : jsonData
        },
        success : function(data) {
            console.log(data.result);
            if (data.result) {
            	document.getElementsByName('content')[0].value = '';
            	const target = document.querySelector('.comment-list');
            	while (target.hasChildNodes()) {
                    target.removeChild(target.firstChild);
                }
                getCommentList();
            } else {
                alert('댓글 작성에 실패하였습니다. 다시 시도해 주세요.');
            }
        },
        error : function(e) {
            console.log('ajax err : ', e);
        }
    });
}
function deleteComment(el) {
    if (!confirm('정말 삭제하시겠습니까?')) return false;
	const cmtNo = el.previousSibling.value;
    const obj = {"cmtNo" : cmtNo};
    const jsonData = JSON.stringify(obj);

    $.ajax({
        type : 'POST',
        dataType : 'json',
        url : 'http://localhost:8080/DEMO/commentDelete.do',
        data : {
            json : jsonData
        },
        success : function(data) {
            if (data.result) {
            	const target = document.querySelector('.comment-list');
            	while (target.hasChildNodes()) {
                    target.removeChild(target.firstChild);
                }
                getCommentList();
            } else {
                alert('댓글 삭제에 실패하였습니다. 다시 시도해 주세요.');
            }
        },
        error : function(e) {
            console.log('ajax err : ', e);
        }
    });
}