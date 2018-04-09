/**
 * 栏目管理初始化
 */
var News = {
    id: "NewsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
News.initColumn = function () {
	var columns = [
        {field: 'rows', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title', align: 'center', valign: 'middle', sortable: true},
        {title: '所属栏目', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '操作员', field: 'user_name', align: 'center', valign: 'middle', sortable: true},
        {title: '是否置顶', field: 'istop', align: 'center', valign: 'middle', sortable: true},
        {title: '修改时间', field: 'modify_date', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'creat_date', align: 'center', valign: 'middle', sortable: true}
        ]
    return columns;
};

/**
 * 检查是否选中
 */
News.check = function () {
	var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	News.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加栏目
 */
News.openAddNews = function () {
    var index = layer.open({
        type: 2,
        title: '添加新闻',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/news/news_add'
    });
    layer.full(index);
    this.layerIndex = index;
};

/**
 * 打开查看栏目详情
 */
News.openNewsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '新闻详情',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/news/news_edit/' + this.seItem.id
        });
        layer.full(index);
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
News.delNews = function () {
    if (this.check()) {
    	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/news/remove", function (data) {
	            Feng.success("删除成功!");
	            News.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("newsId",News.seItem.id);
	        ajax.start();
    	};
    	Feng.confirm("是否刪除该栏目?", operation);
    }
};

/**
 * 查询栏目列表
 */
News.search = function () {
    News.table.refresh({query: News.formParams()});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
News.formParams = function() {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    return queryData;
}

$(function () {
    var defaultColunms = News.initColumn();
    var table = new BSTable("NewsTable", "/news/adminList", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(News.formParams());
    News.table = table.init();
});
