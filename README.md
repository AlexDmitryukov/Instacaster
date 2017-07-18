# Instacaster

Applicaster's assignment... or an attempt to take over Instagram?


This project is an interview assignment for Applicater Inc.
User is supposed to be able to authenticate with Instagram and pull 10 most recent local posts. Also, user can adjust search radius; as per Instagram Developer's specification this value is caped at 5000 meters. Application will automatically double the radius if the number of local posts is under 10. Once succesfully loaded, posts are stored in local SQLite database and can be retrieved if the network is unavailable or API call fails.

Application is build with the MVP pattern in mind and core dependencies are injected with Dagger.

TODO:
<br />
Test cases for Presenter and View
<br />
Refactoring Presenter
<br />
UI tweaks

Instagram Login Library
https://github.com/lorensiuswlt/AndroidInstagram

RxPermissions
https://github.com/tbruyelle/RxPermissions

Test Users:
Will be provided in the email

Test Location: Wilton Manors, FL 

<code>
    LATITUDE_MOCK = 26.16505
    LONGITUDE_MOCK = -80.1588699
</code>
<br />
<img src="https://user-images.githubusercontent.com/22647547/28331324-990389da-6bbe-11e7-8720-9badce243342.jpg" width="250">
<img src="https://user-images.githubusercontent.com/22647547/28331327-9b4793f8-6bbe-11e7-897a-3d016ad7ab36.jpg" width="250">
<img src="https://user-images.githubusercontent.com/22647547/28331330-9d382d80-6bbe-11e7-8b46-32cfc4ad4afc.jpg" width="250">

