## Contributing

We are pleased to receive any kind of contribution (issues, pull requests, suggestions ...).  

### Pull requests guidelines

To open a pull request on this repository, you must sign the contributor license agreement. 
 
<a href="https://cla-assistant.io/bonitasoft/bonita-rest-api-extension-archetype"><img src="https://cla-assistant.io/readme/badge/bonitasoft/bonita-rest-api-extension-archetype" alt="CLA assistant" /></a>

Here are a few things we would appreciate that you do when opening a pull request: 

#### Commit message format

Here is the expected commit format, please respect it, we rely on it to [automatically generate the changelog](https://github.com/lob/generate-changelog#usage): 

```
type(category): description [flags]

< commit description >
```

Where  `type`  is one of the following:

-   `breaking`
-   `build`
-   `ci`
-   `chore`
-   `docs`
-   `feat`
-   `fix`
-   `other`
-   `perf`
-   `refactor`
-   `revert`
-   `style`
-   `test`

Where  `flags`  is an optional comma-separated list of one or more of the following (must be surrounded in square brackets):

-   `breaking`: alters  `type`  to be a breaking change

And  `category`  can be anything of your choice. If you use a type not found in the list (but it still follows the same format of the message), it'll be grouped under  `other`.

#### Tests

Ensure that your contribution is correctly tested: 

 - Any update on the generated project must be tested through the generated unit tests
 - Any update on the archetype must be tested through the integration test suite (*src/test/resources/projects*)
