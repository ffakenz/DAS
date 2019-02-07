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
                   <button type="button" id="${Class.UPDATE_CONFIG_BTN}-${this.concesionariaId}" class="${Class.UPDATE_CONFIG_BTN} btn btn-success">Update</button>
                   <label id="${Id.UPDATE_CONFIG_LABEL}"></label>
               </div>
               <div class="form-group">
                   <button type="button" id="${Class.TEST_CONFIG_BTN}-${this.concesionariaId}" class="${Class.TEST_CONFIG_BTN} btn btn-info">Test</button>
                   <label id="${Id.TEST_CONFIG_LABEL}"></label>
               </div>
               <div class="form-group">
                  <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
              </div>
              `;
    }

    showForm(){
        return `
            <div class="modal-header">
                <h5 class="modal-title">Actualizar Config Param</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="${Id.UPDATE_CONFIG_FORM}" class="form-horizontal" method="post" >
                        ${this.showHeader()}
                        ${this.showControls()}

                </form>
            </div>
            <div class="modal-footer">
                ${this.showFooter()}
            </div>
        `;
    }
};
