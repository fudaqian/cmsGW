/**
 * 菜单详情对话框
 */
var LeaveMsgInfoDlg = {
	LeaveMsgInfoData: {},
    validateFields: {
    }
};

/**
 * 清除数据
 */
LeaveMsgInfoDlg.clearData = function () {
    this.LeaveMsgInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LeaveMsgInfoDlg.set = function (key, val) {
    this.LeaveMsgInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LeaveMsgInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LeaveMsgInfoDlg.close = function () {
    parent.layer.close(window.parent.LeaveMsg.layerIndex);
}

/**
 * 收集数据
 */
LeaveMsgInfoDlg.collectData = function () {
    this.set('id').set('sn').set('unruleCompany').set('legalPerson').set('partnerName')
    .set('concertProject').set('unruleThing').set('attributeCompany').set('processResult').set('version').set('checkTime');
}

/**
 * 验证数据是否为空
 */
LeaveMsgInfoDlg.validate = function () {

}

/**
 * 提交添加用户
 */
LeaveMsgInfoDlg.addSubmit = function () {

	var markupStr = $('#summernote').code();
	$('#processResult').val(markupStr);

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/unrules/add", function (data) {
        Feng.success("添加成功!");
        window.parent.LeaveMsg.table.refresh();
        LeaveMsgInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.LeaveMsgInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LeaveMsgInfoDlg.editSubmit = function () {

	var markupStr = $('#summernote').code();
	$('#processResult').val(markupStr);
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/unrules/edit", function (data) {
        Feng.success("修改成功!");
        window.parent.LeaveMsg.table.refresh();
        LeaveMsgInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.LeaveMsgInfoData);
    ajax.start();
}



$(function () {
	
});
