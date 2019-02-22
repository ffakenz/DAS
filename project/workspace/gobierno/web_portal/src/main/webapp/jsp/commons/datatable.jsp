<%@
        page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${lang}" scope="session" />

<script src="/web_portal/js/external_libraries/jquery.dataTables.min.js"></script>
<script src="/web_portal/js/external_libraries/dataTables.bootstrap4.min.js"></script>
<script src="/web_portal/js/external_libraries/dataTables.buttons.min.js"></script>
<script src="/web_portal/js/external_libraries/pdfmake.min.js"></script>
<script src="/web_portal/js/external_libraries/vfs_fonts.js"></script>
<script src="/web_portal/js/external_libraries/buttons.html5.min.js"></script>


<fmt:message key="datatables_empty_table" var="empty_table" bundle="${etq}" />
<fmt:message key="datatables_zero_records" var="zero_records" bundle="${etq}" />
<fmt:message key="datatables_info" var="info" bundle="${etq}" />
<fmt:message key="datatables_info_empty" var="info_empty" bundle="${etq}" />
<fmt:message key="datatables_info_filtered" var="info_filtered" bundle="${etq}" />
<fmt:message key="datatables_length_menu" var="length_menu" bundle="${etq}" />
<fmt:message key="datatables_loading_records" var="loading_records" bundle="${etq}" />
<fmt:message key="datatables_processing" var="processing" bundle="${etq}" />
<fmt:message key="datatables_search" var="search" bundle="${etq}" />
<fmt:message key="datatables_paginate_first" var="paginate_first" bundle="${etq}" />
<fmt:message key="datatables_paginate_last" var="paginate_last" bundle="${etq}" />
<fmt:message key="datatables_paginate_next" var="paginate_next" bundle="${etq}" />
<fmt:message key="datatables_paginate_previous" var="paginate_previous" bundle="${etq}" />
<fmt:message key="datatables_aria_sort_ascending" var="aria_sort_ascending" bundle="${etq}" />
<fmt:message key="datatables_aria_sort_descending" var="aria_sort_descending" bundle="${etq}" />
<script>
        $(()=> {
            var title = '<c:out value="${param.title}"/>';
            $('#table_admin_result').append('<caption style="caption-side: top"> <h1 style="color:#778182;">'+title+'</h1> </caption>');

            $('#table_admin_result').DataTable( {
                "scrollY": "20rem",
                "scrollCollapse": true,
                "paging": false,
                "scrollX": true,
                dom: 'Bfrtip',
                buttons: [
                    {
                        extend: 'pdfHtml5',
                        orientation: 'landscape',
                        pageSize: 'LEGAL'
                    }
                ],
                language: {
                    decimal:        "",
                    emptyTable:     '<c:out value="${empty_table}" />',
                    info:           '<c:out value="${info}" />',
                    infoEmpty:      '<c:out value="${info_empty}" />',
                    infoFiltered:   '<c:out value="${info_filtered}" />',
                    infoPostFix:    "",
                    thousands:      ",",
                    lengthMenu:     '<c:out value="${length_menu}" />',
                    loadingRecords: '<c:out value="${loading_records}" />',
                    processing:     '<c:out value="${processing}" />',
                    search:         '<c:out value="${search}" />',
                    zeroRecords:    '<c:out value="${zero_records}" />',
                    paginate: {
                        first:      '<c:out value="${paginate_first}" />',
                        last:       '<c:out value="${paginate_last}" />',
                        next:       '<c:out value="${paginate_next}" />',
                        previous:   '<c:out value="${paginate_previous}" />'
                    },
                    aria: {
                        sortAscending:  '<c:out value="${aria_sort_ascending}" />',
                        sortDescending: '<c:out value="${aria_sort_descending}" />'
                    }
                }
            } );

            $('#table_admin_result tbody')
                .on( 'mouseenter', 'td', function () {
                    var colIdx = table.cell(this).index().column;

                    $( table.cells().nodes() ).removeClass( 'highlight' );
                    $( table.column( colIdx ).nodes() ).addClass( 'highlight' );
                } );

        });
</script>