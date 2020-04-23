# WebpageTextCounterJavaFX
First Commit/Build 
-had a working GUI that displayed label, text area, and buttons. Submit button worked but only output
  information to the console
  
 Second Build
 -Revamp of Webhandling class methods to use Java 8 stream features and convert HashMap to String value correctly. Added
  text area to display the results of the word count. Not all conditions are thoroughly tested for a good UI/UX. 
  
  Third Build
  -Added Testing classes for Heading type h3 HTML tag word count and Bold type b HTML tag word count. Bold word count is set to fail on assertEquals because I wanted to make sure I understood testing cases. Tried to create a testing Suite but they do not show up under the "suite creation wizard." I tried to add them manually but Testing suite completes in 0.00s and no failures occur so that isn't working.
  
  Fourth Build
  - Added JavaDoc notation for WebpageHandling class. *specifically scrapeWebPage(String webAddress) method.
  
  Fifth Build
  -added Database storage of results of website scrape. Tried to deploy to a jar with client/server capabilites but that build was unsuccessful.
  
  Future Builds
  - Implement clear all button for the label or add better conditional statments for all possible interaction cases.
  - Check on testing issues. Unsure why the testing suite isn't getting access to them in the testing package.
  - Add JavaDoc notation to all classes and methods.
