function add() {
    const elementID = "tb"
    const tagName = "tr"
    const jsondata = "jsonText"
    var trObj = document.createElement(tagName);
    const len = document.getElementById(elementID).rows.length
    trObj.id = new Date().getTime();
    trObj.innerHTML = "<td >" + (len + 1) + "</td>" +
        "<td contenteditable='true'>0</td>" +
        "<td contenteditable='true'>0</td>" +
        "<td contenteditable='true'>0</td>" +
        "<td contenteditable='true'>0</td>"
    document.getElementById(elementID).appendChild(trObj)
}

//给特殊行李进行添加
function add2() {
    const elementID = "tb2"
    const tagName = "tr"
    const jsondata = "jsonText2"
    var trObj = document.createElement(tagName);
    const len = document.getElementById(elementID).rows.length
    trObj.id = new Date().getTime();
    curid = 'selected' + (len + 1)
    trObj.innerHTML = "<td >" + (len + 1) + "</td>" +
        "<td >" +
        "                                                <select id = " + curid + " class=\"form-control\">\n" +
        "                                                    <option selected=\"One\"> type1</option>\n" +
        "                                                    <option value=\"TWO\"> type2</option>\n" +
        "                                                    <option value=\"THREE\"> type3</option>\n" +
        "                                                    <option value=\"FOUR\"> type4</option>\n" +
        "                                                    <option value=\"FIVE\"> type5</option>\n" +
        "                                                    <option value=\"SIX\"> type6</option>\n" +
        "                                                    <option value=\"SEVEN\"> type7</option>\n" +
        "                                                    <option value=\"EIGHT\"> type8</option>\n" +
        "                                                    <option value=\"NINE\"> type9</option>\n" +
        "                                                </select>\n" +
        "</td>" +
        "<td contenteditable='true'>0</td>"
    document.getElementById(elementID).appendChild(trObj)
}

//提交前将表的数据写入隐藏按钮中，然后进行提交
function myfinish() {
    var ret = true;
    const formID = "ticketform"
    // 普通行李数据写入
    const elementID = "table1"
    const tbObj = document.getElementById(elementID)
    const jsondata = "jsonText"
    var rows = tbObj.rows.length; //获得行数(包括thead)
    var colums = tbObj.rows[0].cells.length; //获得列数
    const dataObj = []
    for (var i = 1; i < rows; i++) {
        var tempObj = {
            id: 0,
            x: 0,
            y: 0,
            z: 0,
            weight: 0
        }
        tempObj.id = tbObj.rows[i].cells[0].innerHTML;
        tempObj.x = tbObj.rows[i].cells[1].innerHTML;
        tempObj.y = tbObj.rows[i].cells[2].innerHTML;
        tempObj.z = tbObj.rows[i].cells[3].innerHTML;
        tempObj.weight = tbObj.rows[i].cells[4].innerHTML;
        var x = Number(tempObj.x)
        var y = Number(tempObj.y)
        var z = Number(tempObj.z)
        var weight = Number(tempObj.weight)
        if (isNaN(x) || isNaN(y) || isNaN(z) || isNaN(weight) || x <= 0 || y <= 0 || z <= 0 || weight <= 0
            || weight > 32) {
            alert("第" + tempObj.id + "普通包中，存在非法字符,请修改")
            ret = false
            break
        }

        dataObj[dataObj.length] = tempObj
    }
    console.log(dataObj)
    document.getElementById(jsondata).setAttribute('value', JSON.stringify(dataObj));

    //特殊行李数据写入
    const elementSpecialID = "table2"
    const tbSpecialObj = document.getElementById(elementSpecialID)
    const jsonSpecialdata = "jsonText2"
    rows = tbSpecialObj.rows.length; //获得行数(包括thead)
    colums = tbSpecialObj.rows[0].cells.length; //获得列数
    const dataSpecialObj = []
    if (ret) {
        for (var i = 1; i < rows; i++) {
            var tempObj = {
                id: 0,
                packetType: 0,
                weight: 0
            }
            tempObj.id = tbSpecialObj.rows[i].cells[0].innerHTML;
            var tempSelectedObj = document.getElementById('selected' + i)
            tempObj.pakageType = tempSelectedObj.options[tempSelectedObj.selectedIndex].value
            tempObj.weight = tbSpecialObj.rows[i].cells[2].innerHTML;
            dataSpecialObj[dataSpecialObj.length] = tempObj
            var weight = Number(tempObj.weight)
            if (isNaN(weight) || weight <= 0) {
                alert("第" + tempObj.id + "特殊包中存在非法字符，请修改")
                ret =false
                break
            } else {
                if (tempObj.pakageType == "THREE" && weight > 45) {
                    alert("第" + tempObj.id + "特殊包中存在非法字符，请修改")
                    ret =false
                    break
                } else if (tempObj.pakageType == "FOUR"&& weight > 45) {
                    alert("第" + tempObj.id + "特殊包中存在非法字符，请修改")
                    ret =false
                    break
                }  else if (tempObj.pakageType == "SIX" && weight > 32) {
                    alert("第" + tempObj.id + "特殊包中存在非法字符，请修改")
                    ret =false
                    break
                } else if (tempObj.pakageType == "SEVEN"&& weight > 32) {
                    alert("第" + tempObj.id + "特殊包中存在非法字符，请修改")
                    ret =false
                    break
                } else if (tempObj.pakageType == "EIGHT"&& weight > 5) {
                    alert("第" + tempObj.id + "特殊包中存在非法字符，请修改")
                    ret =false
                    break
                } else if (tempObj.pakageType == "NINE"&& weight > 32) {
                    alert("第" + tempObj.id + "特殊包中存在非法字符，请修改")
                    ret =false
                    break
                }
            }
        }
    }
    console.log(dataSpecialObj)
    document.getElementById(jsonSpecialdata).setAttribute('value', JSON.stringify(dataSpecialObj));
    //提交结果
    if (ret)
        document.getElementById(formID).submit();
}