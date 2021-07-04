# cc-netflix-minusminus

Assignment about writing a software that is a network of loosely coupled web services. The different services communicate over REST APIs and are able to cooperate (in the sense that even if they are not on the same computer or are written in different languages, they still work together). Designed to show the power of microservices architecture.

## Service arhitecture

* Eureka Server for service discovery
* Zuul for API Gateway
* Video service
  * store Video entities in its **own database** (H2 is fine)
    * properties: id, name, url (from YouTube or Vimeo)
  * able to retrieve all videos (without recommendations) on a REST endpoint (GET)
  * able to retrieve videos+recommendations by a video id on a REST endpoint (GET)
  * able to update a video and it's recommendations (POST)
  * generates some video entities at startup time (via a CommandLineRunner or a @PostConstruct method) to have sample data (3-4 videos are enough)
* Video recommendation service
  * store Recommendations in its **own database** (H2 is fine)
  * properties: id, rating (integer, 1-5), comment, videoId
  * able to retrieve all recommendations for a videoId (GET)
  * able to save a new recommendation for a videoId
  * *(extra): update existing records*

## Frontend

* main page
  * list all videos (id+name, but without recommendations!)
  * every listed item points to a details page (eg: /video/3)
* details page
  * shows a video's name and embeds the YouTube URL
  * shows every recommendation in any order
    * *(extra): in descending order by creation date (for this, add an extra property to Recommendation entity)*
  * has a form for saving a new Recommendation
    * rating: dropdown input field (1-5)
    * comment: text input field
    * **Add** button

## Other specs/hints
* only ``Video service`` is available through Zuul. ``Video recommendation service`` is not a public microservice.
* if you press the **Add** button on the details page, it should call the save method (REST) on ``Video service``, which should call ``Video recommendation service`` to store the new recommendation
* when you call ``Video service``'s list method (REST) on the main page, it shouldn't call ``Video recommendation service`` at all, because we don't need the Recommendations at that time!
