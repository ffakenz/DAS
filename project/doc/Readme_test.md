

# Proyect structure:
###### In this document a service called web_portal is going to be used as example
![img](http://i.imgur.com/J4pTLvW.png)



#### Two parts
###### Source packages, and the application.

![Imgur](https://i.imgur.com/bEINAzY.png)


| ![Imgur](https://i.imgur.com/ktklDiI.png)          | ![Imgur](https://i.imgur.com/wzqkOio.png)  |
|:-----------------------------------------------------:| :----------------------------------------:|
|The location for the source packages folder is defined inside `web.xml`. This is the path where each module will be located. ![Imgur](https://i.imgur.com/wERNqsm.png) | from the first column![Imgur](https://i.imgur.com/bEINAzY.png)|


The location for the source packages folder is defined inside `web.xml`. This is the path where each module will be located.
```xml
  <context-param>
    <param-name>src.packages</param-name>
    <param-value>ar.edu.ubp.das.src.</param-value>
  </context-param>
```
