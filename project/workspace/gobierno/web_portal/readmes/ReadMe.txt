Project structure:

    in web.xml: src.packages -> ar.edu.ubp.das.src. defines the path where each module will be located

    each module should be defined as following
            module              // name of the module
                \actions        // servlet functions: req => resp
                \daos           // point of connections with the DB
                \forms          // entities mapped from DB
                \ar.edu.ubp.das.src.login.boundaries     // interfaces (contracts) needed to be implemented by the module
                \interactors    // module implementations
                \...            // other stuff you might need
                
        webapp
            \js                 // ajax calls to the module\actions
            \jsp                // templates called by module\actions
            \WEB-INF
                \actions        // action-mappings for each module
                config.xml      // alias-mappings for each module
    
    Life cycle: ( jsp -> js -> action -> jsp )
        Notes
            - above actions will be called from js, then the action will redirect to the corresponding jsp
            - js actions are call from jsp. 
                
Implementation Walkthrough:
    - BACKEND
        - create a new module folder: src\${module} with above folder structure define
        - define ar.edu.ubp.das.src.login.boundaries ${module}Req, ${module}Resp and ${module} interface containing required behavior under ${module}\ar.edu.ubp.das.src.login.boundaries
        - define ${someform}Form under ${module}\forms if some new entity is required
        - define an interactor ${module}Impl.java implementing the ${module} interface under ${module}\ar.edu.ubp.das.src.login.boundaries.
            if the interactor needs some DB connection, a Dao should be injected at creation time (throught constructor DI)
        - create a test for the interactor mockin the dao if needed and verify it behaves as expected
            (this dao should contain mocks for each needed function)
        
    - DB
        - define a MS${someform}Dao under ${module}\daos to manage the form mapping and DB related calls
        - create SQL login.procedures needed under project\db\gobierno\${module}\login.procedures.sql
    
    - FMW MAPPING
        - add the module alias-mappings to \WEB-INF\config.xml: <alias name="${module}" />
        - create ${module}-actions.xml under \WEB-INF\actions 
        - add the action-mapping to \WEB-INF\actions\${module}-actions.xml:
            <action type="${someaction}Action" path="/${someaction}.do">
                <forward name="success" path="/${module}/${someaction}.jsp" />
            </action>
        
    - FRONTEND    
        - create module folder under: \webapp\jsp\${module}
        - create ${module}\${someaction}.jsp file
        - create a js file with same name: \js\${module}.js
        - add an ajax call in ${module}.js to: "/web_portal/${module}/${someaction}.do"
        
    -- FINAL
        - create a "Servlet" Action: ${module}\actions\${someaction}Action.java    
        - implement the ${someaction}Action.java
        - run under \web_portal: mvn clean install tomcat7:run-war to verify above changes



WORK TIPS:

- if you need refresh some page when you are doing changes on the FE and tomcat is running execute:
    * war:exploded

- if a test fails due to `The index X is out of range.` is because
    the procedure in the dao is missing a `?` as a parameter

- if a test fails due to `The value is not set for the parameter number 5.` is because
    the procedure in the dao has some extra `?` as a parameter

- Do not comment code in js files which are minimized by Javascript.do because
    we get the folowing error: `Uncaught SyntaxError: Unexpected end of input`

- If a test fail and you do some change in db, remember to do clean install