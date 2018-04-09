/**
 * 管理初始化
 */
var Unrules = {
    id: "UnrulesTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Unrules.initColumn = function () {
	var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '编号', field: 'sn', align: 'center', valign: 'middle', sortable: true},
        {title: '违规公司名称', field: 'unrule_company', align: 'center', valign: 'middle', sortable: true},
        {title: '查处时间', field: 'check_time', align: 'center', valign: 'middle', sortable: true},
        {title: '合作项目', field: 'concert_project', align: 'center', valign: 'middle', sortable: true},
        {title: '违规事项', field: 'unrule_thing', align: 'center', valign: 'middle', sortable: true},
        {title: '所属公司', field: 'attribute_company', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'creat_date', align: 'center', valign: 'middle', sortable: true},
        {title: '创建人', field: 'user_name', align: 'center', valign: 'middle', sortable: true},
        ]
    return columns;
};

/**
 * 检查是否选中
 */
Unrules.check = function () {
	var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	Unrules.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Unrules.openAdd = function () {
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
Unrules.openEdit = function () {
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
Unrules.openDetail = function () {
	if (this.check()) {
		var index = layer.open({
			type: 2,
			title: '违规单详情',
			area: ['800px', '450px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/unrules/unrules_detail/' + this.seItem.id
		});
		layer.full(index);
		this.layerIndex = index;
	}
};

/**
 * 删除
 */
Unrules.del = function () {
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
Unrules.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['sn'] = $("#sn").val();
    queryData['checkTime'] = $("#checkTime").val();
    queryData['unruleCompany'] = $("#unruleCompany").val();
    queryData['attributeCompany'] = $("#attributeCompany").val();
    Unrules.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Unrules.initColumn();
    var table = new BSTable("UnrulesTable", "/unrules/list", defaultColunms);
    table.setPaginationType("client");
    Unrules.table = table.init();
});
