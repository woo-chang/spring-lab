<%--
  Created by IntelliJ IDEA.
  User: woochang
  Date: 2021/10/28
  Time: 9:15 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Modify</h1>
    </div>
    <%-- /.col-lg-12 --%>
</div>
<%-- /.row --%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">

            <div class="panel-heading">Board Modify Page</div>
            <%-- /.panel-heading --%>
            <div class="panel-body">
                <form role="form" action="/board/modify" method="post">

                <div class="form-group">
                    <label>Bno</label> <input class="form-control" name="bno"
                                              value='<c:out value="${board.bno}" />' readonly="readonly">
                </div>

                <div class="form-group">
                    <label>Title</label> <input class="form-control" name="title"
                                                value='<c:out value="${board.title}" />' >
                </div>

                <div class="form-group">
                    <label>Text area</label>
                    <textarea class="form-control" rows="3" name="content"
                              readonly="readonly"><c:out value="${board.content}" /></textarea>
                </div>

                <div class="form-group">
                    <label>Writer</label> <input class="form-control" name="writer"
                                                 value='<c:out value="${board.writer}" />' readonly="readonly">
                </div>

               <div class="form-group">
                   <label>RegDate</label>
                   <input class="form-control" name="regDate" value="<fmt:formatDate pattern="yyyy/MM/dd" value="${board.
                   regDate}" />" readonly="readonly"/>
               </div>

               <div class="form-group">
                    <label>Update Date</label>
                    <input class="form-control" name="updateDate" value="<fmt:formatDate pattern="yyyy/MM/dd" value="${board.
                   updateDate}" />" readonly="readonly"/>
                </div>

                <button type="submit" data-oper="modify" class="btn btn-default">Modify</button>
                <button type="submit" data-oper="remove" class="btn btn-default">Remove</button>
                <button type="submit" data-oper="list" class="btn btn-info">List</button>

                </form>

            </div>
            <%-- end panel-body --%>

        </div>
        <%-- end paenl-body --%>
    </div>
    <%-- end paenl --%>
</div>
<%-- /.row --%>

<script type="text/javascript">
    $(document).read(function () {

        var formObj = $("form");

        $('button').on("click", function (e){

            e.preventDefault(); // 버튼의 기본 submit 동적을 막는 작업

            var operation = $(this).data("oper");

            console.log(operation);

            if(operation === 'remove') {
                formObj.attr("action", "/board/remove");
            } else if(operation === 'list') {
                // move to list
                self.location = "/board/list";
                return;
            }
            formObj.submit(); // 마지막에 직접 submit 작업을 수행

        });
    });
</script>

<%@include file="../includes/footer.jsp" %>