/**
 * 管理初始化
 */
var Present = {
    id: "PresentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Present.initColumn = function () {
	var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '编号', field: 'sn', align: 'center', valign: 'middle', sortable: true},
        {title: '姓名', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '所属公司', field: 'company', align: 'center', valign: 'middle', sortable: true},
        {title: '礼品礼金', field: 'present_money', align: 'center', valign: 'middle', sortable: true},
        {title: '价值（元）', field: 'worth', align: 'center', valign: 'middle', sortable: true},
        {title: '礼品厂家', field: 'present_factory', align: 'center', valign: 'middle', sortable: true},
        {title: '礼品规格型号', field: 'present_spec', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'creat_date', align: 'center', valign: 'middle', sortable: true},
        {title: '创建人', field: 'user_name', align: 'center', valign: 'middle', sortable: true},
        ]
    return columns;
};

/**
 * 检查是否选中
 */
Present.check = function () {
	var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	Present.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Present.openAddPresent = function () {
    var index = layer.open({
        type: 2,
        title: '添加礼品礼金公示单',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/present/present_add'
    });
    layer.full(index);
    this.layerIndex = index;
};

/**
 * 打开修改详情
 */
Present.openPresentEdit = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改礼品单详情',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/present/present_edit/' + this.seItem.id
        });
        layer.full(index);
        this.layerIndex = index;
    }
};
/**
 * 打开查看详情
 */
Present.openPresentDetail = function () {
	if (this.check()) {
		var index = layer.open({
			type: 2,
			title: '礼品单详情',
			area: ['800px', '450px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/present/present_detail/' + this.seItem.id
		});
		layer.full(index);
		this.layerIndex = index;
	}
};

/**
 * 删除
 */
Present.delPresent = function () {
    if (this.check()) {
    	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/present/delete", function (data) {
	            Feng.success("删除成功!");
	            Present.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("presentId",Present.seItem.id);
	        ajax.start();
    	};
    	Feng.confirm("是否刪除该单据?", operation);
    }
};

/**
 * 查询列表
 */
Present.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['sn'] = $("#sn").val();
    queryData['name'] = $("#name").val();
    queryData['company'] = $("#company").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    Present.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Present.initColumn();
    var table = new BSTable("PresentTable", "/present/list", defaultColunms);
    table.setPaginationType("client");
    Present.table = table.init();
});
