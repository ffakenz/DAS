const updateConfigForm = {
    showForm : (params) => {
        const showHeader = (configTecno) => `
            <div class="form-group">
            <select id="${Id.UPDATE_CONFIG_SELECT}" name="configType">
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

        const showFooter = `
            <div class="form-group">
                <button type="button" class="${Class.BTN_UPDATE_CONFIG}">Update</button>
            </div>
            <div class="form-group">
                <button type="button" class="${Class.BTN_TEST_CONFIG}">Test</button>
            </div>`;


        return `
            <form id="${Id.UPDATE_CONFIG_FORM}" class="form-horizontal" method="post" >
                    ${showHeader(params.configTecno)}
                    ${showControls(params.configParams)}
                    ${showFooter}
            </form>
        `;
    },

    buildConfigurarConcesionariasForm : (jsonArray) => {
        if(jsonArray.length == 0) {
            return showForm(ConfigTecno.REST);
        }
        else {
            const head = jsonArray[0];
            const configTecno = head["configTecno"];
            const configParams = jsonArray.map( param => {
                let obj = {};
                obj["name"] = param.configParam;
                obj["value"] = param.value;
                return obj;
            });
            return showForm(
                {"configTecno": configTecno, "configParams": configParams}
            );
        }
    }
};