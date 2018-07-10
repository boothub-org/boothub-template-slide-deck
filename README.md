[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/boothub-org/boothub-template-slide-deck/blob/master/LICENSE)
[![Build Status](https://img.shields.io/travis/boothub-org/boothub-template-slide-deck/master.svg?label=Build)](https://travis-ci.org/boothub-org/boothub-template-slide-deck)
## Slide-Deck template ##

A [BootHub](https://boothub.org) template for slide decks that include code snippets in a language of your choice (Java, Groovy, or Kotlin).

The content of your slides is written in [AsciiDoc](https://asciidoctor.org/docs/what-is-asciidoc/) and converted to
an HTML presentation powered by [deck.js](http://imakewebthings.com/deck.js/).



The code snippets in your presentation are taken from a codebase.
This ensures that your slides always contain valid and up-to-date code. 
You can [include](https://asciidoctor.org/docs/user-manual/#include-directive)
whole source code files or [only portions](https://asciidoctor.org/docs/user-manual/#include-partial) of them.

A Gradle script lets you build the codebase and generate the HTML presentation.

Read the [documentation](http://slide-deck.boothub.org) for more details about this template.

### Quick start

You can generate a project skeleton based on the Slide-Deck template by using the [BootHub GUI](https://boothub.org/goto/org.boothub.slide-deck)
or the [BootHub CLI](https://boothub.org/app#/cli).
