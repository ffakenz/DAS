URL : http://localhost:9000/web_portal/login/Login.do
Haciendo APROBAR, error en aprobarHandler:
``` [Brownser Console]
Uncaught TypeError: Cannot read property 'POST_APROBAR_CONCESIONARIA' of undefined
    at HTMLButtonElement.aprobarHandler (concesionarias:1)
    at HTMLTableElement.dispatch (jquery.min.js:3)
    at HTMLTableElement.q.handle
```
Haciendo CONFIGURAR, error en configurarHandler:
``` [Brownser Console]
Uncaught TypeError: Cannot read property 'POST_CONSULTAR_CONFIG_PARAM' of undefined
    at HTMLButtonElement.configurarHandler (concesionarias:1)
    at HTMLTableElement.dispatch (jquery.min.js:3)
    at HTMLTableElement.q.handle (jquery.min.js:3)
```
Backend logs:
ActionMapping [
    parameters={}, 
    forwards={
        failure=ForwardConfig [name=failure, path=/util/failure.jsp, redirect=false],
        success=ForwardConfig [name=success, path=/concesionarias/tableConcesionarias.jsp, redirect=false],
        warning=ForwardConfig [name=warning, path=/util/warning.jsp, redirect=false],
        information=ForwardConfig [name=information, path=/util/information.jsp, redirect=false],
        success_gobierno=ForwardConfig [name=success_gobierno, path=/usuarios/successLoginGobierno.jsp, redirect=false], 
        success_consumer=ForwardConfig [name=success_consumer, path=/usuarios/successLoginConsumer.jsp, redirect=false]}]

login-action.xml
```
<action type="LoginAction" path="/Login.do">
    <forward name="success_gobierno" path="/usuarios/successLoginGobierno.jsp" />
    <forward name="success_consumer" path="/usuarios/successLoginConsumer.jsp" />
    <forward name="warning" path="/login/failParamsLogin.jsp" /> // NOT THE SAME
    <forward name="failure" path="/login/failLogin.jsp" /> // NOT THE SAME
</action>
```
Note: success and information are not in login-action.xml


let Height = class {
    constructor(height) {
        this.height = height;
    }
    hacerAlgo() {
        return `Haciendo Algo ${this.height}`;
    }
}
let Rectangle = class {
  constructor(height, width) {
    this.height = height;
    this.width = width;
  }
  hacerAlgo(evt) {
        console.log(`HEIGHT = ${this.height.hacerAlgo()}`);
    }
};
