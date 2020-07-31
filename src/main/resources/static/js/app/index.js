var main = {

    init : function() {
        var _this = this;
        $('#btn-save').on('click', function(){
            _this.save();
        });
        $('#btn-update').on('click', function() {
            _this.update();
        });
    },

    //등록
    save : function() {
        var data = {
            title  : $('#title').val(),
            author : $('#author').val(),
            content: $('#content').val()
        };

       $.ajax({
            type : 'post',
            url  : '/api/v1/posts',
            dataType    : 'json',
            contentType : 'application/json; charset=UTF-8',
            data : JSON.stringify(data)
       }).done(function() {
            alert("게시글 등록 성공!!");
            window.location.href = "/";

       }).fail(function(error) {
            alert(JSON.stringify(error));
       });
    },

    //수정
    update : function () {
           var data = {
               title: $('#title').val(),
               content: $('#content').val()
           };

           var id = $('#id').val();

           $.ajax({
               type: 'PUT',
               url: '/api/v1/posts/'+id,
               dataType: 'json',
               contentType:'application/json; charset=utf-8',
               data: JSON.stringify(data)
           }).done(function() {
               alert('글이 수정되었습니다.');
               window.location.href = '/';
           }).fail(function (error) {
               alert(JSON.stringify(error));
           });
    }
};

main.init();

















