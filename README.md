# WaveAccessTest

## About
Приложение, отображающее список людей с возможностью просмотра информации о каждом конкретном человеке, включая список его друзей с возможностью перехода между ними. 
Полученные данные кэшируются при получении и НЕ перезапрашиваются при последующем запуске приложения. 
На экране списка обеспечена возможность перезагрузки закешированных данных (swipe to refresh).

Min SDK - 24  
Target SDK - 32

## Technology Stack

Language - Kotlin  
Kotlin coroutines
Architecture - MVVM  
Rest API - Retrofit  
Database - SQLite (Room)  
Dependency Injection - Hilt.
Others -  Android Arhitecture Components, Databinding (kirich 1409 library), Livedata and Single Live Events. 

## Other
Скомпелированный файл apk лежит по пути:  
WaveAccessTest/app/release  
Протестирован на устройстве Samsung (api level 32). 
