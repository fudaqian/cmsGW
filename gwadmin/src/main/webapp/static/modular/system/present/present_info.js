/**
 * 菜单详情对话框
 */
var PresentInfoDlg = {
	presentInfoData: {},
    validateFields: {
    }
};

/**
 * 清除数据
 */
PresentInfoDlg.clearData = function () {
    this.presentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PresentInfoDlg.set = function (key, val) {
    this.presentInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PresentInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PresentInfoDlg.close = function () {
    parent.layer.close(window.parent.Present.layerIndex);
}

/**
 * 收集数据
 */
PresentInfoDlg.collectData = function () {
    this.set('id').set('sn').set('name').set('company').set('presentMoney').set('acceptDate')
    .set('worth').set('presentFactory').set('presentSpec').set('processResult').set('cloumnId').set('version');
}

/**
 * 验证数据是否为空
 */
PresentInfoDlg.validate = function () {

}

/**
 * 提交添加用户
 */
PresentInfoDlg.addSubmit = function () {

	var markupStr = $('#summernote').code();
	$('#processResult').val(markupStr);

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/present/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Present.table.refresh();
        PresentInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.presentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PresentInfoDlg.editSubmit = function () {

	var markupStr = $('#summernote').code();
	$('#processResult').val(markupStr);
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/present/edit", function (data) {
        Feng.success("修改成功!");
        window.parent.Present.table.refresh();
        PresentInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.presentInfoData);
    ajax.start();
}



$(function () {
	
});
