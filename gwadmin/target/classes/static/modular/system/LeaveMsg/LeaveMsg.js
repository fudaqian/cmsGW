/**
 * 管理初始化
 */
var LeaveMsg = {
    id: "LeaveMsgTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LeaveMsg.initColumn = function () {
	var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '留言人', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '电话', field: 'phone', align: 'center', valign: 'middle', sortable: true},
        {title: '邮箱', field: 'email', align: 'center', valign: 'middle', sortable: true},
        {title: '内容', field: 'content', align: 'center', valign: 'middle', sortable: true},
        {title: '时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true},
        ]
    return columns;
};

/**
 * 检查是否选中
 */
LeaveMsg.check = function () {
	var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	LeaveMsg.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
LeaveMsg.openAdd = function () {
    var index = layer.open({
        type: 2,
        title: '添加违规合作单',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/unrules/unrules_add'
    });
    layer.full(index);
    this.layerIndex = index;
};

/**
 * 打开修改详情
 */
LeaveMsg.openEdit = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改违规单详情',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/unrules/unrules_edit/' + this.seItem.id
        });
        layer.full(index);
        this.layerIndex = index;
    }
};
/**
 * 打开查看详情
 */
LeaveMsg.openDetail = function () {
	if (this.check()) {
		var index = layer.open({
			type: 2,
			title: '留言详情',
			area: ['800px', '450px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/leaveMsg/detail/' + this.seItem.id
		});
		layer.full(index);
		this.layerIndex = index;
	}
};

/**
 * 删除
 */
LeaveMsg.del = function () {
    if (this.check()) {
    	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/unrules/delete", function (data) {
	            Feng.success("删除成功!");
	            Unrules.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("unrulesId",Unrules.seItem.id);
	        ajax.start();
    	};
    	Feng.confirm("是否刪除该单据?", operation);
    }
};

/**
 * 查询列表
 */
LeaveMsg.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Unrules.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = LeaveMsg.initColumn();
    var table = new BSTable("LeaveMsgTable", "/leaveMsg/list", defaultColunms);
    table.setPaginationType("client");
    LeaveMsg.table = table.init();
});
