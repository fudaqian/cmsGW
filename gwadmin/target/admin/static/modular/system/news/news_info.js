/**
 * 菜单详情对话框
 */
var NewsInfoDlg = {
    newsInfoData: {},
    validateFields: {
    }
};

/**
 * 清除数据
 */
NewsInfoDlg.clearData = function () {
    this.newsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NewsInfoDlg.set = function (key, val) {
    this.newsInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NewsInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NewsInfoDlg.close = function () {
    parent.layer.close(window.parent.News.layerIndex);
}

/**
 * 收集数据
 */
NewsInfoDlg.collectData = function () {
    this.set('id').set('image').set('cloumnId').set('istop').set('htmlContent').set('title').set('subtitle').set('creatDate');
}

/**
 * 验证数据是否为空
 */
NewsInfoDlg.validate = function () {

}

/**
 * 添加top
 */
NewsInfoDlg.istopBox = function () {
	$("input[name='radio1']").each(
		function(){
			if($(this).prop('checked')){
				$('#istop').val($(this).val());
			}
		}
	);
}

/**
 * 提交添加
 */
NewsInfoDlg.addSubmit = function () {
		var subtitle = CKEDITOR.instances.subTitleT.getData();
		$('#subtitle').val(subtitle);
		var markupStr = CKEDITOR.instances.htmlContentT.getData();
		$('#htmlContent').val(markupStr);
		NewsInfoDlg.istopBox();
	    this.clearData();
	    this.collectData();

	    //提交信息
	    var ajax = new $ax(Feng.ctxPath + "/news/add", function (data) {
	        Feng.success("添加成功!");
	        window.parent.News.table.refresh();
	        NewsInfoDlg.close();
	    }, function (data) {
	        Feng.error("添加失败!" + data.responseJSON.message + "!");
	    });
	    ajax.set(this.newsInfoData);
	    ajax.start();

}

/**
 * 提交修改
 */
NewsInfoDlg.editSubmit = function () {
	var subtitle = CKEDITOR.instances.subTitleT.getData();
	$('#subtitle').val(subtitle);
	var markupStr = CKEDITOR.instances.htmlContentT.getData();
	$('#htmlContent').val(markupStr);
		NewsInfoDlg.istopBox();
	    this.clearData();
	    this.collectData();
	    
	    //提交信息
	    var ajax = new $ax(Feng.ctxPath + "/news/edit", function (data) {
	        Feng.success("修改成功!");
	        window.parent.News.table.refresh();
	        NewsInfoDlg.close();
	    }, function (data) {
	        Feng.error("修改失败!" + data.responseJSON.message + "!");
	    });
	    ajax.set(this.newsInfoData);
	    ajax.start();

}

$(function () {
    var avatarUp = new $WebUpload("image","/complain/upload");
    avatarUp.init();
	
	if ($("#image").val() != "") {
		var $li = $('<div><img width="200px" height="130px"></div>');
		var $img = $li.find('img');
		$("#imagePreId").html($li);
		$img.attr('src', Feng.ctxPath + "/kaptcha/" + $("#image").val());
	}
	if ($("#dateTemp").val() != "") {
		$("#creatDate").val($("#dateTemp").val());
	}
	if ($("#cloumnIdValue").val() != "") {
		$("#cloumnId").val($("#cloumnIdValue").val());
	}
	if ($("#istopValue").val() != undefined) {
		if($("#istopValue").val() == '1'){
			$(":radio[name='radio1'][value='1']").attr("checked","true");
		}else{
			$(":radio[name='radio1'][value='0']").attr("checked","true");
		}
		
	}
});
