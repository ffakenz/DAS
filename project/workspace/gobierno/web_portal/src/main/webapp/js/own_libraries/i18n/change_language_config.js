const ChangeLanguageConfig = (module) => [
    {
        ctx: Id.LANG_DIV,
        cnfg: [
            { delegate: Class.LANG_BTN, handler: module.changeLang },
            { delegate: Id.LANG_SELECT, event_type: "change", handler: module.changeLang }
        ]
    }
];
    