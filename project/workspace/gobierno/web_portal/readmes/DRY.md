# Keepin' it DRY 

##### When adding HTML tags to an HTML document sooner than later its going to happen that a piece is going to be written two times. 
And the DRY principle states that you should not repeat yourself.

1. HTML partials definition
2. HTML partials current implementation
3. HTML partials serverside
4. HTML partials clientside
5. HTML partials: Interaction between server and client implementation
6. Case study


## 1.HTML partials
![asd](https://social.technet.microsoft.com/wiki/cfs-filesystemfile.ashx/__key/communityserver-wikis-components-files/00-00-00-00-05/7752.image001.png)


HTML partials, or templates, are UI reusable components.

There are two main reason why reusable components are good.
1. They are components. This means that using them will turn your huge file into lots of smaller files.
2. They are reusable. This is the most interesting part, because it is the feature needed for DRY principle support.


## 2.How
In this project, two approaches to UI reusable components have been made, one serverside and one clientside.

![](https://i.imgur.com/18ILitD.png)

## 3.Serverside
Here we can see how the JSP allow for HTML partials.
![](https://svgshare.com/i/6NC.svg)


They can even be parametrizable, by using sessions, cookies, or even straight-up parametrization like this!
 `/jsp/reusable/collapsable.jsp`
![](https://svgshare.com/i/6Mp.svg)

##### Why serverside?
DB-dependant HTML becomes trivial to write. Speeds up development time.

  

## 4.Clientside
Here we can see how using Lodash.JS we can create HTML partials with ease. 

The same could be done using the string interpolation.

But using Lodash let's us write the HTML outside our Javascript code, differentiate them clearly.

![](https://svgshare.com/i/6M4.svg)

##### Why clientside?
Search/Sort operations are performed on the client on data received from the server. Makes sense performance-wise.


## 5.Interaction
![](https://svgshare.com/i/6Mo.svg)
  
They can be nested one inside the other. Specifically, the server can modify the JS text.
![](https://i.imgur.com/m1VdEec.png)
  
That code can be found at `/jsp/reusable/carousel.jsp`

##### The full code excerpts
  
The JS excerpt where the last image was taken from:
![](https://svgshare.com/i/6NB.svg)
  
How JAVA would create and return a JSON object inside a JSP
![](https://svgshare.com/i/6Mz.svg)


## 6.The full fledged example
So, a carousel. Templatable, and needs data from the server to work with.


Using bootstrap the implementation of a carousel spends around 60 lines of HTML. Sure it can be minimized, minified, and I am counting comments and even the hardcoded data to be used. Surely it can be shorter. But asides from reducing the ammount of lines as some sort of codegolf artist, I focused mainly on giving this tools a reason to exist inside my project. This was the perfect use case.


A Carousel contains inside N ammount of indicators and items. The items are the slides, the indicators the buttons that move them.
An item can be either active or passive, and there can only be 1 active item at a time.
Indicators have the same restriction, except that they do not need help to work it by themselves. You place all of the items as passive, and the only problem will be that the slides won't move until you start moving them. Then they will continue to do so using a timer.

Now what happens if you place all passive items, like with indicators, is much worse. They dont get rendered at all, and it bassically breaks the Carousel.

So you need at least two tipes of Carousel items. Passive and active.

With that said, what differentiates Passive and Active carousel items is not that much. They share 80% of the HTML code. 

So if you are going to stay DRY, you are going to use another template, called `carousel-item-base`, which contains this 80% of HTML code.


Now, enough about reuse. About abstraction. We can abstract away this complexity about active and passive carousel items. We can make another template called `carousel-item-template`, which contains the conditional and uses either `carousel-item-active` or `carousel-item-passive`.


Finnally after fighting enough with Lodash.JS, we are left with a nice parametrized `carousel-template`, to which we can send data and it will just do the job.

What data? We can make an AJAX call to the server to find it but no! Better is to use JSP to it's strenghts as explained in 3.





![](https://svgshare.com/i/6P4.svg)

Code can be found at `/jsp/reusable/carousel.jsp`





# Conclusion
Citing Lord Wellington, who once said that given that the troops were well fed, well dressed, and taken care of, if they were to desert the battlefield they would be diciplined, a similar phrase could be coined for the ocassion:

"Given that tools have been made and used in this project to avoid HTML duplication, anyone who dare break DRY is to be diciplined."
![](https://i.imgur.com/fx84axN.png)

