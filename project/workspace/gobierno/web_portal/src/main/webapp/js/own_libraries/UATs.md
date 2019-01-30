1) Go to Home Page
Logs de "Handle Click"
Main View Loaded Modules
Main View executed on Load

ActionMapping [parameters={}, forwards={
    failure=ForwardConfig [name=failure, path=/util/failure.jsp, redirect=false],
    success=ForwardConfig [name=success, path=/home/home.jsp, redirect=false], 
    warning=ForwardConfig [name=warning, path=/util/warning.jsp, redirect=false], information=ForwardConfig [name=information, path=/util/information.jsp, redirect=false]}]

[HttpServletRequest - URL] = http://localhost:9000/web_portal/
[HttpServletRequest - METHOD] = GET
[HttpServletResponse - STATUS] = 200

2) Click on Login Button
Logs de "Handle Click"
Main View Loaded Modules
Main View executed on Load

ActionMapping [parameters={}, forwards={
    failure=ForwardConfig [name=failure, path=/util/failure.jsp, redirect=false], 
    success=ForwardConfig [name=success, path=/login/login.jsp, redirect=false], 
    warning=ForwardConfig [name=warning, path=/util/warning.jsp, redirect=false], 
    information=ForwardConfig [name=information, path=/util/information.jsp, redirect=false]}]

[DynaActionForm] = 
    Key: javax.servlet.forward.context_path - Value: /web_portal
    Key: javax.servlet.forward.servlet_path - Value: /home/ShowLogin.do
    Key: javax.servlet.forward.request_uri - Value: /web_portal/home/ShowLogin.do
    Key: org.tuckey.web.filters.urlrewrite.RuleMatched - Value: true
    Key: uri - Value: /home/ShowLogin.do

[HttpServletRequest - URL] = http://localhost:9000/web_portal/index.jsp
[HttpServletRequest - QUERY] = uri=/home/ShowLogin.do
[HttpServletRequest - METHOD] = GET
[HttpServletRequest - ATTRIBUTE] = /web_portal/home/ShowLogin.do
[HttpServletRequest - ATTRIBUTE_NAME] = javax.servlet.forward.context_path
[HttpServletRequest - ATTRIBUTE] = /web_portal
[HttpServletRequest - ATTRIBUTE_NAME] = javax.servlet.forward.servlet_path
[HttpServletRequest - ATTRIBUTE] = /home/ShowLogin.do
[HttpServletRequest - ATTRIBUTE_NAME] = org.tuckey.web.filters.urlrewrite.RuleMatched
[HttpServletRequest - ATTRIBUTE] = true
[HttpServletResponse - STATUS] = 200

3) Execute Login
concesionarias:1 Uncaught SyntaxError: Identifier 'Concesionarias' has already been declared
    at VM768 concesionarias:1
Logs de "Handle Click"
Main View Loaded Modules
Concesionarias View Loaded Modules
Main View executed on Load
Concesionarias View executed on Load

ActionMapping [parameters={}, forwards={
    failure=ForwardConfig [name=failure, path=/login/failLogin.jsp, redirect=false], 
    success=ForwardConfig [name=success, path=/concesionarias/tableConcesionarias.jsp, redirect=false], warning=ForwardConfig [name=warning, path=/login/failParamsLogin.jsp, redirect=false], information=ForwardConfig [name=information, path=/util/information.jsp, redirect=false], success_gobierno=ForwardConfig [name=success_gobierno, path=/usuarios/successLoginGobierno.jsp, redirect=false], 
    success_consumer=ForwardConfig [name=success_consumer, path=/usuarios/successLoginConsumer.jsp, redirect=false]}]

[DynaActionForm] = 
    Key: javax.servlet.forward.context_path - Value: /web_portal
    Key: javax.servlet.forward.servlet_path - Value: /concesionarias/ConsultarConcesionarias.do
    Key: javax.servlet.forward.request_uri - Value: /web_portal/concesionarias/ConsultarConcesionarias.do
    Key: org.tuckey.web.filters.urlrewrite.RuleMatched - Value: true
    Key: uri - Value: /concesionarias/ConsultarConcesionarias.do

[HttpServletRequest - URL] = http://localhost:9000/web_portal/index.jsp
[HttpServletRequest - QUERY] = uri=/concesionarias/ConsultarConcesionarias.do
[HttpServletRequest - METHOD] = GET
[HttpServletRequest - SESSION] = org.apache.catalina.session.StandardSessionFacade@f32a72e
[HttpServletRequest - ATTRIBUTE_NAME] = javax.servlet.forward.request_uri
[HttpServletRequest - ATTRIBUTE] = /web_portal/concesionarias/ConsultarConcesionarias.do
[HttpServletRequest - ATTRIBUTE_NAME] = javax.servlet.forward.context_path
[HttpServletRequest - ATTRIBUTE] = /web_portal
[HttpServletRequest - ATTRIBUTE_NAME] = javax.servlet.forward.servlet_path
[HttpServletRequest - ATTRIBUTE] = /concesionarias/ConsultarConcesionarias.do
[HttpServletRequest - ATTRIBUTE_NAME] = result
[HttpServletRequest - ATTRIBUTE] = [{"id":1,"nombre":"rest_one","fechaRegistracion":"2019-01-30 12:01:05.350","fechaAlta":"2019-01-30 12:10:11.567","codigo":"SUPER_CODIGO_SECRETO_1","direccion":"Direccion rest_one","cuit":"21-93337511-1","tel":"+5493513059161","email":"c1@gmail.com","name":"ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm","items":{}}, {"id":2,"nombre":"cxf_one","fechaRegistracion":"2019-01-30 12:01:05.350","direccion":"Direccion cxf_one","cuit":"22-93337511-2","tel":"+5493513059162","email":"c2@gmail.com","name":"ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm","items":{}}, {"id":3,"nombre":"axis_one","fechaRegistracion":"2019-01-30 12:01:05.350","direccion":"Direccion axis_one","cuit":"22-93337511-3","tel":"+5493513059163","email":"c3@gmail.com","name":"ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm","items":{}}]
[HttpServletRequest - ATTRIBUTE_NAME] = org.tuckey.web.filters.urlrewrite.RuleMatched
[HttpServletRequest - ATTRIBUTE] = true
[HttpServletResponse - STATUS] = 200