# WeatherApp

<p float="left">
  <img src="https://github.com/isilay-subasi/WeatherApp/blob/main/images/icon.png" width="100" />

</p></br>

<p>
WeatherApp is a weather forecast application that displays near-location locations and a week's weather forecast of near-location locations using https://www.metaweather.com/api/.

</p></hr>

## Libraries

+ A request has been sent to the following url to list the locations of nearby locations and the names of cities. <br>
` /api/location/search/?query=(query) /api/location/search/?lattlong=(latt),(long) ` </br>
The  **Retrofit** library is used to send this request.

+ For weekly weather information, we need to send a request to the URL below.</br>
` /api/location/(woeid)/(date)/ ` </br>
**OkHttp** library is used for this request. </hr>

## Outputs
<p float="left">
  <img src="https://github.com/isilay-subasi/WeatherApp/blob/main/images/birinci.PNG" width="200" />
  <img src="https://github.com/isilay-subasi/WeatherApp/blob/main/images/ikinci.PNG" width="200" />
  <img src="https://github.com/isilay-subasi/WeatherApp/blob/main/images/ucuncu.PNG" width="200" />
  <img src="https://github.com/isilay-subasi/WeatherApp/blob/main/images/d%C3%B6rd%C3%BCnc%C3%BC.PNG" width="200" />

### Installation

+ Clone the repo to your local machine `git clone https://github.com/isilay-subasi/WeatherApp.git`

+ Open the cloned project in Android Studio.

+ Wait until you make sure the gradle packages are finished installing.( Watch status bar)

+ If your Android Studio version is less than 4.2.1, please upgrade your Android Studio to at least 4.2.1 version.

+ Or open the build.gradle file and set it to your Android Studio version from the dependecies section.

### Usage

+ run the project 