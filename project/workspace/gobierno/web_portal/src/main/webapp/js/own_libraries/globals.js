const Action = {
    HOME_ENDPOINT : "/web_portal/home/Home.do",
    SHOW_REGISTRAR_CONCESIONARIA_ENDPOINT : "/web_portal/home/ShowRegistrarConcesionaria.do",
    SHOW_LOGIN_ENDPOINT : "/web_portal/home/ShowLogin.do",
    LOGIN_ENDPOINT : "/web_portal/login/Login.do",
    LOGOUT_ENDPOINT : "/web_portal/login/Logout.do",
    CHANGE_LANGUAGE_ENDPOINT : "/web_portal/other/ChangeLanguage.do",
    REGISTRAR_CONCESIONARIA_ENDPOINT : "/web_portal/concesionarias/RegistrarConcesionaria.do",
    APROBAR_CONCESIONARIA_ENDPOINT : "/web_portal/concesionarias/AprobarConcesionaria.do",
    DESAPROBAR_CONCESIONARIA_ENDPOINT : "/web_portal/concesionarias/DesAprobarConcesionaria.do",
    CONCESIONARIA_CONSULTAR_CONFIG_PARAM :  "/web_portal/concesionarias/ConsultarConcesionariaConfigParam.do",
    TEST_CONFIG :  "/web_portal/concesionarias/TestConfig.do",
    CONSUMO_TEST :  "/web_portal/consumo/Consumo.do",
    CONFIG_CONCESIONARIA : "/web_portal/concesionarias/Configurar.do"
};

const ConfigTecno = {
    REST: "REST",
    AXIS: "AXIS",
    CXF: "CXF"
};

const Id = {
    UPDATE_CONFIG_FORM: "update_config_form",
    INNER_UPDATE_CONFIG_FORM_DIV: "inner_update_config_form_div",
    UPDATE_CONFIG_SELECT: "update_config_select",
    UPDATE_CONFIG_LABEL: "update_config_label",
    TEST_CONFIG_LABEL: "test_config_label"
};

const Class = {
    UPDATE_CONFIG_PARAM: "config_param",
    BTN_UPDATE_CONFIG: "update_config_btn",
    BTN_TEST_CONFIG: "test_config_btn"
};

class ConfigParam {
    constructor(configTecno, configParams, concesionariaId) {
        this.configTecno = configTecno;
        this.configParams = configParams;
        this.concesionariaId = concesionariaId;
    }

    showHeader(){
        return `
                <div class="form-group">
                <input type="hidden" name="concesionariaId" id="concesionariaId" value="${this.concesionariaId}" />
                <select id="${Id.UPDATE_CONFIG_SELECT}" name="configTecno">
                    <option value="${ConfigTecno.REST}" ${this.configTecno == ConfigTecno.REST ? " selected " : ""}>
                        ${ConfigTecno.REST}
                    </option>
                    <option value="${ConfigTecno.AXIS}" ${this.configTecno == ConfigTecno.AXIS ? " selected " : ""}>
                        ${ConfigTecno.AXIS}
                    </option>
                    <option value="${ConfigTecno.CXF}" ${this.configTecno == ConfigTecno.CXF ? " selected " : ""}>
                        ${ConfigTecno.CXF}
                    </option>
                </select>
                </div>
            `;
    }

    showControls() {
        return this.configParams.map( param =>
           `<div class="form-group">
               <label class="col-md-4 control-label" for="">${param.name}</label>
               <input type="text" class="form-control form-control-lg ${Class.UPDATE_CONFIG_PARAM}" name="${param.name}" value="${param.value}" size=120 required/>
           </div>`
        ).reduce((acc, elem) => { acc += elem + " "; return acc }, "");
    }

    showFooter() {
        return `
               <div class="form-group">
                   <button type="button" id="${Class.BTN_UPDATE_CONFIG}-${this.concesionariaId}" class="${Class.BTN_UPDATE_CONFIG}">Update</button>
               </div>
               <div class="form-group">
                   <button type="button" id="${Class.BTN_TEST_CONFIG}-${this.concesionariaId}" class="${Class.BTN_TEST_CONFIG}">Test</button>
               </div>`;
    }

    showForm(){
        return `
            <form id="${Id.UPDATE_CONFIG_FORM}" class="form-horizontal" method="post" >
                    ${this.showHeader()}
                    ${this.showControls()}
                    ${this.showFooter()}
            </form>
        `;
    }
};

/* Application State */
let LAST_CONFIGS_CONSULTED_ST = undefined;
