/**
 * 栏目管理初始化
 */
var Column = {
    id: "ColumnTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Column.initColumn = function () {
	var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '栏目名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'num', align: 'center', valign: 'middle', sortable: true},
        {title: '是否置顶', field: 'istop', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'statusName', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};

/**
 * 检查是否选中
 */
Column.check = function () {
	var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	console.info(Column.seItem);
    	Column.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加栏目
 */
Column.openAddColumn = function () {
    var index = layer.open({
        type: 2,
        title: '添加栏目',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/column/column_add'
    });
    layer.full(index);
    this.layerIndex = index;
};

/**
 * 打开查看栏目详情
 */
Column.openColumnDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '栏目详情',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/column/column_update/' + Column.seItem.id
        });
        layer.full(index);
        this.layerIndex = index;
    }
};

/**
 * 删除栏目
 */
Column.delete = function () {
    if (this.check()) {
    	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/column/delete", function (data) {
	            Feng.success("删除成功!");
	            Column.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("columnId",Column.seItem.id);
	        ajax.start();
    	};
    	Feng.confirm("是否刪除该栏目?", operation);
    }
};

/**
 * 查询栏目列表
 */
Column.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Column.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Column.initColumn();
    var table = new BSTreeTable(Column.id, "/column/list", defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("pid");
    table.setExpandAll(true);
    table.init();
    Column.table = table;
});
