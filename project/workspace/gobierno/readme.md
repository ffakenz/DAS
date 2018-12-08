#This module contains the code to run the gobierno service.


# How to run ?

1 - Only the first time run the next script:
- gobierno/db/src/main/resources/startup/build/build_docker.sh

2 - gobierno/db/src/main/resources/startup/run_docker.sh

3 - gobierno/db/run_db_test.sh 

4 - gobierno/web_portal/clean_and_run_web_portal.sh or /run_web_portal.sh 

5 - Open a browser in: http://localhost:9000/web_portal/home/Home.do

6 - if a test fails due to `The index X is out of range.` is because 
    the procedure in the dao is missing a `?` as a parameter
