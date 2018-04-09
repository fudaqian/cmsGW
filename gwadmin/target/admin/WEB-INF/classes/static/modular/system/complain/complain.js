/**
 * 栏目管理初始化
 */
var Complain = {
    id: "complainTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Complain.initColumn = function () {
	var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '编号', field: 'sn', align: 'center', valign: 'middle', sortable: true},
        {title: '被举报人', field: 'accept_name', align: 'center', valign: 'middle', sortable: true},
        {title: '所属部门', field: 'accept_department', align: 'center', valign: 'middle', sortable: true},
        {title: '所在公司', field: 'accept_company', align: 'center', valign: 'middle', sortable: true},
        {title: '举报类型', field: 'accept_type', align: 'center', valign: 'middle', sortable: true},
        {title: '举报标题', field: 'title', align: 'center', valign: 'middle', sortable: true},
        {title: '举报人', field: 'put_name', align: 'center', valign: 'middle', sortable: true},
        {title: '举报人身份', field: 'put_status', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'creat_date', align: 'center', valign: 'middle', sortable: true}  
        ]
    return columns;
};

/**
 * 检查是否选中
 */
Complain.check = function () {
	var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	Complain.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加举报
 */
Complain.openAddComplain = function () {
    var index = layer.open({
        type: 2,
        title: '添加举报',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/complain/complain_add'
    });
    layer.full(index);
    this.layerIndex = index;
};

/**
 * 修改详情
 */
Complain.openComplainEdit = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改投诉详情',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/complain/complain_edit/' + this.seItem.id
        });
        layer.full(index);
        this.layerIndex = index;
    }
};
/**
 * 打开查看详情
 */
Complain.openComplainDetail = function () {
	if (this.check()) {
		var index = layer.open({
			type: 2,
			title: '投诉详情',
			area: ['800px', '450px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/complain/complain_detail/' + this.seItem.id
		});
		layer.full(index);
		this.layerIndex = index;
	}
};

/**
 * 删除
 */
Complain.delComplain = function () {
    if (this.check()) {
    	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/complain/remove", function (data) {
	            Feng.success("删除成功!");
	            Complain.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("complainId",Complain.seItem.id);
	        ajax.start();
    	};
    	Feng.confirm("是否刪除该举报信息?", operation);
    }
};

/**
 * 查询栏目列表
 */
Complain.search = function () {
    var queryData = {};
    queryData['acceptName'] = $("#acceptName").val();
    queryData['acceptType'] = $("#acceptType").val();
    queryData['ischeck'] = $("#ischeck").val();
    Complain.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Complain.initColumn();
    var table = new BSTable("complainTable", "/complain/list", defaultColunms);
    table.setPaginationType("client");
    Complain.table = table.init();
});
