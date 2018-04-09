/**
 * 底部管理初始化
 */
var Bottom = {
	    id: "BottomTable",	//表格id
	    seItem: null,		//选中的条目
	    table: null,
	    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Bottom.initColumn = function () {
	var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '链接名称', field: 'url_name', align: 'center', valign: 'middle', sortable: true},
        {title: '链接地址', field: 'url', align: 'center', valign: 'middle', sortable: true},
        ]
    return columns;
};

/**
 * 检查是否选中
 */
Bottom.check = function () {
	var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	Bottom.seItem = selected[0];
        return true;
    }
};

/**
 * 关闭此对话框
 */
Bottom.close = function() {
    parent.layer.close(window.parent.Bottom.layerIndex);
}

/**
 * 修改底部相关链接
 */
Bottom.updateUrl = function () {
    var baseAjax = Feng.baseAjax("/bottom/updateUrl","提交修改");
    baseAjax.set("id");
    baseAjax.set("url");
    baseAjax.set("urlName");
    baseAjax.start();
    Bottom.close();
};
/**
 * 修改底部企业信息
 */
Bottom.updateCompany = function () {
	var baseAjax = Feng.baseAjax("/bottom/updateCompany","提交修改");
	baseAjax.set("id");
	baseAjax.set("place");
	baseAjax.set("email");
	baseAjax.set("phone");
	baseAjax.set("fax");
	baseAjax.set("copyright_information");
	baseAjax.start();
};

Bottom.openAdd = function () {
    var index = layer.open({
        type: 2,
        title: '添加链接',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bottom/bottomUrl_add'
    });
    layer.full(index);
    this.layerIndex = index;
};

/**
 * 修改详情
 */
Bottom.openEdit = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改投诉详情',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bottom/bottomUrl_edit/' + this.seItem.id
        });
        layer.full(index);
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Bottom.delete = function () {
    if (this.check()) {
    	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/bottom/bottomUrl_remove", function (data) {
	            Feng.success("删除成功!");
	            Bottom.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("bottomUrlId",Bottom.seItem.id);
	        ajax.start();
    	};
    	Feng.confirm("是否刪除该相关链接?", operation);
    }
};

$(function () {
    var defaultColunms = Bottom.initColumn();
    var table = new BSTable("BottomTable", "/bottom/urlList", defaultColunms);
    table.setPaginationType("client");
    Bottom.table = table.init();
});