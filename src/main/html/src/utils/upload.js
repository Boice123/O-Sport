export const uploadPic = (obj) => {
    let shopimg
    var options = {
      contentType:"multipart/form-data",
      url: API_uploadFileURL,
      type: "post",
      dataType: "json",
      success: (result) => {
        shopimg = result.data;
      },
      error: (XMLHttpRequest, textStatus, errorThrown) => {
        alert("服务器出错，上传图片失败！")
      }
    }
    obj.parentNode.parentNode.ajaxSubmit(options)
    console.log("点击上传后的图片"+shopimg)
    return shopimg
}
