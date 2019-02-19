class ChangeLanguage extends Module {
    constructor(config){
        super(config);
    }

    changeLang(evt) {
        evt.preventDefault();
        console.log("setIdioma, [EVENT] = %o", evt);
        const lang = $(evt.target).data("id");
        if(lang == null) alert("language %o", lang);
        console.log("language %o", lang);
        ChangeLanguageService.SET_IDIOMA(lang);
    }
};

$(() => {
    const changeLanguage = new ChangeLanguage(ChangeLanguageConfig);
    jUtils.loadModule(changeLanguage);
    console.log("ChangeLanguage View Loaded Modules: [ChangeLanguageConfig] = %o, [ChangeLanguage] = %o",
        ChangeLanguageConfig, changeLanguage);
});