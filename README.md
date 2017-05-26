# Messenger Application

*authors Sirasath Piyapootinun (`@Gotsira`),
Issaree Srisomboon (`@boranorben`)*

Documentation Online [Click Here](https://gotsira.github.io/chatapplication/).
Download JAR file [Click Here](https://github.com/Gotsira/chatapplication/blob/master/chatapplication.jar)

## UML Diagram

![](http://i.imgur.com/qmdJIhu.png)

## Description
This messenger application allows people to interact with each other by sending text messages. With the messenger, it brings people closer together overcoming the barriers known as distance.

## Proposition

- Learning about storing data on a database and sending information over the network.
- Utilizing a large variety of gui components.

## Features

- This application allows the user to add or delete friends through their username. And the user can also change the profile picture from their repository.
- 1:1 chat is possible only when both users are online.

## Interesting Technology

- JDBC (Java Database Connectivity)
- ORMLite
- JAVAFX and SceneBuilder using MVC (Model View Controller) pattern
- Tray Notification by [PlusHaze][1]

[1]: https://github.com/PlusHaze/TrayNotification "PlusHaze"

## Instruction on how to use this application
- Download JAR file [Here](https://github.com/Gotsira/chatapplication/blob/master/chatapplication.jar) and run the program.
- First shown page is Login page, you have 2 options here. If you are already a member of this chat application, just sign in with your username and password and then it will bring you to the second page, Home page.

![](http://i.imgur.com/b41EHtM.jpg)
- But if you are not a member of its, just click the button at bottom of the login page which shown **SIGN UP** and field your information and click **Register** button.

![](http://i.imgur.com/uGGNrvp.jpg)

- In Home page, the default profile picture is shown in the image, but you can change it by clicking the pen button at the right side of the page. Once you finish changing the picture, it still be there even though you log out from the server.

![](http://i.imgur.com/0baxqez.jpg)

![](http://i.imgur.com/2OZPzg3.jpg)

- Near the Refresh button at the top of the page, there is a menu item that you can select it to add or delete friend and your friend list will automatically updated.

![](http://i.imgur.com/vM4Ogzu.jpg)

- You can chat 1:1 by selecting one of your friend in the friend list and click the **NEW CHAT** button.

![](http://i.imgur.com/rmM73es.jpg)

- If your selected friend is offline, your message cannot be sent to your freind but there will be a notification message shown that the user are offline.

![](http://i.imgur.com/8V3rLej.jpg)

- Otherwise if your friend are online, this notification message will not shown up. It means you can chat with you friend now.

![](http://i.imgur.com/cymcnxL.jpg)
