# Instacaster

Applicaster's assignment... or an attempt to take over Instagram?


This project is an interview assignment for Applicater Inc.
User is supposed to be able to authenticate with Instagram and pull 10 most recent local posts. Also, user can adjust search radius; as per Instagram Developer's specification this value is caped at 5000 meters. Application will automatically double the radius if the number of local posts is under 10. Once succesfully loaded, posts are stored in local SQLite database and can be retrieved if the network is unavailable or API call fails.

Application is build with the MVP pattern in mind and core dependencies are injected with Dagger.

Instagram Login Library
https://github.com/lorensiuswlt/AndroidInstagram

