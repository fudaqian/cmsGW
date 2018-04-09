/**
 * 菜单详情对话框
 */
var ComplainInfoDlg = {
	complainInfoData : {},
	validateFields : {}
};

/**
 * 清除数据
 */
ComplainInfoDlg.clearData = function() {
	this.complainInfoData = {};
}

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
ComplainInfoDlg.set = function(key, val) {
	this.complainInfoData[key] = (typeof value == "undefined") ? $("#" + key)
			.val() : value;
	return this;
}

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
ComplainInfoDlg.get = function(key) {
	return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ComplainInfoDlg.close = function() {
	parent.layer.close(window.parent.Complain.layerIndex);
}

/**
 * 收集数据
 */
ComplainInfoDlg.collectData = function() {
	this.set('id').set('acceptName').set('acceptDepartment').set('acceptCompany')
			.set('acceptType').set('putName').set('putStatus').set('putPhone')
			.set('putEmail').set('title').set('place').set('content')
			.set('file').set('ischeck').set('feedbackIdea').set('feedbackIdeaHtml').set('creatDate')
			.set('fileName').set('fileType').set('sn');
}

/**
 * 验证数据是否为空
 */
ComplainInfoDlg.validate = function() {

}

/**
 * 提交添加
 */
ComplainInfoDlg.addSubmit = function() {
	var acceptType ="";
	$("input:checkbox[name=typeBox]").each(		
		function(){
			if(this.checked){
				acceptType += this.value+",";
			}
		}
	);
	$("#acceptType").val(acceptType);
	this.clearData();
	this.collectData();

	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/complain/add", function(data) {
		Feng.success("添加成功!");
		window.parent.Complain.table.refresh();
		ComplainInfoDlg.close();
	}, function(data) {
		Feng.error("添加失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.complainInfoData);
	ajax.start();
}

/**
 * 提交修改
 */
ComplainInfoDlg.editSubmit = function() {
	
	var markupStr = $('#summernote').code();	
	this.clearData();
	this.collectData();
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/complain/edit", function(data) {
		Feng.success("修改成功!");
		window.parent.Complain.table.refresh();
		ComplainInfoDlg.close();
	}, function(data) {
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.complainInfoData);
	ajax.set("feedbackIdeaHtml",markupStr);
	ajax.start();
}

/**
 * 下载文件
 */
ComplainInfoDlg.down = function() {
	
	var id = $('#id').val();
	var operation = function(){	       
	  		window.open(Feng.ctxPath + '/complain/down/'+ id);
	};
    Feng.confirm("是否下载该文件?", operation);
    
}

/**
 * 清空文件
 */
ComplainInfoDlg.removeFile = function() {
	$("#filePreId").html("");
	$("#fileType").val("");
	$("#fileName").val("");
	$("#file").val("");
}

$(function() {
	var type = $("#fileType").val();
	if(type == 'image/gif' || type == 'image/jpg' || type == 'image/jpeg' || type == 'image/bmp' || type =='image/png'){
		if ($("#file").val() != undefined && $("#file").val() != "") {
			var $li = $('<div><img width="200px" height="130px"></div>');
			var $img = $li.find('img');
			$("#filePreId").html($li);
			$img.attr('src', Feng.ctxPath + "/kaptcha/" + $("#file").val());
		}
	}else{
		$("#filePreId").html("<span>"+$("#fileName").val()+"</span>");
	}
    var complainUp = new $WebUpload("file","/complain/upload");
    complainUp.init();
    
    
});
