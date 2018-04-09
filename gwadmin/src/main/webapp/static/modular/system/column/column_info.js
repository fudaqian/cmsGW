/**
 * 初始化栏目详情对话框
 */
var ColumnInfoDlg = {
    columnInfoData : {}
};

/**
 * 清除数据
 */
ColumnInfoDlg.clearData = function() {
    this.columnInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ColumnInfoDlg.set = function(key, val) {
    this.columnInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ColumnInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ColumnInfoDlg.close = function() {
    parent.layer.close(window.parent.Column.layerIndex);
}

/**
 * 收集数据
 */
ColumnInfoDlg.collectData = function() {
    this.set('id').set('name').set('status').set('pid').set('num').set('istop');
}

/**
 * 提交添加
 */
ColumnInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/column/add", function(data){
        Feng.success("添加成功!");
        window.parent.Column.table.refresh();
        ColumnInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.columnInfoData);
    ajax.start();
}

ColumnInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#pcodeName").attr("value", ColumnInfoDlg.ztreeInstance.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
};

ColumnInfoDlg.showMenuSelectTree = function (){
	Feng.showInputTree("pcodeName", "pcodeTreeDiv", 15, 34);
}


/**
 * 提交修改
 */
ColumnInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/column/update", function(data){
        Feng.success("修改成功!");
        window.parent.Column.table.refresh();
        ColumnInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.columnInfoData);
    ajax.start();
}

$(function() {
	 	var ztree = new $ZTree("pcodeTree", "/column/tree");
	    ztree.bindOnClick(ColumnInfoDlg.onClickDept);
	    ztree.init();
	    ColumnInfoDlg.ztreeInstance = ztree;
	    
	    //初始化是否是菜单
	    if($("#statusValue").val() == undefined){
	        $("#status").val(0);
	    }else{
	        $("#status").val($("#statusValue").val());
	    }
	    if($("#statusValue").val() != undefined){
	    	$("#status").val($("#statusValue").val());
	    }
});
