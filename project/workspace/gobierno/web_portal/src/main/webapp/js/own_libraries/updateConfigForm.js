const updateConfigForm = {
    showForm : (params) => {
        const showHeader = (configTecno, concesionariaId) => `
            <div class="form-group">
            <input type="hidden" name="concesionariaId" id="concesionariaId" value="${concesionariaId}" />
            <select id="${Id.UPDATE_CONFIG_SELECT}" name="configTecno">
                <option value="${ConfigTecno.REST.configTecno}" ${configTecno == ConfigTecno.REST.configTecno ? " selected " : ""}>
                    ${ConfigTecno.REST.configTecno}
                </option>
                <option value="${ConfigTecno.AXIS.configTecno}" ${configTecno == ConfigTecno.AXIS.configTecno ? " selected " : ""}>
                    ${ConfigTecno.AXIS.configTecno}
                </option>
                <option value="${ConfigTecno.CXF.configTecno}" ${configTecno == ConfigTecno.CXF.configTecno ? " selected " : ""}>
                    ${ConfigTecno.CXF.configTecno}
                </option>
            </select>
            </div>
        `;

        const showControls = (configParams) =>
            configParams.map( param =>
                `<div class="form-group">
                    <label class="col-md-4 control-label" for="">${param.name}</label>
                    <input type="text" class="form-control form-control-lg ${Class.UPDATE_CONFIG_PARAM}" name=${param.name} value=${param.value} size=120 required/>
                </div>`
            ).reduce((acc, elem) => { acc += elem + " "; return acc }, "");

        const showFooter = (concesionariaId) => `
            <div class="form-group">
                <button type="button" id="${Class.BTN_UPDATE_CONFIG}-${concesionariaId}" class="${Class.BTN_UPDATE_CONFIG}">Update</button>
            </div>
            <div class="form-group">
                <button type="button" id="${Class.BTN_TEST_CONFIG}-${concesionariaId}" class="${Class.BTN_TEST_CONFIG}">Test</button>
            </div>`;


        return `
            <form id="${Id.UPDATE_CONFIG_FORM}" class="form-horizontal" method="post" >
                    ${showHeader(params.configTecno, params.concesionariaId)}
                    ${showControls(params.configParams)}
                    ${showFooter(params.concesionariaId)}
            </form>
        `;
    }
};