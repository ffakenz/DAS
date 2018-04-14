Proyect structure:
    in web.xml: src.packages -> ar.edu.ubp.das.src. defines the path where each module will be located
    the defined each module structure as following
            module              // name of the module
                \actions        // servlet functions: req => resp
                \daos           // point of connections with the DB
                \forms          // entities mapped from DB
                \boundaries     // interfaces (contracts) needed to be implemented by the module
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
        - create a new module folder: src\${module} with above folder structure defien
        - define boundaries ${module}Req, ${module}Resp and ${module} interface containing required behavior under ${module}\boundaries
        - define ${someform}Form under ${module}\forms if some new entity is required
        - define an interactor ${module}Impl.java implementing the ${module} interface under ${module}\boundaries. 
            if the interactor needs some DB connection, a Dao should be injected at creation time (throught constructor DI)
        - create a test for the interactor mockin the dao if needed and verify it behaves as expected
            (this dao should contain mocks for each needed function)
        
    - DB
        - define a MS${someform}Dao under ${module}\daos to manage the form mapping and DB related calls
        - create SQL procedures needed under project\db\gobierno\${module}\procedures.sql      
    
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
        - implement the ${someaction}Ac tion.java
        - run under \web_portal: mvn clean install tomcat7:run to verify above changes
    