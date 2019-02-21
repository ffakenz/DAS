<%@
        page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="/web_portal/js/external_libraries/jquery.dataTables.min.js"></script>
<script src="/web_portal/js/external_libraries/dataTables.bootstrap4.min.js"></script>
<script src="/web_portal/js/external_libraries/dataTables.buttons.min.js"></script>
<script src="/web_portal/js/external_libraries/pdfmake.min.js"></script>
<script src="/web_portal/js/external_libraries/vfs_fonts.js"></script>
<script src="/web_portal/js/external_libraries/buttons.html5.min.js"></script>
<script>
        $(()=> {
            var title = '<c:out value="${param.title}"/>';
            $('#table_admin_result').append('<caption style="caption-side: top"> <h1>'+title+'</h1> </caption>');

            $('#table_admin_result').DataTable( {
                scrollX: true,
                dom: 'Bfrtip',
                buttons: [
                    {
                        extend: 'pdfHtml5',
                        orientation: 'landscape',
                        pageSize: 'LEGAL'
                    }
                ]
            } );

        });
</script>