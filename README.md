# Movieous
Movieous: is a movie app that allows users to view and rate movies without watching them using a simply and friendly application which uses a local database (SQLite).

This app was implemented using Andriod Studio

## Movieous Functionality

### 1. Introduction Layout
This layout has a “connect” button which will connect to a Server using REST to load movies data
into an array-List.

If connection successful → go-to login and registration section. 
If connection unsuccessful (any error) → show error message and stay on the same layout. 

### 2. Login and Sign up  Layout

This layout should have (1) Login layout and (2) a “Sign-Up” button.
- On the main page (i.e., first page) the customer can enter his/her e-mail and password that are 
registered in the sqlite database to login to a special menu List. This layout also must have a check 
box called “remember me” which will save the email and password in shared preferences so next 
time we login we do not need to type the email and password.

- The Sign-Up button will redirect us to another layout (fragment) where we will enter our email 
(must be a valid email) as a primary key, first name (not less than 3 characters), gender (spinner), 
password (must not be less than 3 characters and must include at least 1 small character, 1 capital 
letter, 1 number, and one special character), confirm password (the password should be encrypted 
using a secure Hash Function) and one phone number (must be at least 14 digit number and starts 
with 009705. If all the previous conditions are entered correctly then and only then we can register.

### 3. Home Layout (Navigation Drawer)
This layout should be a Navigation Drawer Activity which will contain the navigation bar with the 
following functionality:
- Movie List: which will contain all the movies fetched from the API link. 
- Watch List: which will contain all movies added to my watch list. 
- Rated List: which will contain all movies rated by the customer, each with its rating. 
- Profile: which will allow the customer to change his password and first name.
- Contact us: this will redirect the customer to a new layout to help sending an email to the provider.
- Logout: which will log the customer out from this profile and redirect him to the login page. 

#### Movie List: 
In this List you must use (Recycler View). This list should include all movies where each one should 
be defined by (Movie Poster (get it from the “posterurl”), title, release date, duration, imdb-Rating and 
genres) so if the customer clicked on one card, then all details of the that pressed movie appears (this must 
be done using fragments). Also, a filter to search on a special type should be implemented to search for a 
movie based on (1) release date, (2) duration and (3) imdbRating (multiple filters can be applied together. 
E.g., we can search for a movie released in 2018-02-14 with duration between 120 -160 minute(min-max), 
and imdb rating greater than 7.
Although, beside each movie, there must be a “add to Watch List” button which will add the
selected movie to watch list (if the movie is already added then the button should be “remove from watch 
list”) and a rate button which will pop up a rating bar stars layout with will ask the user to rate the selected 
movie. (Rating must be from 0 to 10 with half star step).
When choosing a movie, all movie details must appear and the user can add comments below the 
details, these comments should be shown to all users each with his first name and the comment message. 
(think of it as youtube comments below the videos).
#### Watch List 
This will only include all movies added to watch list with the date and time when they were added.
#### Rated List 
This will include all rated movies each with the its rated value besides it.
Profile 
In this layout, each customer should view all this personal information and be able to change his first 
name and password (with all the conditions from the sign-up page) (when changing password, the old 
password, new password and confirm password are mandatory)
#### Contact us 
This layout should have one send button and subject edit-text. The user is required to add the email 
subject text and click on the send button which will open Gmail to send an email to the provider on his 
main email “movies@movies.com” with email title of customer name and an email body what is stated in 
the contact us layout edit-text.
#### Logout: 
Which will log the customer out from this profile and redirect him to the login page.

# The project must be designed using Android packages and must use:
▪ Android Layouts (dynamically and statically). 
▪ Intents and Notifications (toast messages). 
▪ SQLite Database. 
▪ Animation (frame of tween). 
▪ Fragments. 
▪ Shared Preferences. 
▪ REST. Using this link https://firebasestorage.googleapis.com/v0/b/advanceproj1.appspot.com/o/movies-in-theaters.json?alt=media&token=e3121ae7-be1b-4480-99d8-
c1d0ad2eaa2f

