/*  // 不使用 jquery 的 非ajax post 请求
function add(elem) {
    var temp = document.createElement("form")
    temp.action = "/add"
    temp.method = "post"
    temp.style.display = "none"
    document.body.appendChild(temp)

    temp.submit()
    return temp
}
*/


 // jquery 中的 非 ajax post 请求
function add(elem) {
    var form;
    form = $('<form />', {
        action: '/add',
        method: 'POST',
        style: 'display: none;'
    });
    form.appendTo('body').submit();
}


function alter(elem) {
    var row = elem.parentNode.parentNode.rowIndex - 1
    var temp = document.createElement("form")
    temp.action = "/alter"
    temp.method = "post"
    temp.style.display = "none"

    var opt = document.createElement("textarea")
    opt.name = "row"
    opt.value = row.toString()
    temp.appendChild(opt)
    document.body.appendChild(temp)

    temp.submit()
    return temp
}

/* // 不使用jquery 的 ajax (比较麻烦)
function del(elem) {
    var row = elem.parentNode.parentNode.rowIndex - 1
    var tr = elem.parentNode.parentNode
    var tbody = tr.parentNode
    tbody.removeChild(tr)  // 在前端中删除这一行

    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = callBack
    xmlHttpRequest.open("post", "/del", true)
    xmlHttpRequest.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded")
    xmlHttpRequest.send("row=" + row)
    //history.go(0)
}
*/

// 使用 jquery 简化 ajax 型 post 请求
function del(elem) {
    var tr = elem.parentNode.parentNode
    var row = tr.rowIndex - 1
    $.post('/del', { row: row }, sucdel(tr))
}

// 在后端成功删除数据后 更新前端数据的方法
function sucdel(tr) {
    tr.parentNode.removeChild(tr)  // 在前端中删除这一行
}


// ajax 的另一种写法：
/*
function del(elem) {
    var tr = elem.parentNode.parentNode
    var row = tr.rowIndex - 1
    tr.parentNode.removeChild(tr)  // 在前端中删除这一行

    $.ajax({
        type: 'POST',
        data: { row: row },
        url: '/del',
    })
}
 */

function callBack() {
    // 暂时不需要执行回调操作
}
