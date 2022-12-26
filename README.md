
# Notification Hunt

Are you tired of constantly checking the NIT Srinagar website for updates and notifications? Wish you could get all the latest updates delivered straight to your inbox with just one click? Look no further than Notification Hunt!

This application is a web scraping bot which is programmed to visit the website, grab the relevant page and extract useful information.




## How it works?

A java library, ***jsoup***, is used to make HTTP request to the server, find, extract and manipulate data from HTML document using DOM traversal or CSS selectors.

The information extracted from the webpage https://nitsri.ac.in/Pages/DisplayPages.aspx?page=cackm is compared with the previously stored updates in a local file. If new updates exists, a mail containing these updates is sent to the email ID specified by the user with the help of an API  ***JavaMail***.
No more manual refreshing or missed notifications - get all the updates you need in one convenient place!



## Requirements

jsoup is available as a downloadable .jar java library. The current release version is 1.15.3.

[jsoup-1.15.3.jar](https://jsoup.org/packages/jsoup-1.15.3.jar) core library

### Maven

If you use Maven to manage the dependencies in your Java project, you do not need to download; just place the following into your POM's <dependencies> section:

```
<dependency>
  <!-- jsoup HTML parser library @ https://jsoup.org/ -->
  <groupId>org.jsoup</groupId>
  <artifactId>jsoup</artifactId>
  <version>1.15.3</version>
</dependency>
```
### Gradle

```
// jsoup HTML parser library @ https://jsoup.org/
implementation 'org.jsoup:jsoup:1.15.3
```

For sending the email using JavaMail API, you need to load the two jar files:
* mail.jar   
* activation.jar
Download these files from [here](https://static.javatpoint.com/src/mail/mailactivation.zip).

You may also visit [this](https://www.javatpoint.com/java-mail-api-tutorial) site to learn using this API.


## Usage

While executing the file one needs to pass three command line arguments:
1. The sender's EmailId
2. 16 digit app authentication password

    (An App Password is a 16-digit passcode that gives a less secure app or device permission to access your Google Account)
3. The Receiver's EmailId

```
java Driver sendermail@gmail.com asdftyhuijgtredf receiver@gmail.com
```

  ![Demo_EmailImage](https://user-images.githubusercontent.com/104207247/191587732-a88ed3d8-3133-4753-b35b-6eda56cf7fcf.jpeg)

## Current Status
I am actively working on improving the Notification Hunt program, and am always open to contributions and suggestions. If you have any ideas on how to make this application even better, please don't hesitate to reach out!
  
