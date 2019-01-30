class ConfigParam {
    constructor(configTecno, configParams, concesionariaId) {
        this.configTecno = configTecno;
        this.configParams = configParams;
        this.concesionariaId = concesionariaId;
    }

    showHeader(){
        return `
                <div class="form-group">
                <input type="hidden" name="concesionariaId" value="${this.concesionariaId}" />
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
                   <label id="${Id.UPDATE_CONFIG_LABEL}"></label>
               </div>
               <div class="form-group">
                   <button type="button" id="${Class.BTN_TEST_CONFIG}-${this.concesionariaId}" class="${Class.BTN_TEST_CONFIG}">Test</button>
                   <label id="${Id.TEST_CONFIG_LABEL}"></label>
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
