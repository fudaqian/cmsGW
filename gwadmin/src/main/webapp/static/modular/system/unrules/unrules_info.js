/**
 * 菜单详情对话框
 */
var UnrulesInfoDlg = {
	UnrulesInfoData: {},
    validateFields: {
    }
};

/**
 * 清除数据
 */
UnrulesInfoDlg.clearData = function () {
    this.UnrulesInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UnrulesInfoDlg.set = function (key, val) {
    this.UnrulesInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UnrulesInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UnrulesInfoDlg.close = function () {
    parent.layer.close(window.parent.Unrules.layerIndex);
}

/**
 * 收集数据
 */
UnrulesInfoDlg.collectData = function () {
    this.set('id').set('sn').set('unruleCompany').set('legalPerson').set('partnerName')
    .set('concertProject').set('unruleThing').set('attributeCompany').set('processResult').set('version').set('checkTime');
}

/**
 * 验证数据是否为空
 */
UnrulesInfoDlg.validate = function () {

}

/**
 * 提交添加用户
 */
UnrulesInfoDlg.addSubmit = function () {

	var markupStr = $('#summernote').code();
	$('#processResult').val(markupStr);

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/unrules/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Unrules.table.refresh();
        UnrulesInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.UnrulesInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UnrulesInfoDlg.editSubmit = function () {

	var markupStr = $('#summernote').code();
	$('#processResult').val(markupStr);
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/unrules/edit", function (data) {
        Feng.success("修改成功!");
        window.parent.Unrules.table.refresh();
        UnrulesInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.UnrulesInfoData);
    ajax.start();
}



$(function () {
	
});
